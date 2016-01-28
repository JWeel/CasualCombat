// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package nl.mprog.casualcombat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashSet;

import item.EquippableItem;
import item.Item;
import move.Move;

// this is a combatant that is controlled by the user. it has some extra features, such as items and leveling
public class PlayerCharacter extends Combatant implements Parcelable {

    private int level;
    private int levelPoints;

    private ArrayList<Integer> usableItems;

    private EquippableItem weapon;
    private EquippableItem armor;
    private EquippableItem boots;

    private int weaponId;
    private int armorId;
    private int bootsId;

    private String colorString;

    PlayerCharacter(){
        this.level = 0;
        this.money = 0;
        this.levelPoints = 10;
        this.maxHealth = 6;
        this.currentHealth = maxHealth;
        this.maxMagic = 3;
        this.currentMagic = maxMagic;
        this.strength = 1;
        this.defense = 1;
        this.speed = 1;
        this.willpower = 1;
        this.resistance = 1;
        this.name = "";
        this.spells = new HashSet<>();
        this.spells.add(Move.FIREBALL);
        this.spells.add(Move.HEAL);
        this.usableItems = new ArrayList<>();
        this.usableItems.add(Item.HERB);
        this.colorString = "#FFFFFFFF";
    }

    // returns true if a player character has both a move and a target
    boolean isReady(){
        if (this.move == null) return false;
        return this.move.getTarget() != Move.NO_TARGET;
    }

    void subtractLevelPoint(){
        this.levelPoints--;
    }
    int getLevelPoints(){
        return this.levelPoints;
    }
    boolean finishedLevelUp(){
        return this.levelPoints == 0;
    }

    void addHealth(){
        this.maxHealth++;
        this.currentHealth++;
    }
    void addMagic(){
        this.maxMagic++;
        this.currentMagic++;
    }
    void addStrength(){
        this.strength++;
    }
    void addWillpower(){
        this.willpower++;
    }
    void addDefense(){
        this.defense++;
    }
    void addResistance(){
        this.resistance++;
    }
    void addSpeed(){
        this.speed++;
    }

    void addMoney(int amount){ this.money += amount; }
    void subtractMoney(int amount){ this.money -= amount; }
    void addLevel(){ this.level++; }
    void addLevelPoints(int amount) { this.levelPoints += amount; }

    void addSpell(Move spell){
        spells.add(spell.getId());
    }

    void addUsableItem(Item item){
        usableItems.add(item.getId());
    }

    // sets a passed item into one of the equipment slots
    void equipItem(EquippableItem item){
        switch(item.getType()){
            case EquippableItem.TYPE_WEAPON:
                this.weapon = item.copy();
                break;
            case EquippableItem.TYPE_ARMOR:
                this.armor = item.copy();
                break;
            case EquippableItem.TYPE_BOOTS:
                this.boots = item.copy();
                break;
        }
    }

    // because JSON does not work well with abstract objects, and equipment is abstract, an Id is needed when saving and storing with JSON
    // this method sets equipment to null, temporarily storing their ids as ints
    void prepareForSave() {
        if (this.weapon == null) this.weaponId = -1;
        else {
            this.weaponId = this.weapon.getId();
            this.weapon = null;
        }
        if (this.armor == null) this.armorId = -1;
        else {
            this.armorId = this.armor.getId();
            this.armor = null;
        }
        if (this.boots == null) this.bootsId = -1;
        else {
            this.bootsId = this.boots.getId();
            this.boots = null;
        }
    }
    // see prepareForSave/0, this restores the equippableItems from the temporary ints
    void restoreAfterSave(){
        if (this.weaponId == -1) this.weapon = null;
        else this.weapon = (EquippableItem) Item.findItemById(this.weaponId);
        if (this.armorId == -1) this.armor = null;
        else this.armor = (EquippableItem) Item.findItemById(this.armorId);
        if (this.bootsId == -1) this.boots = null;
        else this.boots = (EquippableItem) Item.findItemById(this.bootsId);
    }

    // returns true if the player character already has a similar item equipped
    boolean alreadyHasEquipment(EquippableItem newItem){
        switch(newItem.getType()){
            case EquippableItem.TYPE_WEAPON:
                if (this.getWeapon() == null) return false;
                else return this.weapon.getId() == newItem.getId();
            case EquippableItem.TYPE_ARMOR:
                if (this.getArmor() == null) return false;
                else return this.armor.getId() == newItem.getId();
            case EquippableItem.TYPE_BOOTS:
                if (this.getBoots() == null) return false;
                else return this.boots.getId() == newItem.getId();
            default: return false;
        }
    }

    public EquippableItem getWeapon() {
        return this.weapon;
    }
    public EquippableItem getArmor() {
        return this.armor;
    }
    public EquippableItem getBoots() {
        return this.boots;
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    public boolean isFoe(){
        return false;
    }

    // these basic getter methods also take equipment bonuses into account
    @Override
    public int getStrength(){
        int weaponBonus = 0;
        if (this.weapon != null){
            weaponBonus += this.weapon.getBonusStrength();
        }
        return this.strength + weaponBonus;
    }
    @Override
    public int getWillpower() {
        int weaponBonus = 0;
        if (this.weapon != null) {
            weaponBonus += this.weapon.getBonusWillpower();
        }
        return this.willpower + weaponBonus;
    }
    @Override
    public int getDefense(){
        int armorBonus = 0;
        if (this.armor != null){
            armorBonus += this.armor.getBonusDefense();
        }
        return this.defense + armorBonus;
    }
    @Override
    public int getResistance() {
        int armorBonus = 0;
        if (this.armor != null){
            armorBonus += this.armor.getBonusResistance();
        }
        return this.resistance + armorBonus;
    }
    @Override
    public int getSpeed(){
        int speedBonus = 0;
        if (this.boots != null){
            speedBonus += this.boots.getBonusSpeed();
        }
        return this.speed + speedBonus;
    }

    public ArrayList<Integer> getUsableItems() {
        return this.usableItems;
    }

    public String getColorString(){
        return this.colorString;
    }

    // picks a random color by taking random numbers between 0 and 255 for red, green and blue
    public void changeColorString(){
        String red = String.format("%02X", TitlePage.random.nextInt(0xFF));
        String green = String.format("%02X", TitlePage.random.nextInt(0xFF));
        String blue = String.format("%02X", TitlePage.random.nextInt(0xFF));
        this.colorString= "#FF" + red + green + blue;
    }

    // basic copy constructor
    public PlayerCharacter copy(){
        PlayerCharacter copiedPlayerCharacter = new PlayerCharacter();

        copiedPlayerCharacter.maxHealth = this.maxHealth;
        copiedPlayerCharacter.maxMagic = this.maxMagic;
        copiedPlayerCharacter.currentHealth = this.currentHealth;
        copiedPlayerCharacter.currentMagic = this.currentMagic;
        copiedPlayerCharacter.strength = this.strength;
        copiedPlayerCharacter.willpower = this.willpower;
        copiedPlayerCharacter.defense = this.defense;
        copiedPlayerCharacter.resistance = this.resistance;
        copiedPlayerCharacter.speed = this.speed;
        copiedPlayerCharacter.level = this.level;
        copiedPlayerCharacter.levelPoints = this.levelPoints;
        copiedPlayerCharacter.money = this.money;
        copiedPlayerCharacter.name = this.name;
        copiedPlayerCharacter.colorString = this.colorString;
        copiedPlayerCharacter.spells = new HashSet<>(this.spells);
        copiedPlayerCharacter.usableItems = new ArrayList<>(this.usableItems);

        if (this.weapon != null) copiedPlayerCharacter.weapon = this.weapon.copy();
        else copiedPlayerCharacter.weapon = null;
        if (this.armor != null) copiedPlayerCharacter.armor = this.armor.copy();
        else copiedPlayerCharacter.armor = null;
        if (this.boots != null) copiedPlayerCharacter.boots = this.boots.copy();
        else copiedPlayerCharacter.boots = null;

        return copiedPlayerCharacter;
    }

    // Parcelable required to pass PlayerCharacter between Android activities
    public PlayerCharacter (Parcel in){
        String[] contents = new String[17];
        in.readStringArray(contents);
        this.name = contents[0];
        this.level = Integer.parseInt(contents[1]);
        this.money = Integer.parseInt(contents[2]);

        this.maxHealth = Integer.parseInt(contents[3]);
        this.maxMagic = Integer.parseInt(contents[4]);
        this.currentHealth = Integer.parseInt(contents[5]);
        this.currentMagic = Integer.parseInt(contents[6]);

        this.strength = Integer.parseInt(contents[7]);
        this.willpower = Integer.parseInt(contents[8]);
        this.defense = Integer.parseInt(contents[9]);
        this.resistance = Integer.parseInt(contents[10]);
        this.speed = Integer.parseInt(contents[11]);
        this.levelPoints = Integer.parseInt(contents[12]);

        int tempWeaponId = Integer.parseInt(contents[13]);
        int tempArmorId = Integer.parseInt(contents[14]);
        int tempBootsId = Integer.parseInt(contents[15]);

        if (tempWeaponId != -1) this.weapon = (EquippableItem) Item.findItemById(tempWeaponId);
        if (tempArmorId != -1) this.armor = (EquippableItem) Item.findItemById(tempArmorId);
        if (tempBootsId != -1) this.boots = (EquippableItem) Item.findItemById(tempBootsId);

        this.colorString = contents[16];

        ArrayList<Integer> readSpells = new ArrayList<>();
        in.readList(readSpells, null);
        this.spells = new HashSet<>(readSpells);
        this.usableItems = new ArrayList<>();
        in.readList(usableItems, null);
    }

    // standard Parcelable methods
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int tempWeaponId = -1;
        if (this.weapon != null) tempWeaponId = this.weapon.getId();
        int tempArmorId = -1;
        if (this.armor != null) tempArmorId = this.armor.getId();
        int tempBootsId = -1;
        if (this.boots != null) tempBootsId = this.boots.getId();
        dest.writeStringArray(new String[]{
                this.name,
                String.valueOf(this.level),
                String.valueOf(this.money),
                String.valueOf(this.maxHealth),
                String.valueOf(this.maxMagic),
                String.valueOf(this.currentHealth),
                String.valueOf(this.currentMagic),
                String.valueOf(this.strength),
                String.valueOf(this.willpower),
                String.valueOf(this.defense),
                String.valueOf(this.resistance),
                String.valueOf(this.speed),
                String.valueOf(this.levelPoints),
                String.valueOf(tempWeaponId),
                String.valueOf(tempArmorId),
                String.valueOf(tempBootsId),
                this.colorString
        });
        dest.writeList(new ArrayList<>(this.spells));
        dest.writeList(this.usableItems);
    }
    public static final Parcelable.Creator<PlayerCharacter> CREATOR = new Parcelable.Creator<PlayerCharacter>() {
        @Override
        public PlayerCharacter createFromParcel(Parcel source) {
            return new PlayerCharacter(source);
        }

        @Override
        public PlayerCharacter[] newArray(int size) {
            return new PlayerCharacter[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
}
