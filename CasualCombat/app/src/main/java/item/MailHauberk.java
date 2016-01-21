package item;

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
