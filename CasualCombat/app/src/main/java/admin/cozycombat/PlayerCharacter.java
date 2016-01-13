package admin.cozycombat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PlayerCharacter extends Combatant implements Parcelable {

    private int maxHealth;
    private int maxMagic;
    private int currentHealth;
    private int currentMagic;

    private int strength;
    private int willpower;
    private int defense;
    private int resistance;
    private int speed;

    private int level;
    private int money;

    private String name;

    private Move move;

    private int levelPoints;

    private ArrayList<Integer> spells;
    private ArrayList<Integer> items;

    Item weapon;
    Item armor;
    Item boots;

    static final int NO_TARGET = -2;
    static final int TARGET_SELF = -1;

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
        this.spells = new ArrayList<Integer>();
        this.spells.add(2);
        this.items = new ArrayList<Integer>();
    }

    // returns true if a player character has both a move and a target
    boolean isReady(){
        if (this.move == null) return false;
//        if (this.move.getId() == Move.BASIC_DEFEND[Move.INDEX_ID]) return true;
        return this.move.getTarget() != NO_TARGET;
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

    void setName(String s){
        this.name = s;
    }

    @Override
    Move getMove(){
        return this.move;
    }
    @Override
    void setMove(Move m){
        this.move = m;
    }

    @Override
    boolean isFoe(){ return false; }

    @Override
    void lowerHealth(int damage){
        this.currentHealth -= damage;
        if (currentHealth < 0) currentHealth = 0;
    }
    @Override
    void lowerMagic(int cost){
        this.currentMagic -= cost;
        if (currentMagic < 0) currentMagic = 0;
    }

    @Override
    int getMaxHealth() { return this.maxHealth; }
    @Override
    int getMaxMagic() { return this.maxMagic; }
    @Override
    int getHealth(){
        return this.currentHealth;
    }
    @Override
    int getMagic(){
        return this.currentMagic;
    }
    @Override
    int getStrength(){
        int weaponBonus = 0;
        if (this.weapon != null){

        }
        return this.strength + weaponBonus;
    }
    @Override
    int getWillpower() {
        int weaponBonus = 0;
        if (this.weapon != null) {

        }
        return this.willpower + weaponBonus;
    }
    @Override
    int getDefense(){
        int armorBonus = 0;
        if (this.armor != null){

        }
        return this.defense + armorBonus;
    }
    @Override
    int getResistance() {
        int armorBonus = 0;
        if (this.armor != null){

        }
        return this.resistance + armorBonus;
    }
    @Override
    int getSpeed(){
        int speedBonus = 0;
        if (this.boots != null){

        }
        return this.speed + speedBonus;
    }
    @Override
    int getLevel(){ return this.level; }
    @Override
    int getMoney(){ return this.money; }
    @Override
    String getName(){ return this.name; }
    @Override
    ArrayList<Integer> getSpells() { return this.spells; }
    ArrayList<Integer> getItems() { return this.items; }



    // Parcelable required to pass PlayerCharacter between Android activities
    PlayerCharacter (Parcel in){
        String[] contents = new String[12];
        in.readStringArray(contents);
        name = contents[0];
        level = Integer.parseInt(contents[1]);
        money = Integer.parseInt(contents[2]);

        maxHealth = Integer.parseInt(contents[3]);
        maxMagic = Integer.parseInt(contents[4]);
        currentHealth = Integer.parseInt(contents[5]);
        currentMagic = Integer.parseInt(contents[6]);

        strength = Integer.parseInt(contents[7]);
        willpower = Integer.parseInt(contents[8]);
        defense = Integer.parseInt(contents[9]);
        resistance = Integer.parseInt(contents[10]);
        speed = Integer.parseInt(contents[11]);

        spells = new ArrayList<>();
        in.readList(spells, null);
        items = new ArrayList<>();
        in.readList(items, null);
    }

    // standard Parcelable methods
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                name,
                String.valueOf(level),
                String.valueOf(money),
                String.valueOf(maxHealth),
                String.valueOf(maxMagic),
                String.valueOf(currentHealth),
                String.valueOf(currentMagic),
                String.valueOf(strength),
                String.valueOf(willpower),
                String.valueOf(defense),
                String.valueOf(resistance),
                String.valueOf(speed)
        });
        dest.writeList(spells);
        dest.writeList(items);
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
