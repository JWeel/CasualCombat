package move;

public class ItemHerb extends Move {

    ItemHerb(){
        this.id = Move.ITEM_HERB;
        this.damage = 0;
        this.cost = -5;
        this.range = Move.RANGE_SELF;
        this.name = "";
        this.info = "";
    }
}
