package move;

public class BasicDefend extends Move {

    BasicDefend(){
        this.id = Move.BASIC_DEFEND;
        this.damage = 0;
        this.cost = 0;
        this.range = Move.RANGE_SELF;
        this.name = "Defend";
        this.info = "Improves defense and resistance by a scale of 2";
    }
}
