package move;

public class Shockwave extends Move {

    Shockwave(){
        this.id = Move.SHOCKWAVE;
        this.damage = 3;
        this.cost = 2;
        this.range = Move.RANGE_SINGLE;
        this.name = "Shockwave";
        this.info = "Electrify a single opponent";
    }
}
