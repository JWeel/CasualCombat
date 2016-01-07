package admin.cozycombat;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MoveAdapter extends ArrayAdapter<Move> {
    private ArrayList<Move> moves;

    public MoveAdapter(Context context, int resource, int textViewResourceId, ArrayList<Move> newMoves){
        super(context, resource, textViewResourceId, newMoves);
        moves = newMoves;
    }

    // override getview
}
