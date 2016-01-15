package move;

public class Fireball extends Move {

    Fireball(){
        this.id = 2;
        this.damage = 1;
        this.cost = 2;
        this.range = Move.RANGE_CLOSE;
    }

    public String getName(){
        return "Fireball";
    }
    public String getInfo(){
        return "Conjure up a mighty ball of fire";
    }
}
