package move;

public class Fireball extends Move {

    Fireball(){
        this.id = Move.FIREBALL;
        this.damage = 1;
        this.cost = 2;
        this.range = Move.RANGE_CLOSE;
        this.name = "Fireball";
        this.info = "Conjure up a mighty ball of fire";
    }
}
