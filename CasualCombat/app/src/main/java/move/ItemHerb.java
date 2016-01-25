// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

// used by Herb item, restores magic
public class ItemHerb extends Move {

    ItemHerb(){
        this.id = Move.ITEM_HERB;
        this.damage = 0;
        this.cost = -5;
        this.range = Move.RANGE_SELF;
        this.name = "Herb";
        this.info = "";
    }
}
