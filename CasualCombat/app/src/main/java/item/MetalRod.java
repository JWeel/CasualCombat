// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

// first tier willpower weapon
public class MetalRod extends EquippableItem {

    public MetalRod(){
        this.id = Item.METAL_ROD;
        this.type = EquippableItem.TYPE_WEAPON;
        this.bonusStrength = 0;
        this.bonusWillpower = 1;
        this.bonusDefense = 0;
        this.bonusResistance = 0;
        this.bonusSpeed = 0;
        this.price = 2;
        this.name = "Metal Rod";
    }
}
