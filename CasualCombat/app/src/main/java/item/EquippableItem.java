package item;

public abstract class EquippableItem extends Item {

    public static final int TYPE_WEAPON = 0;
    public static final int TYPE_ARMOR = 1;
    public static final int TYPE_BOOTS = 2;

    protected int type;

    protected int bonusStrength;
    protected int bonusWillpower;
    protected int bonusDefense;
    protected int bonusResistance;
    protected int bonusSpeed;

    public int getType() { return this.type; }

    public int getBonusStrength() { return this.bonusStrength; }
    public int getBonusWillpower() { return this.bonusWillpower; }
    public int getBonusDefense() { return this.bonusDefense; }
    public int getBonusResistance() { return this.bonusResistance; }
    public int getBonusSpeed() { return this.bonusSpeed; }

    @Override
    public boolean isEquippable() { return true; }
}
