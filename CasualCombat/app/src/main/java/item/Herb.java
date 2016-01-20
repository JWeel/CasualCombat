package item;

import move.Move;

public class Herb extends UsableItem {

    public Herb(){
        this.id = Item.HERB;
        this.itemMove = Move.findMoveById(Move.ITEM_HERB);
        this.price = 2;
        this.name = "Herb";
        this.info = "A bitter herb that restores 5 magic";
    }

}
