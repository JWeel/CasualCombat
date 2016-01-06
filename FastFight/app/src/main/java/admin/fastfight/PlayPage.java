package admin.fastfight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        resizeButtons();

        // todo get pc and foe
        PlayerCharacter playerCharacter = new PlayerCharacter(PlayerCharacter.BRAWLER);
        ArrayList<Foe> foes = new ArrayList<>();
        foes.add(new Foe(Foe.GOBLIN));

        displayPlayer(playerCharacter);
        displayFoes(foes);
    }

    //
    private void resizeButtons(){
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (40 * scale + 0.5f);
        int screenWidth = (int) ((getResources().getDisplayMetrics().widthPixels - pixels) / 4.0);

        Button attackButton = (Button) findViewById(R.id.attackButton);
        attackButton.getLayoutParams().width = screenWidth;

        Button spellButton = (Button) findViewById(R.id.spellButton);
        spellButton.getLayoutParams().width = screenWidth;

        Button itemButton = (Button) findViewById(R.id.itemButton);
        itemButton.getLayoutParams().width = screenWidth;

        Button defendButton = (Button) findViewById(R.id.defendButton);
        defendButton.getLayoutParams().width = screenWidth;
    }

    //
    private void displayPlayer(Combatant playerCharacter){
        ImageView charIcon = (ImageView) findViewById(R.id.charIcon);

        TextView charName = (TextView) findViewById(R.id.charName);
        charName.setText(playerCharacter.getName());

        ImageView charHealth = (ImageView) findViewById(R.id.charHealth);
        ImageView charMagic = (ImageView) findViewById(R.id.charMagic);

        TextView charLevel = (TextView) findViewById(R.id.charLevel);
        charLevel.setText("LEVEL " + playerCharacter.getLevel());

        TextView charStrength = (TextView) findViewById(R.id.charStrength);
        charStrength.setText("STR " + playerCharacter.getStrength());

        TextView charDefense = (TextView) findViewById(R.id.charDefense);
        charDefense.setText("DEF " + playerCharacter.getDefense());

        TextView charSpeed = (TextView) findViewById(R.id.charSpeed);
        charSpeed.setText("SPD " + playerCharacter.getSpeed());
    }

    //
    private void displayFoes(ArrayList<Foe> foes){
        LinearLayout layout = (LinearLayout)findViewById(R.id.foeLayout);
        float weight = layout.getWeightSum() / foes.size();
        System.err.println("" + weight);

        // TODO continue here
    }

    //
    public void logClick(View logText){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activwaity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
