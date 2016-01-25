package item;

public class Gladius extends EquippableItem {

    public Gladius(){
        this.id = Item.GLADIUS;
        this.type = EquippableItem.TYPE_WEAPON;
        this.bonusStrength = 4;
        this.bonusWillpower = 0;
        this.bonusDefense = 0;
        this.bonusResistance = 0;
        this.bonusSpeed = 0;
        this.price = 7;
        this.name = "Gladius";
    }
}
