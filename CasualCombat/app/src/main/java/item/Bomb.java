// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package item;

import move.Move;

// usable item that hits multiple foes
public class Bomb extends UsableItem {

    public Bomb(){
        this.id = Item.BOMB;
        this.itemMove = Move.findMoveById(Move.ITEM_BOMB);
        this.price = 3;
        this.name = "Bomb";
        this.info = "A small bomb that explodes on impact";
    }
}
