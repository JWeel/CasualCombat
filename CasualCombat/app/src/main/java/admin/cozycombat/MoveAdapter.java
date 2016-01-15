package admin.cozycombat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import move.Move;

// TODO consider renaming to spell
class MoveAdapter extends ArrayAdapter<Integer> {
    private ArrayList<Move> moves;

    public MoveAdapter(Context context, int resource, int textViewResourceId, ArrayList<Integer> moveIds){
        super(context, resource, textViewResourceId, moveIds);

        moves = new ArrayList<>();
        for (int i = 0; i < moveIds.size(); i++) {
            moves.add(Move.findMoveByID(moveIds.get(i)));
        }
    }

    Move getListMove(int position){
        return moves.get(position);
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
