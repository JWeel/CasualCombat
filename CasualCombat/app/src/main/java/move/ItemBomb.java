// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

// used by Bomb item, hits multiple targets
public class ItemBomb extends Move {

    ItemBomb(){
        this.id = Move.ITEM_BOMB;
        this.damage = 4;
        this.cost = 0;
        this.range = Move.RANGE_CLOSE;
        this.name = "Bomb";
        this.info = "";
    }
}
