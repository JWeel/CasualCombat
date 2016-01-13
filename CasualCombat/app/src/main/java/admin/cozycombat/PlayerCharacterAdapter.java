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

class PlayerCharacterAdapter extends ArrayAdapter<PlayerCharacter> {

    private ArrayList<PlayerCharacter> players;

    PlayerCharacterAdapter(Context context, int resource, int textViewResourceId, ArrayList<PlayerCharacter> items){
        super(context, resource, textViewResourceId, items);
        players = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = super.getView(position, convertView, parent);

        PlayerCharacter playerCharacter = players.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.listCharName);
        name.setText(playerCharacter.getName());

        ProgressBar health = (ProgressBar) listItem.findViewById(R.id.listCharHealth);
        health.setMax(playerCharacter.getMaxHealth());
        health.setProgress(playerCharacter.getHealth());
        health.getProgressDrawable().setColorFilter(Color.parseColor("#00AA00"), PorterDuff.Mode.SRC_IN);

        ProgressBar magic = (ProgressBar) listItem.findViewById(R.id.listCharMagic);
        magic.setMax(playerCharacter.getMaxHealth());
        magic.setProgress(playerCharacter.getHealth());
        magic.getProgressDrawable().setColorFilter(Color.parseColor("#0055DD"), PorterDuff.Mode.SRC_IN);

        TextView strength = (TextView) listItem.findViewById(R.id.listCharStrength);
        strength.setText("STR " + playerCharacter.getStrength());

        TextView willpower = (TextView) listItem.findViewById(R.id.listCharWillpower);
        willpower.setText("WIL " + playerCharacter.getWillpower());

        TextView defense = (TextView) listItem.findViewById(R.id.listCharDefense);
        defense.setText("DEF " + playerCharacter.getDefense());

        TextView resistance = (TextView) listItem.findViewById(R.id.listCharResistance);
        resistance.setText("RES " + playerCharacter.getResistance());

        TextView speed = (TextView) listItem.findViewById(R.id.listCharSpeed);
        speed.setText("SPD " + playerCharacter.getSpeed());

        TextView level = (TextView) listItem.findViewById(R.id.listCharLevel);
        level.setText("LVL " + playerCharacter.getLevel());

        return listItem;
    }
}
