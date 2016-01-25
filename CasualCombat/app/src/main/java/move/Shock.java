// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

// early-game spell that hits one target
public class Shock extends Move {

    Shock(){
        this.id = Move.SHOCK;
        this.damage = 3;
        this.cost = 2;
        this.range = Move.RANGE_SINGLE;
        this.name = "Shock";
        this.info = "Electrify a single opponent";
    }
}
