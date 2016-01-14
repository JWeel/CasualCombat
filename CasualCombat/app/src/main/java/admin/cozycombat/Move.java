package admin.cozycombat;

import android.text.style.RelativeSizeSpan;

public class Move {

    private int id;
    private int damage;
    private int cost;
    private int strength;
    private int willpower;
    private int defense;
    private int resistance;
    private int speed;
    private int range;

    private int target;

    static final int INDEX_ID = 0;
    static final int INDEX_DAMAGE = 1;
    static final int INDEX_COST = 2;
    static final int INDEX_STRENGTH = 3;
    static final int INDEX_WILLPOWER = 4;
    static final int INDEX_DEFENSE = 5;
    static final int INDEX_RESISTANCE = 6;
    static final int INDEX_SPEED = 7;
    static final int INDEX_RANGE = 8;

    // structure is id, damage, cost, strength, willpower, defense, resistance, speed, range
    static final int[] BASIC_ATTACK     = {0, 1, 0, 0, 0, 0, 0, 0, 1};
    static final int[] BASIC_DEFEND     = {1, 0, 0, 0, 0, 1, 1, 0, 0};
    static final int[] FIREBALL         = {2, 4, 2, 0, 0, 0, 0, 0, 3};
    static final int[] HEAL             = {3, -3, 3, 0, 0, 0, 0, 0, 0};

    Move(int[] moveArray){
        this.id = moveArray[INDEX_ID];
        this.damage = moveArray[INDEX_DAMAGE];
        this.cost = moveArray[INDEX_COST];
        this.strength = moveArray[INDEX_STRENGTH];
        this.willpower = moveArray[INDEX_WILLPOWER];
        this.defense = moveArray[INDEX_DEFENSE];
        this.resistance = moveArray[INDEX_RESISTANCE];
        this.speed = moveArray[INDEX_SPEED];
        this.range = moveArray[INDEX_RANGE];
        this.target = PlayerCharacter.NO_TARGET;
    }

    boolean isSpell(){
        return this.id != BASIC_ATTACK[INDEX_ID];
    }

    void setTarget(int t) { this.target = t; }
    int getTarget() { return this.target; }

    int getId() {
        return this.id;
    }
    int getDamage() {
        return this.damage;
    }
    int getCost() {
        return this.cost;
    }
    int getStrength() {
        return this.strength;
    }
    int getWillpower() {
        return this.willpower;
    }
    int getDefense() {
        return this.defense;
    }
    int getResistance() {
        return this.resistance;
    }
    int getSpeed() {
        return this.speed;
    }
    int getRange() {
        return this.range;
    }
}
