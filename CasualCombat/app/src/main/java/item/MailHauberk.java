// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

// first tier armor for defense
public class MailHauberk extends EquippableItem {

    public MailHauberk(){
        this.id = Item.MAIL_HAUBERK;
        this.type = EquippableItem.TYPE_ARMOR;
        this.bonusStrength = 0;
        this.bonusWillpower = 0;
        this.bonusDefense = 2;
        this.bonusResistance = 0;
        this.bonusSpeed = 0;
        this.price = 4;
        this.name = "Mail Hauberk";
    }
}
