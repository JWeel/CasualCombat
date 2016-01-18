package admin.cozycombat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import move.Move;

class SpellAdapter extends ArrayAdapter<Integer> {
    private ArrayList<Move> usableSpells;

    public SpellAdapter(Context context, int resource, int textViewResourceId, ArrayList<Integer> moveIds){
        super(context, resource, textViewResourceId, moveIds);

        usableSpells = new ArrayList<>();
        for (int i = 0; i < moveIds.size(); i++) {
            usableSpells.add(Move.findMoveByID(moveIds.get(i)));
        }
    }

    //
    Move getListMove(int position){
        return usableSpells.get(position);
    }

    //
    void updateUsableSpells(PlayerCharacter playerCharacter){
        usableSpells.clear();
        for (Integer spellId : playerCharacter.getSpells()){
            Move spell = Move.findMoveByID(spellId);
            if (spell.getCost() > playerCharacter.getMagic()) usableSpells.add(null);
            else usableSpells.add(spell);
        }
        this.notifyDataSetChanged();
    }

    //
    boolean noUsableSpells(){
        for (Move spell : usableSpells) if (spell != null) return false;
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = super.getView(position, convertView, parent);

        Move move = usableSpells.get(position);
        if (move == null){
            listItem.setVisibility(View.GONE);
            return listItem;
        }


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

        listItem.setVisibility(View.VISIBLE);
        return listItem;
    }
}
