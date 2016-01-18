package item;

public abstract class Item {
    public static final int WOODEN_SWORD = 0;
    public static final int HAUBERK = 10;
    public static final int SANDLE = 20;

    public static final int BOMB = 30;
    public static final int HERB = 40;

    protected int id;
    protected int price;
    protected String name;

    public int getId(){ return this.id; }
    public int getPrice(){ return this.price; }
    public String getName() { return this.name; }

    public abstract String getInfo();
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
