// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

// spell that can hit five targets
public class Tornado extends Move {

    Tornado(){
        this.id = Move.TORNADO;
        this.damage = 2;
        this.cost = 4;
        this.range = Move.RANGE_FAR;
        this.name = "Tornado";
        this.info = "Summon the power of the wind";
    }
}
