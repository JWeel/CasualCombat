package move;

import item.Item;

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
    public static final int SHOCKWAVE        = 4;
    public static final int ITEM_BOMB        = Item.BOMB;
    public static final int ITEM_HERB        = Item.HERB;

    // TODO maybe only one index for BOMB and ITEM_BOMB

    public static Move findMoveById(int id){
        switch(id){
            case BASIC_ATTACK: return new BasicAttack();
            case BASIC_DEFEND: return new BasicDefend();
            case FIREBALL: return new Fireball();
            case HEAL: return new Heal();
            case SHOCKWAVE: return new Shockwave();
            case ITEM_BOMB: return new ItemBomb();
            case ITEM_HERB: return new ItemHerb();
            default: return null;
        }
    }

    protected int id;
    protected int damage;
    protected int cost;
    protected int range;
    protected int target = NO_TARGET;
    protected String name;
    protected String info;

    public boolean isSpell(){ return (this.id > BASIC_DEFEND && this.id < ITEM_BOMB); }
    public boolean isItemMove() { return this.id >= ITEM_BOMB; }

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

    public String getName(){ return this.name; }
    public String getInfo(){ return this.info; }

    // could implement prices for individual spells, but for now the price remains constant
    public int getPrice() { return 3; }

    @Override
    public String toString(){
        return name + "[DMG=" + damage + ",CST=" + cost + ",RNG=" + range + "TAR=" + target + "]";
    }
}
