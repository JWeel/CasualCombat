package admin.cozycombat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

// TODO consider renaming to spell
class MoveAdapter extends ArrayAdapter<Move> {
    private ArrayList<Move> moves;

    public MoveAdapter(Context context, int resource, int textViewResourceId, ArrayList<Move> newMoves){
        super(context, resource, textViewResourceId, newMoves);
        moves = newMoves;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = super.getView(position, convertView, parent);

        Move move = moves.get(position);

        ((TextView) listItem.findViewById(R.id.listSpellName)).setText(move.getName());
        ((TextView) listItem.findViewById(R.id.listSpellInfo)).setText(move.getInfo());
        ((TextView) listItem.findViewById(R.id.listSpellCost)).setText("Cost " + move.getCost() + " MP");

        int drawableId = 0;
        switch(move.getRange()){
            case Move.RANGE_SELF:
                drawableId = R.drawable.range_self;
                break;
            case Move.RANGE_SINGLE:
                drawableId = R.drawable.range_single;
                break;
            case Move.RANGE_CLOSE:
                drawableId = R.drawable.range_close;
                break;
            case Move.RANGE_FAR:
                drawableId = R.drawable.range_far;
                break;
        }
        listItem.findViewById(R.id.listSpellRange).setBackgroundResource(drawableId);

        return listItem;
    }
}
