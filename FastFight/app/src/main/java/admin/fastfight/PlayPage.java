package admin.fastfight;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayPage extends AppCompatActivity {

    // TODO
    // click spell button brings up scrollable list over item and defend
    // click item does same but over attack and spell
    // once attack selected (or spell/item chosen), other three become greyed out, choose enemy
    //

    private boolean selectingFoe = false;
    private boolean selectingMove = false;
    private Move currentMove = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        resizeButtons();
        prepareLists();

        // todo get pc and foe
        PlayerCharacter playerCharacter = new PlayerCharacter(PlayerCharacter.BRAWLER);
        ArrayList<Foe> foes = new ArrayList<>();
        foes.add(new Foe(Foe.GOBLIN));
        foes.add(new Foe(Foe.GOBLIN));

        displayPlayer(playerCharacter);
        displayFoes(foes);
    }

    //
    private void resizeButtons(){
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (40 * scale + 0.5f);
        int quarterScreenWidth = (int) ((getResources().getDisplayMetrics().widthPixels - pixels) / 4.0);

        Button attackButton = (Button) findViewById(R.id.attackButton);
        attackButton.getLayoutParams().width = quarterScreenWidth;

        Button spellButton = (Button) findViewById(R.id.spellButton);
        spellButton.getLayoutParams().width = quarterScreenWidth;

        Button itemButton = (Button) findViewById(R.id.itemButton);
        itemButton.getLayoutParams().width = quarterScreenWidth;

        Button defendButton = (Button) findViewById(R.id.defendButton);
        defendButton.getLayoutParams().width = quarterScreenWidth;
    }

    //
    private void prepareLists()    {
        ListView spellListView = (ListView) findViewById(R.id.listSpells);
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (40 * scale + 0.5f);

        int halfScreenWidth = (int) ((getResources().getDisplayMetrics().widthPixels - pixels) / 2.0);
        int topMargin = (int)(getResources().getDisplayMetrics().heightPixels / 30.0 * scale + 0.5f);
        int height = (int)(getResources().getDisplayMetrics().heightPixels / 6.0 * scale + 0.5f) * 3;

        int test = (int) (22 * scale + 0.5f);
        RelativeLayout.LayoutParams spellParams = new RelativeLayout.LayoutParams(halfScreenWidth, height);
        spellParams.setMargins(24, topMargin, 24, 0);
        spellParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        spellListView.setLayoutParams(spellParams);

        ListView itemListView = (ListView) findViewById(R.id.listItems);
        RelativeLayout.LayoutParams itemParams = new RelativeLayout.LayoutParams(halfScreenWidth, height);
        itemParams.setMargins(24, topMargin, 24, 0);
        itemParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        itemListView.setLayoutParams(itemParams);
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
        float equalWeight = layout.getWeightSum() / foes.size();

        for (int i = 0; i < foes.size(); i++){
            TextView foeTextView = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(2,0,2,0);
            params.weight = equalWeight;
            foeTextView.setLayoutParams(params);
            foeTextView.setText(foes.get(i).getName());
            foeTextView.setBackgroundColor(Color.parseColor("#345678"));
            foeTextView.setGravity(Gravity.CENTER);
            layout.addView(foeTextView);
        }
    }

    //
    public void attackClick(View attackButton){
        // set that now enemy can be selected
        selectingFoe = true;
        currentMove = new Move(Move.BASIC_ATTACK);
        //
        // set views of other moves to disable (greyed out)
        // also requires a cancel button...............................
        // although can use the back button for that maybe
    }

    //
    public void spellClick(View spellButton){
        // select spell
        selectingMove = true;
    }

    //
    public void itemClick(View itemButton){
        selectingMove = true;
    }

    //
    public void defendClick(View defendButton){
        currentMove = new Move(Move.BASIC_DEFEND);
        // prepare log?
    }

    //
    public void logClick(View logText){
        // handle move

        // write string

    }

    //
    @Override
    public void onBackPressed(){
        if (selectingFoe) {
            // TODO maybe also allow this action by pressing the button again
            selectingFoe = false;
            currentMove = null;
            // enable other buttons again
        } else if (selectingMove) {
            // TODO maybe also allow this action by pressing the button again
            selectingMove = false;
            // remove list, enable buttons
        } else {
            finish();
        }
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
