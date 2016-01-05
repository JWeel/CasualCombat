package admin.readpub;

public class Combatant {
    int maxHealth;
    int maxMagic;
    private int currentHealth;
    private int currentMagic;

    private int strength;
    private int defense;
    private int speed;

    int level;

    Item weapon;
    Item armor;
    Item boots;

    public static final byte BRAWLER = 0;
    public static final byte ARCHER = 1;
    public static final byte MAGE = 2;

    Combatant(byte type){
        this.level = 0;

        switch(type){
            case BRAWLER:
                this.maxHealth = 10;
                this.currentHealth = maxHealth;
                this.maxMagic = 3;
                this.currentMagic = maxMagic;
                this.strength = 5;
                this.defense = 3;
                this.speed = 2;
                break;
            case ARCHER:
                this.maxHealth = 8;
                this.currentHealth = maxHealth;
                this.maxMagic = 5;
                this.currentMagic = maxMagic;
                this.strength = 4;
                this.defense = 2;
                this.speed = 4;
                break;
            case MAGE:
                this.maxHealth = 6;
                this.currentHealth = maxHealth;
                this.maxMagic = 10;
                this.currentMagic = maxMagic;
                this.strength = 2;
                this.defense = 1;
                this.speed = 3;
                break;
            default:
                this.maxHealth = 0;
                this.currentHealth = 0;
                this.maxMagic = 0;
                this.currentMagic = 0;
                this.strength = 0;
                this.defense = 0;
                this.speed = 0;
        }
    }

    int getHealth(){
        return this.currentHealth;
    }
    int getMagic(){
        return this.currentMagic;
    }
    int getAttack(){
        int weaponBonus = 0;
        if (this.weapon != null){

        }
        return this.strength + weaponBonus;
    }
    int getDefense(){
        int armorBonus = 0;
        if (this.armor != null){

        }
        return this.defense + armorBonus;
    }
    int getSpeed(){
        int speedBonus = 0;
        if (this.boots != null){

        }
        return this.speed + speedBonus;
    }
}
