package move;

public abstract class Move {

    public static final int NO_TARGET = -2;
    public static final int TARGET_SELF = -1;

    public static final int RANGE_SELF = 0;
    public static final int RANGE_SINGLE = 1;
    public static final int RANGE_CLOSE = 2;
    public static final int RANGE_FAR = 3;

    // structure is id, damage, cost, strength, willpower, defense, resistance, speed, range
    public static final int BASIC_ATTACK     = 0;
    public static final int BASIC_DEFEND     = 1;
    public static final int FIREBALL         = 2;
    public static final int HEAL             = 3;

    public static Move findMoveByID(int id){
        switch(id){
            case BASIC_ATTACK: return new BasicAttack();
            case BASIC_DEFEND: return new BasicDefend();
            case FIREBALL: return new Fireball();
            case HEAL: return new Heal();
            default: return null;
        }
    }

    protected int id;
    protected int damage;
    protected int cost;
    protected int range;
    protected int target = NO_TARGET;

    public abstract String getName();
    public abstract String getInfo();

    public boolean isSpell(){
        return getId() > BASIC_DEFEND;
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
    public int getRange() {
        return this.range;
    }

    @Override
    public String toString(){
        return getName() + "[DMG=" + damage + ",CST=" + cost + ",RNG=" + range + "TAR=" + target + "]";
    }
}
