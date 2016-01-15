package admin.cozycombat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import move.Move;

public class PlayerCharacter extends Combatant implements Parcelable {

    // TODO maybe implement a color and if you click on the avatar the color changes of the helmet

    private int level;
    private int levelPoints;

    private ArrayList<Integer> items;

    Item weapon;
    Item armor;
    Item boots;

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
        this.spells.add(Move.FIREBALL);
        this.spells.add(Move.HEAL);
        this.items = new ArrayList<Integer>();
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
    void addLevel(){ this.level++; }
    void addLevelPoints(int amount) { this.levelPoints += amount; }

    public int getLevel() { return this.level; }

    @Override
    public boolean isFoe(){ return false; }

    @Override
    public int getStrength(){
        int weaponBonus = 0;
        if (this.weapon != null){

        }
        return this.strength + weaponBonus;
    }
    @Override
    public int getWillpower() {
        int weaponBonus = 0;
        if (this.weapon != null) {

        }
        return this.willpower + weaponBonus;
    }
    @Override
    public int getDefense(){
        int armorBonus = 0;
        if (this.armor != null){

        }
        return this.defense + armorBonus;
    }
    @Override
    public int getResistance() {
        int armorBonus = 0;
        if (this.armor != null){

        }
        return this.resistance + armorBonus;
    }
    @Override
    public int getSpeed(){
        int speedBonus = 0;
        if (this.boots != null){

        }
        return this.speed + speedBonus;
    }

    public ArrayList<Integer> getItems() { return this.items; }

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
        for (int i = 0; i < this.spells.size(); i++) copiedPlayerCharacter.spells.add(this.spells.get(i));
        for (int i = 0; i < this.items.size(); i++) copiedPlayerCharacter.items.add(this.items.get(i));

        // TODO copy constructor for items
        //copiedPlayerCharacter.weapon = this.weapon;
        //copiedPlayerCharacter.armor = this.armor;
        //copiedPlayerCharacter.boots = this.boots;

        return copiedPlayerCharacter;
    }

    // Parcelable required to pass PlayerCharacter between Android activities
    public PlayerCharacter (Parcel in){
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
