package admin.cozycombat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        //TextView nameView = (TextView) listItem.findViewById(R.id.list_name);

        return listItem;
    }
}
