package item;

public class GreaterWard extends EquippableItem {

    public GreaterWard(){
        this.id = Item.GREATER_WARD;
        this.type = EquippableItem.TYPE_ARMOR;
        this.bonusStrength = 0;
        this.bonusWillpower = 0;
        this.bonusDefense = 0;
        this.bonusResistance = 3;
        this.bonusSpeed = 0;
        this.price = 8;
        this.name = "Greater Ward";
    }
}
