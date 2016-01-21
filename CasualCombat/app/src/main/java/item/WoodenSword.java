package item;

public class WoodenSword extends EquippableItem {

    public WoodenSword(){
        this.id = Item.WOODEN_SWORD;
        this.type = EquippableItem.TYPE_WEAPON;
        this.bonusStrength = 1;
        this.bonusWillpower = 0;
        this.bonusDefense = 0;
        this.bonusResistance = 0;
        this.bonusSpeed = 0;
        this.price = 2;
        this.name = "Wooden Sword";
    }
}
