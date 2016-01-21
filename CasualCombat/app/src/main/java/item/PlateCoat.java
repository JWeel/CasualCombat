package item;

public class PlateCoat extends EquippableItem {

    public PlateCoat(){
        this.id = Item.PLATE_COAT;
        this.type = EquippableItem.TYPE_ARMOR;
        this.bonusStrength = 0;
        this.bonusWillpower = 0;
        this.bonusDefense = 5;
        this.bonusResistance = 0;
        this.bonusSpeed = 0;
        this.price = 10;
        this.name = "Plate Coat";
    }
}
