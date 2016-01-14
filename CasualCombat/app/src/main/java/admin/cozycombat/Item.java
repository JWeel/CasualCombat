package admin.cozycombat;

class Item {
    public static final int WEAPON_WOODEN_SWORD = 0;
    public static final int WEAPON_SIMPLE_BOW = 1;
    public static final int WEAPON_STICK = 2;
    public static final int ARMOR_MAIL = 100;
    public static final int ARMOR_LEATHER = 101;
    public static final int ARMOR_ROBE = 102;
    public static final int BOOTS_IRON = 200;
    public static final int BOOTS_LEATHER = 201;
    public static final int BOOTS_SANDLE = 202;

    //                                              idx str wil def res spd
    static final int[] _WEAPON_WOODEN_SWORD     = { 0,  2,  0,  0,  0,  0 };

    private int[] table;

    private int bonusStrength;
    private int bonusWillpower;
    private int bonusDefense;
    private int bonusResistance;
    private int bonusSpeed;

    Item(int id){
        table = new int[6];
    }

    int getBonusStrength(){
        return this.table[1];
    }
    int getBonusWillpower(){
        return this.table[2];
    }
    int getBonusDefense(){
        return this.table[3];
    }
    int getBonusResistance(){
        return this.table[4];
    }
    int getBonusSpeed(){
        return this.table[5];
    }
}
