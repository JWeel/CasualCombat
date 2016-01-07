package admin.fastfight;

import java.util.ArrayList;

public class PlayerCharacter extends Combatant {

    private int maxHealth;
    private int maxMagic;
    private int currentHealth;
    private int currentMagic;

    private int strength;
    private int defense;
    private int speed;

    private int level;
    private int money;

    String name;

    private ArrayList<Integer> spells;
    private ArrayList<Integer> items;

    Item weapon;
    Item armor;
    Item boots;

    public static final byte BRAWLER = 0;
    public static final byte ARCHER = 1;
    public static final byte MAGE = 2;

    PlayerCharacter(byte type){
        this.level = 0;
        this.money = 0;

        switch(type){
            case BRAWLER:
                this.maxHealth = 10;
                this.currentHealth = maxHealth;
                this.maxMagic = 3;
                this.currentMagic = maxMagic;
                this.strength = 5;
                this.defense = 3;
                this.speed = 2;
                this.name = "BRAWLER";
                this.spells = new ArrayList<Integer>();
                this.spells.add(2);
                this.items = new ArrayList<Integer>();
                break;
            case ARCHER:
                this.maxHealth = 8;
                this.currentHealth = maxHealth;
                this.maxMagic = 5;
                this.currentMagic = maxMagic;
                this.strength = 4;
                this.defense = 2;
                this.speed = 4;
                this.name = "ARCHER";
                this.spells = new ArrayList<Integer>();
                this.spells.add(2);
                this.items = new ArrayList<Integer>();
                break;
            case MAGE:
                this.maxHealth = 6;
                this.currentHealth = maxHealth;
                this.maxMagic = 10;
                this.currentMagic = maxMagic;
                this.strength = 2;
                this.defense = 1;
                this.speed = 3;
                this.name = "MAGE";
                this.spells = new ArrayList<Integer>();
                this.spells.add(2);
                this.items = new ArrayList<Integer>();
                break;
            default:
                this.maxHealth = 0;
                this.currentHealth = 0;
                this.maxMagic = 0;
                this.currentMagic = 0;
                this.strength = 0;
                this.defense = 0;
                this.speed = 0;
                this.name = "";
        }
    }


    @Override
    void setHealth(int damage){
        this.currentHealth -= damage;
        if (currentHealth < 0) currentHealth = 0;
    }
    @Override
    void setMagic(int cost){
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
    int getDefense(){
        int armorBonus = 0;
        if (this.armor != null){

        }
        return this.defense + armorBonus;
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
}
