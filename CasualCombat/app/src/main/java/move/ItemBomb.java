package move;

public class ItemBomb extends Move {

    ItemBomb(){
        this.id = Move.ITEM_BOMB;
        this.damage = 3;
        this.cost = 0;
        this.range = Move.RANGE_CLOSE;
        this.name = "Bomb";
        this.info = "";
    }
}
