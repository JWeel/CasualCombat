// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

// items exist as two types - equippable and usable, and are held by player character in combat
public abstract class Item {

    public static final int WOODEN_SWORD = 0;
    public static final int METAL_ROD = 1;
    public static final int GLADIUS = 3;
    public static final int FINE_SCEPTER = 4;

    public static final int MAIL_HAUBERK = 10;
    public static final int LESSER_WARD = 11;
    public static final int PLATE_COAT = 12;
    public static final int GREATER_WARD = 13;

    public static final int SANDLES = 20;
    public static final int SWEET_KICKS = 21;

    public static final int BOMB = 30;
    public static final int DART = 31;
    public static final int HERB = 40;

    protected int id;
    protected int price;
    protected String name;

    public int getId(){ return this.id; }
    public int getPrice(){ return this.price; }
    public String getName() { return this.name; }

    // returns an instance of the corresponding item. when adding new items, also add them here
    public static Item findItemById(int id){
        switch(id){
            case WOODEN_SWORD: return new WoodenSword();
            case METAL_ROD: return new MetalRod();
            case GLADIUS: return new Gladius();
            case FINE_SCEPTER: return new FineScepter();
            case MAIL_HAUBERK: return new MailHauberk();
            case LESSER_WARD: return new LesserWard();
            case PLATE_COAT: return new PlateCoat();
            case GREATER_WARD: return new GreaterWard();
            case SANDLES: return new Sandles();
            case SWEET_KICKS: return new SweetKicks();
            case BOMB: return new Bomb();
            case DART: return new Dart();
            case HERB: return new Herb();
            default: return null;
        }
    }

    public abstract String getInfo();
}
