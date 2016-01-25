// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

// first tier boots
public class Sandles extends EquippableItem {

    public Sandles(){
        this.id = Item.SANDLES;
        this.type = EquippableItem.TYPE_BOOTS;
        this.bonusStrength = 0;
        this.bonusWillpower = 0;
        this.bonusDefense = 0;
        this.bonusResistance = 0;
        this.bonusSpeed = 1;
        this.price = 2;
        this.name = "Sandles";
    }
}
