// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

// powerful late-game spell
public class ArcaneBlast extends Move {

    ArcaneBlast(){
        this.id = Move.ARCANE_BLAST;
        this.damage = 5;
        this.cost = 6;
        this.range = Move.RANGE_CLOSE;
        this.name = "Arcane Blast";
        this.info = "Blast enemies with pure energy";
    }
}
