package move;

public class BasicAttack extends Move {

    BasicAttack(){
        this.id = 0;
        this.damage = 1;
        this.cost = 0;
        this.range = Move.RANGE_SINGLE;
    }

    public String getName(){
        return "Attack";
    }
    public String getInfo(){
        return "A basic attack that scales with Strength";
    }
}
