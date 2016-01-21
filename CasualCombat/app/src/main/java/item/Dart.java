package item;

import move.Move;

public class Dart extends UsableItem {

    public Dart(){
        this.id = Item.DART;
        this.itemMove = Move.findMoveById(Move.ITEM_DART);
        this.price = 2;
        this.name = "Dart";
        this.info = "Very sharp and very deadly";
    }
}
