// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

// basic attack used by every combatant
public class BasicAttack extends Move {

    BasicAttack(){
        this.id = Move.BASIC_ATTACK;
        this.damage = 1;
        this.cost = 0;
        this.range = Move.RANGE_SINGLE;
        this.name = "Attack";
        this.info = "A basic attack that scales with Strength";
    }
}
