// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

import move.Move;

// usable item that restores magic
public class Herb extends UsableItem {

    public Herb(){
        this.id = Item.HERB;
        this.itemMove = Move.findMoveById(Move.ITEM_HERB);
        this.price = 2;
        this.name = "Herb";
        this.info = "A bitter herb that restores 5 magic";
    }

}
