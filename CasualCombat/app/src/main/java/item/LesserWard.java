package item;

public class LesserWard extends EquippableItem {

    public LesserWard(){
        this.id = Item.LESSER_WARD;
        this.type = EquippableItem.TYPE_ARMOR;
        this.bonusStrength = 0;
        this.bonusWillpower = 0;
        this.bonusDefense = 0;
        this.bonusResistance = 2;
        this.bonusSpeed = 0;
        this.price = 3;
        this.name = "Lesser Ward";
    }
}
