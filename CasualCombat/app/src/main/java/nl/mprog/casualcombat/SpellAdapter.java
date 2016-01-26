// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package nl.mprog.casualcombat;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import move.Move;

// this adapter is used to display spells nicely
class SpellAdapter extends ArrayAdapter<Integer> {

    // the player character stores only the ids of the spells, the actual spells are stored here
    private ArrayList<Move> usableSpells;

    public SpellAdapter(Context context, int resource, int textViewResourceId, ArrayList<Integer> moveIds){
        super(context, resource, textViewResourceId, moveIds);

        usableSpells = new ArrayList<>();
        for (int i = 0; i < moveIds.size(); i++) {
            usableSpells.add(Move.findMoveById(moveIds.get(i)));
        }
    }

    // updates the item list, setting spells that cost too much magic to null
    void updateUsableSpells(PlayerCharacter playerCharacter){
        usableSpells.clear();
        for (Integer spellId : playerCharacter.getSpells()){
            Move spell = Move.findMoveById(spellId);
            if (spell.getCost() > playerCharacter.getMagic()) usableSpells.add(null);
            else usableSpells.add(spell);
        }
        this.notifyDataSetChanged();
    }

    Move getListMove(int position){
        return usableSpells.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = super.getView(position, convertView, parent);

        Move move = usableSpells.get(position);
        // if move is null, that means there is not enough magic to cast it
        if (move == null){
            ((TextView) listItem.findViewById(R.id.listSpellInfo)).setText("INSUFFICIENT MAGIC");
            listItem.setBackgroundColor(Color.parseColor("#BB7788"));

            listItem.findViewById(R.id.listSpellName).setVisibility(View.INVISIBLE);
            listItem.findViewById(R.id.listSpellCost).setVisibility(View.INVISIBLE);
            listItem.findViewById(R.id.listSpellRange).setVisibility(View.INVISIBLE);

        }
        // if not null, just display information about the spell
        else {
            listItem.findViewById(R.id.listSpellName).setVisibility(View.VISIBLE);
            listItem.findViewById(R.id.listSpellCost).setVisibility(View.VISIBLE);
            listItem.findViewById(R.id.listSpellRange).setVisibility(View.VISIBLE);

            ((TextView) listItem.findViewById(R.id.listSpellName)).setText(move.getName());
            ((TextView) listItem.findViewById(R.id.listSpellInfo)).setText(move.getInfo());
            ((TextView) listItem.findViewById(R.id.listSpellCost)).setText("Cost " + move.getCost() + " MP");

            int drawableId = 0;
            switch (move.getRange()) {
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
            listItem.setBackgroundColor(Color.parseColor("#7788BB"));
        }
        return listItem;
    }
}
