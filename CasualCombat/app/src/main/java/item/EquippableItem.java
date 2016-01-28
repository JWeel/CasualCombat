// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

// equippable items give passive skill bonus to a player character
public abstract class EquippableItem extends Item {

    public static final int TYPE_WEAPON = 0;
    public static final int TYPE_ARMOR = 1;
    public static final int TYPE_BOOTS = 2;

    // a PlayerCharacter can only equip one item per type
    protected int type;

    protected int bonusStrength;
    protected int bonusWillpower;
    protected int bonusDefense;
    protected int bonusResistance;
    protected int bonusSpeed;

    public int getType() {
        return this.type;
    }

    public int getBonusStrength() {
        return this.bonusStrength;
    }
    public int getBonusWillpower() {
        return this.bonusWillpower;
    }
    public int getBonusDefense() {
        return this.bonusDefense;
    }
    public int getBonusResistance() {
        return this.bonusResistance;
    }
    public int getBonusSpeed() {
        return this.bonusSpeed;
    }

    // creates a string representation of the EquippableItem's skill bonus
    public String getStatBonusAsString(){
        switch(this.type){
            case TYPE_WEAPON:
                if (this.bonusStrength > this.bonusWillpower) return  "+" + this.bonusStrength + "STR";
                else return "+" + this.bonusWillpower + "WIL";
            case TYPE_ARMOR:
                if (this.bonusDefense > this.bonusResistance) return "+" + this.bonusDefense + "DEF";
                else return "+" + this.bonusResistance + "RES";
            case TYPE_BOOTS:
                return "+" + this.bonusSpeed + "SPD";
            default: return "";
        }
    }

    // override basic info to also supply skill bonus
    @Override
    public String getInfo() {
        switch(this.type){
            case TYPE_WEAPON:
                return "Weapon\n" + getStatBonusAsString();
            case TYPE_ARMOR:
                return "Armor\n" + getStatBonusAsString();
            case TYPE_BOOTS:
                return "Boots\n" + getStatBonusAsString();
            default: return "";
        }
    }

    // basic copy constructor
    public EquippableItem copy(){
        EquippableItem copiedItem = (EquippableItem) findItemById(this.id);
        copiedItem.type = this.type;
        copiedItem.bonusStrength = this.bonusStrength;
        copiedItem.bonusWillpower = this.bonusWillpower;
        copiedItem.bonusDefense = this.bonusDefense;
        copiedItem.bonusResistance = this.bonusResistance;
        copiedItem.bonusSpeed = this.bonusSpeed;
        return copiedItem;
    }
}
