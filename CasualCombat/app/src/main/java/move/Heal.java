package move;

public class Heal extends Move {

    Heal(){
        this.id = 3;
        this.damage = -3;
        this.cost = 3;
        this.range = Move.RANGE_SELF;
    }

    public String getName(){
        return "Fireball";
    }
    public String getInfo(){
        return "Conjure up a mighty ball of fire";
    }
}
