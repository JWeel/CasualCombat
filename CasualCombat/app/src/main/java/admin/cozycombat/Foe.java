package admin.cozycombat;

import java.util.ArrayList;

public class Foe extends Combatant {

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

    private ArrayList<Integer> spells;

    public static final int GOBLIN = 0;

    Foe(int type){

        switch(type){
            case GOBLIN:
                this.maxHealth = 3;
                this.currentHealth = maxHealth;
                this.maxMagic = 0;
                this.currentMagic = maxMagic;
                this.strength = 2;
                this.willpower = 0;
                this.defense = 2;
                this.resistance = 0;
                this.speed = 6;
                this.name = "GOBLIN";
                this.level = 0;
                this.money = 0;
                this.spells = new ArrayList<Integer>();
                break;
            default:
                this.level = 0;
                this.money = 0;
                this.maxHealth = 0;
                this.currentHealth = 0;
                this.maxMagic = 0;
                this.currentMagic = 0;
                this.strength = 0;
                this.willpower = 0;
                this.defense = 0;
                this.resistance = 0;
                this.speed = 0;
                this.name = "";
                this.spells = new ArrayList<Integer>();
        }
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
    boolean isFoe(){ return true; }

    @Override
    void modifyHealth(int damage){
        this.currentHealth -= damage;
        if (currentHealth < 0) currentHealth = 0;
        if (currentHealth > maxHealth) currentHealth = maxHealth;
    }
    @Override
    void modifyMagic(int cost){
        this.currentMagic -= cost;
        if (currentMagic < 0) currentMagic = 0;
        if (currentMagic > maxMagic) currentMagic = maxMagic;
    }

    @Override
    int getMaxHealth() { return this.maxHealth; }
    @Override
    int getMaxMagic() { return this.maxMagic; }
    @Override
    int getHealth() { return this.currentHealth; }
    @Override
    int getMagic() { return this.currentMagic; }
    @Override
    int getStrength() { return this.strength; }
    @Override
    int getWillpower() { return this.willpower; }
    @Override
    int getDefense() { return this.defense; }
    @Override
    int getResistance() { return this.resistance; }
    @Override
    int getSpeed() { return this.speed; }
    @Override
    int getLevel() { return this.level; }
    @Override
    int getMoney() { return this.money; }
    @Override
    String getName() { return this.name; }
    @Override
    ArrayList<Integer> getSpells() { return this.spells; }
}
