// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

import item.Item;

// moves are used by combatants in combat. various predefined subclasses tweak the move's effect
public abstract class Move {

    public static final int PRICE_SPELL = 3; // all spells cost 3 gold

    public static final int NO_TARGET = -2;
    public static final int TARGET_SELF = -1;

    public static final int RANGE_SELF = 0;
    public static final int RANGE_SINGLE = 1;
    public static final int RANGE_CLOSE = 2;
    public static final int RANGE_FAR = 3;

    public static final int BASIC_ATTACK    = 0;
    public static final int BASIC_DEFEND    = 1;
    public static final int FIREBALL        = 2;
    public static final int HEAL            = 3;
    public static final int SHOCK           = 4;
    public static final int TORNADO         = 5;
    public static final int ARCANE_BLAST    = 6;
    public static final int ITEM_BOMB       = Item.BOMB;
    public static final int ITEM_DART       = Item.DART;
    public static final int ITEM_HERB       = Item.HERB;

    // returns an instance of the corresponding move. when adding new moves, also add them here
    public static Move findMoveById(int id){
        switch(id){
            case BASIC_ATTACK: return new BasicAttack();
            case BASIC_DEFEND: return new BasicDefend();
            case FIREBALL: return new Fireball();
            case HEAL: return new Heal();
            case SHOCK: return new Shock();
            case TORNADO: return new Tornado();
            case ARCANE_BLAST: return new ArcaneBlast();
            case ITEM_BOMB: return new ItemBomb();
            case ITEM_DART: return new ItemDart();
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

    public int getPrice() { return PRICE_SPELL; }

    @Override
    public String toString(){
        return name + "[DMG=" + damage + ",CST=" + cost + ",RNG=" + range + "TAR=" + target + "]";
    }
}
