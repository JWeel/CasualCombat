// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

// spell that can restore health
public class Heal extends Move {

    Heal(){
        this.id = Move.HEAL;
        this.damage = -5;
        this.cost = 3;
        this.range = Move.RANGE_SELF;
        this.name = "Heal";
        this.info = "Restore some health at the cost of magic";
    }
}
