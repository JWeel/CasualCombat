package move;

public class Shock extends Move {

    Shock(){
        this.id = Move.SHOCK;
        this.damage = 3;
        this.cost = 2;
        this.range = Move.RANGE_SINGLE;
        this.name = "Shock";
        this.info = "Electrify a single opponent";
    }
}
