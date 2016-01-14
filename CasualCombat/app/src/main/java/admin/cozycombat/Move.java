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

    static final int NO_TARGET = -2;
    static final int TARGET_SELF = -1;

    static final int RANGE_SELF = 0;
    static final int RANGE_SINGLE = 1;
    static final int RANGE_CLOSE = 2;
    static final int RANGE_FAR = 3;

    // structure is id, damage, cost, strength, willpower, defense, resistance, speed, range
    static final int[] BASIC_ATTACK     = {0, 1, 0, 0, 0, 0, 0, 0, RANGE_FAR};
    static final int[] BASIC_DEFEND     = {1, 0, 0, 0, 0, 1, 1, 0, RANGE_SELF};
    static final int[] FIREBALL         = {2, 4, 2, 0, 0, 0, 0, 0, RANGE_CLOSE};
    static final int[] HEAL             = {3, -3, 3, 0, 0, 0, 0, 0, RANGE_SELF};

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
        this.target = NO_TARGET;
    }

    boolean isSpell(){
        return this.id > 1;
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
