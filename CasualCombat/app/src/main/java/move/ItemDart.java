package move;

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
