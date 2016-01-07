package admin.fastfight;

public class Move {

    private int id;
    private int damage;
    private int cost;
    private int strength;
    private int defense;
    private int speed;
    private int range;

    // structure is id, damage, cost, strength, defense, speed, range
    static final int[] BASIC_ATTACK     = {0, 1, 0, 0, 0, 0, 1};
    static final int[] BASIC_DEFEND     = {1, 0, 0, 0, 1, 0, 0};
    static final int[] FIREBALL         = {2, 4, 2, 0, 0, 0, 3};
    static final int[] HEAL             = {3, -3, 3, 0, 0, 0, 0};

    Move(int[] moveArray){
        this.id = moveArray[0];
        this.damage = moveArray[1];
        this.cost = moveArray[2];
        this.strength = moveArray[3];
        this.defense = moveArray[4];
        this.speed = moveArray[5];
        this.range = moveArray[6];
    }

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
    int getDefense() {
        return this.defense;
    }
    int getSpeed() {
        return this.speed;
    }
    int getRange() {
        return this.range;
    }
}
