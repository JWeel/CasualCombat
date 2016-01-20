package move;

public class Heal extends Move {

    Heal(){
        this.id = Move.HEAL;
        this.damage = -4;
        this.cost = 3;
        this.range = Move.RANGE_SELF;
        this.name = "Heal";
        this.info = "Restore some health at the cost of magic";
    }
}
