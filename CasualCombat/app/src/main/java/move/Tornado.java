package move;

public class Tornado extends Move {

    Tornado(){
        this.id = Move.TORNADO;
        this.damage = 2;
        this.cost = 4;
        this.range = Move.RANGE_FAR;
        this.name = "Tornado";
        this.info = "Summon the power of the wind";
    }
}
