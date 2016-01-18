package item;

public class WoodenSword extends EquippableItem {

    public WoodenSword(){
        this.type = EquippableItem.TYPE_WEAPON;
        this.bonusStrength = 1;
        this.bonusWillpower = 0;
        this.bonusDefense = 0;
        this.bonusResistance = 0;
        this.bonusSpeed = 0;
        this.price = 1;
        this.name = "Wooden Sword";
    }
}
