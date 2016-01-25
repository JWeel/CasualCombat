// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

// second tier boots
public class SweetKicks extends EquippableItem {

    public SweetKicks(){
        this.id = Item.SWEET_KICKS;
        this.type = EquippableItem.TYPE_BOOTS;
        this.bonusStrength = 0;
        this.bonusWillpower = 0;
        this.bonusDefense = 0;
        this.bonusResistance = 0;
        this.bonusSpeed = 3;
        this.price = 6;
        this.name = "Sweet Kicks";
    }
}
