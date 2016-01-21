package item;

import move.Move;

public class Bomb extends UsableItem {

    public Bomb(){
        this.id = Item.BOMB;
        this.itemMove = Move.findMoveById(Move.ITEM_BOMB);
        this.price = 3;
        this.name = "Bomb";
        this.info = "A small bomb that explodes on impact";
    }
}
