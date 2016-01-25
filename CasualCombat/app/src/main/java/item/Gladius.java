// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

// second tier strength weapon
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
