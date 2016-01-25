// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

// starter spell that can hit three targets
public class Fireball extends Move {

    Fireball(){
        this.id = Move.FIREBALL;
        this.damage = 1;
        this.cost = 2;
        this.range = Move.RANGE_CLOSE;
        this.name = "Fireball";
        this.info = "Conjure up a mighty ball of fire";
    }
}
