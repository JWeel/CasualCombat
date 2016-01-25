// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package move;

// used by Dart item, hits one target
public class ItemDart extends Move {

    ItemDart(){
        this.id = Move.ITEM_DART;
        this.damage = 5;
        this.cost = 0;
        this.range = Move.RANGE_SINGLE;
        this.name = "Dart";
        this.info = "";
    }
}
