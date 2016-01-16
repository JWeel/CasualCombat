package admin.cozycombat;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import foe.Foe;
import move.Move;

public class PlayPage extends AppCompatActivity {

    // TODO
    // click item does same but over attack and spell
    //
    // cant cast spell if not enough magic

    private static final int BUTTON_INDEX_ALL = 0;
    private static final int BUTTON_INDEX_ATTACK = 1;
    private static final int BUTTON_INDEX_SPELL = 2;
    private static final int BUTTON_INDEX_ITEM = 3;
    private static final int BUTTON_INDEX_DEFEND = 4;

    private boolean selectingFoe = false;
    private boolean selectingMove = false;

    private LinkedList<String> log;
    private ArrayList<TextView> foeTextViews;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);


        Intent previousPage = getIntent();
        PlayerCharacter playerCharacter = previousPage.getParcelableExtra(TitlePage.KEY_PLAYER);

        game = new Game(playerCharacter);

        updatePlayerValues(game.getPlayerCharacter());
        displayFoes(game.getFoes());

        resizeButtons();
        prepareLists();

        log = new LinkedList<>();

        findViewById(R.id.logNotify).setVisibility(View.INVISIBLE);

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
    private void prepareLists() {
        ListView spellListView = (ListView) findViewById(R.id.listSpells);
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (40 * scale + 0.5f);

        int halfScreenWidth = (int) ((getResources().getDisplayMetrics().widthPixels - pixels) / 2.0);
        int topMargin = (int)(getResources().getDisplayMetrics().heightPixels / 16.0 * scale + 0.5f);
        int height = (int)(getResources().getDisplayMetrics().heightPixels / 7.0 * scale + 0.5f) * 3;

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

        final MoveAdapter adapter = new MoveAdapter(this, R.layout.spell_list, R.id.listSpellName, new ArrayList<>(game.getPlayerCharacter().getSpells()));
        spellListView.setAdapter(adapter);
        spellListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // check have enough magic

                Move move = adapter.getListMove(position);
                game.pickMove(move);

                selectingMove = false;

                if (move.getRange() == Move.RANGE_SELF) {
                    game.pickTarget(Move.TARGET_SELF);
                    disableMoveButons(BUTTON_INDEX_ALL);
                    findViewById(R.id.logNotify).setVisibility(View.VISIBLE);
                } else {
                    selectingFoe = true;
                    disableMoveButons(BUTTON_INDEX_SPELL);
                }
                findViewById(R.id.listSpells).setVisibility(View.INVISIBLE);
            }
        });

        spellListView.setVisibility(View.INVISIBLE);
        itemListView.setVisibility(View.INVISIBLE);
    }

    //
    private void updatePlayerValues(PlayerCharacter playerCharacter){
        ImageView charIcon = (ImageView) findViewById(R.id.charIcon);

        ProgressBar charHealth = (ProgressBar) findViewById(R.id.charHealth);
        charHealth.setMax(game.getPlayerCharacter().getMaxHealth());
        charHealth.setProgress(game.getPlayerCharacter().getHealth());

        ProgressBar charMagic = (ProgressBar) findViewById(R.id.charMagic);
        charMagic.setMax(game.getPlayerCharacter().getMaxMagic());
        charMagic.setProgress(game.getPlayerCharacter().getMagic());

        ((TextView) findViewById(R.id.charName)).setText(playerCharacter.getName());
        ((TextView) findViewById(R.id.charHealthText)).setText("" + playerCharacter.getHealth());
        ((TextView) findViewById(R.id.charMagicText)).setText("" + playerCharacter.getMagic());
        ((TextView) findViewById(R.id.charLevel)).setText("LVL " + playerCharacter.getLevel());
        ((TextView) findViewById(R.id.charStrength)).setText("STR " + playerCharacter.getStrength());
        ((TextView) findViewById(R.id.charWillpower)).setText("WIL " + playerCharacter.getStrength());
        ((TextView) findViewById(R.id.charDefense)).setText("DEF " + playerCharacter.getDefense());
        ((TextView) findViewById(R.id.charResistance)).setText("RES " + playerCharacter.getDefense());
        ((TextView) findViewById(R.id.charSpeed)).setText("SPD " + playerCharacter.getSpeed());
    }

    //
    private void displayFoes(ArrayList<Foe> foes){
        LinearLayout layout = (LinearLayout)findViewById(R.id.foeLayout);
        float equalWeight = layout.getWeightSum() / foes.size();
        foeTextViews = new ArrayList<>();

        for (int i = 0; i < foes.size(); i++){
            TextView foeTextView = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(2,0,2,0);
            params.weight = equalWeight;
            foeTextView.setLayoutParams(params);
            foeTextView.setText(foes.get(i).getName() + "\n" + foes.get(i).getHealth());
            foeTextView.setBackgroundColor(Color.parseColor(foes.get(i).getColor()));
            foeTextView.setGravity(Gravity.CENTER);
            foeTextView.setTextColor(Color.parseColor("#FFFFFF"));

            final int targetId = i;
            foeTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectingFoe) {
                        if (game.getFoes().get(targetId).isDead()) {
                            // TODO tell player can't target dead? or just ignore
                        } else {
                            // TODO game method for set target
                            game.pickTarget(targetId);
                            selectingFoe = false;
                            findViewById(R.id.logNotify).setVisibility(View.VISIBLE);
                            disableMoveButons(BUTTON_INDEX_ALL);
                        }
                    }
                }
            });

            layout.addView(foeTextView);
            foeTextViews.add(foeTextView);
        }
    }

    //
    public void attackClick(View attackButton){
        if (selectingFoe){
            game.pickMove(null);
            // restore buttons
            enableMoveButtons();
        } else {
            // set default attack move to player character
            game.pickMove(Move.findMoveByID(Move.BASIC_ATTACK));

            // set views of other moves to disable (greyed out)
            disableMoveButons(BUTTON_INDEX_ATTACK);
        }
        selectingFoe = !selectingFoe;
    }

    //
    public void spellClick(View spellButton){
        if (selectingMove){
            findViewById(R.id.listSpells).setVisibility(View.INVISIBLE);

            // restore buttons
            enableMoveButtons();
        } else {
            findViewById(R.id.listSpells).setVisibility(View.VISIBLE);

            selectingFoe = false;
            // set views of other moves to disable (greyed out)
            disableMoveButons(BUTTON_INDEX_SPELL);
        }
        game.getPlayerCharacter().setMove(null);
        selectingMove = !selectingMove;
    }

    //
    public void itemClick(View itemButton){
        if (selectingMove){
            game.getPlayerCharacter().setMove(null);

            findViewById(R.id.listItems).setVisibility(View.INVISIBLE);

            // restore buttons
            enableMoveButtons();
        } else {
            // listview
            findViewById(R.id.listItems).setVisibility(View.VISIBLE);

            // set views of other moves to disable (greyed out)
            disableMoveButons(BUTTON_INDEX_ITEM);
        }
        selectingMove = !selectingMove;
    }

    //
    public void defendClick(View defendButton){
        game.getPlayerCharacter().setMove(Move.findMoveByID(Move.BASIC_DEFEND));
        game.pickTarget(Move.TARGET_SELF);

        // set views of other moves to disable (greyed out)
        disableMoveButons(BUTTON_INDEX_ALL);
        findViewById(R.id.logNotify).setVisibility(View.VISIBLE);
    }

    // enables all move buttons and restores their background color
    private void enableMoveButtons(){
        findViewById(R.id.attackButton).setEnabled(true);
        findViewById(R.id.spellButton).setEnabled(true);
        findViewById(R.id.itemButton).setEnabled(true);
        findViewById(R.id.defendButton).setEnabled(true);
    }

    // disables move buttons and greys them out. can ignore a button based on passed int
    private void disableMoveButons(int ignoreIndex){
        if (ignoreIndex != BUTTON_INDEX_ATTACK) {
            findViewById(R.id.attackButton).setEnabled(false);
        }
        if (ignoreIndex != BUTTON_INDEX_SPELL) {
            findViewById(R.id.spellButton).setEnabled(false);
        }
        if (ignoreIndex != BUTTON_INDEX_ITEM) {
            findViewById(R.id.itemButton).setEnabled(false);
        }
        if (ignoreIndex != BUTTON_INDEX_DEFEND) {
            findViewById(R.id.defendButton).setEnabled(false);
        }
    }

    //
    public void logClick(View logTextView) {

        game.advance();
        String poppedMessage = game.pop();

        if (!poppedMessage.isEmpty()) {
            log.add(poppedMessage);
            if (log.size() > 8) log.removeFirst();

            String logText = "";
            for (int i = 0; i < log.size(); i++) {
                logText += log.get(i) + "\n";
            }
            ((TextView) logTextView).setText(logText);

            updateHealthMagicDisplays();
        }

        if (!game.roundInProgress()) {
            findViewById(R.id.logNotify).setVisibility(View.INVISIBLE);

            if (!selectingFoe && !selectingMove) enableMoveButtons();
        }

        //
        if (game.gameOver()){

            // should print some more messages first
            // who died (pc or some foe)
            // if pc wins, 2 messages (1 for money and 1 for level up)

            Intent newPage = new Intent(this, ShopPage.class);
            newPage.putExtra(TitlePage.KEY_PLAYER, game.getPlayerCharacter());

            startActivity(newPage);

        }
    }

    //
    private void updateHealthMagicDisplays(){

        for (int i = 0; i < foeTextViews.size(); i++){
            foeTextViews.get(i).setText(game.getFoes().get(i).getName() + "\n" + game.getFoes().get(i).getHealth());
            if (game.getFoes().get(i).isDead()) foeTextViews.get(i).setBackgroundColor(Color.parseColor("#332222"));
        }

        ((ProgressBar) findViewById(R.id.charHealth)).setProgress(game.getPlayerCharacter().getHealth());
        ((ProgressBar) findViewById(R.id.charMagic)).setProgress(game.getPlayerCharacter().getMagic());
        ((TextView) findViewById(R.id.charHealthText)).setText("" + game.getPlayerCharacter().getHealth());
        ((TextView) findViewById(R.id.charMagicText)).setText("" + game.getPlayerCharacter().getMagic());
    }

    //
    @Override
    public void onBackPressed(){
        if (selectingFoe) {
            selectingFoe = false;
            game.getPlayerCharacter().setMove(null);
            enableMoveButtons();
        } else if (selectingMove) {
            selectingMove = false;
            // TODO hide lists
            enableMoveButtons();
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
