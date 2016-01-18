package item;

public abstract class Item {
    public static final int WOODEN_SWORD = 0;
    public static final int HAUBERK = 10;
    public static final int SANDLE = 20;

    public static final int BOMB = 30;
    public static final int HERB = 40;

    protected int id;
    protected String name;

    public int getId(){ return this.id; }
    public String getName() { return this.name; }

    public abstract boolean isEquippable();

    public static Item findItemByID(int id){
        switch(id){
            case WOODEN_SWORD: return new WoodenSword();
            case BOMB: return new Bomb();
            case HERB: return new Herb();
            default: return null;
        }
    }
}
