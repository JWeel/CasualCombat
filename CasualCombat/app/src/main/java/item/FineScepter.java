package item;

public class FineScepter extends EquippableItem {

    public FineScepter(){
        this.id = Item.FINE_SCEPTER;
        this.type = EquippableItem.TYPE_WEAPON;
        this.bonusStrength = 0;
        this.bonusWillpower = 4;
        this.bonusDefense = 0;
        this.bonusResistance = 0;
        this.bonusSpeed = 0;
        this.price = 6;
        this.name = "Fine Scepter";
    }
}
