// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package nl.mprog.casualcombat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

// this adapter is used to display player characters nicely
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

        ImageView avatar = (ImageView) listItem.findViewById(R.id.listCharIcon);
        Drawable d = ContextCompat.getDrawable(listItem.getContext(), R.drawable.avatar);
        d.mutate().setColorFilter(Color.parseColor(playerCharacter.getColorString()), PorterDuff.Mode.MULTIPLY);
        avatar.setImageDrawable(d);

        TextView name = (TextView) listItem.findViewById(R.id.listCharName);
        name.setText(playerCharacter.getName());

        ProgressBar health = (ProgressBar) listItem.findViewById(R.id.listCharHealth);
        health.setMax(playerCharacter.getMaxHealth());
        health.setProgress(playerCharacter.getHealth());

        ProgressBar magic = (ProgressBar) listItem.findViewById(R.id.listCharMagic);
        magic.setMax(playerCharacter.getMaxMagic());
        magic.setProgress(playerCharacter.getMagic());

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
