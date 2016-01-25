// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

import move.Move;

// usable item that hits only one foe
public class Dart extends UsableItem {

    public Dart(){
        this.id = Item.DART;
        this.itemMove = Move.findMoveById(Move.ITEM_DART);
        this.price = 2;
        this.name = "Dart";
        this.info = "Very sharp and very deadly";
    }
}
