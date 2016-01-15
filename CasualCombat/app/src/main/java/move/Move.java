package move;

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

    public static final int INDEX_ID = 0;
    public static final int INDEX_DAMAGE = 1;
    public static final int INDEX_COST = 2;
    public static final int INDEX_STRENGTH = 3;
    public static final int INDEX_WILLPOWER = 4;
    public static final int INDEX_DEFENSE = 5;
    public static final int INDEX_RESISTANCE = 6;
    public static final int INDEX_SPEED = 7;
    public static final int INDEX_RANGE = 8;

    public static final int NO_TARGET = -2;
    public static final int TARGET_SELF = -1;

    public static final int RANGE_SELF = 0;
    public static final int RANGE_SINGLE = 1;
    public static final int RANGE_CLOSE = 2;
    public static final int RANGE_FAR = 3;

    // structure is id, damage, cost, strength, willpower, defense, resistance, speed, range
    public static final int[] BASIC_ATTACK     = {0, 1, 0, 0, 0, 0, 0, 0, RANGE_FAR};
    public static final int[] BASIC_DEFEND     = {1, 0, 0, 0, 0, 1, 1, 0, RANGE_SELF};
    public static final int[] FIREBALL         = {2, 4, 2, 0, 0, 0, 0, 0, RANGE_CLOSE};
    public static final int[] HEAL             = {3, -3, 3, 0, 0, 0, 0, 0, RANGE_SELF};

    public static final String FIREBALL_       = "2,4,2,0,0,0,0,0,2";

    public Move(int[] moveArray){
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

    public Move(int idx, int dmg, int cst, int str, int wil, int def, int res, int spd, int rng, int tar){
        this.id = idx;
        this.damage = dmg;
        this.cost = cst;
        this.strength = str;
        this.willpower = wil;
        this.defense = def;
        this.resistance = res;
        this.speed = spd;
        this.range = rng;
        this.target = tar;
    }

    //
    public String getName(){
        switch(this.id){
            case 2:
                return "Fireball";
            case 3:
                return "Heal";
            default:
                return "";
        }
    }

    //
    public String getInfo(){
        switch(this.id){
            case 2:
                return "Conjure up a mighty ball of fire.";
            case 3:
                return "Restore some health at the cost of magic.";
            default:
                return "";
        }
    }

    public boolean isSpell(){
        return this.id > 1;
    }

    public void setTarget(int t) { this.target = t; }
    public int getTarget() { return this.target; }

    public int getId() {
        return this.id;
    }
    public int getDamage() {
        return this.damage;
    }
    public int getCost() {
        return this.cost;
    }
    public int getStrength() {
        return this.strength;
    }
    public int getWillpower() {
        return this.willpower;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getResistance() {
        return this.resistance;
    }
    public int getSpeed() {
        return this.speed;
    }
    public int getRange() {
        return this.range;
    }

    @Override
    public String toString(){
        return getName() + "[DMG=" + damage + ",CST=" + cost + ",RNG=" + range + "TAR=" + target + "]";
    }
}
