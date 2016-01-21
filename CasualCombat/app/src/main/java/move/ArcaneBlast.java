package move;

public class ArcaneBlast extends Move {

    ArcaneBlast(){
        this.id = Move.ARCANE_BLAST;
        this.damage = 5;
        this.cost = 6;
        this.range = Move.RANGE_CLOSE;
        this.name = "Arcane Blast";
        this.info = "Blast enemies with pure energy";
    }
}
