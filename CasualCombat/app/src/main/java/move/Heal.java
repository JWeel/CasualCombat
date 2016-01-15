package move;

public class Heal extends Move {

    Heal(){
        this.id = 3;
        this.damage = -3;
        this.cost = 3;
        this.range = Move.RANGE_SELF;
    }

    public String getName(){
        return "Heal";
    }
    public String getInfo(){
        return "Restore some health at the cost of magic";
    }
}
