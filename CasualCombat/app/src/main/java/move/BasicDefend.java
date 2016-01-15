package move;

public class BasicDefend extends Move {

    BasicDefend(){
        this.id = 1;
        this.damage = 0;
        this.cost = 0;
        this.range = Move.RANGE_SELF;
    }

    public String getName(){
        return "Defend";
    }
    public String getInfo(){
        return "Improves defense and resistance by a scale of 2";
    }
}
