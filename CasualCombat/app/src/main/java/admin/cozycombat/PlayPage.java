package admin.cozycombat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

public class PlayPage extends AppCompatActivity {

    // TODO
    // click spell button brings up scrollable list over item and defend
    // click item does same but over attack and spell
    //
    // when pressing a button it should highlight, but stop highlight when no longer pressed

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

        resizeButtons();
        prepareLists();

        // todo get pc from something
        Intent previousPage = getIntent();
        PlayerCharacter playerCharacter = previousPage.getParcelableExtra(MenuPage.KEY_PLAYER);

        game = new Game(playerCharacter);

        displayPlayer(game.getPlayerCharacter());
        displayFoes(game.getFoes());

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

//        MoveAdapter adapter = new MoveAdapter(this, R.layout.spell_list, R.id.listSpellName, game.getPlayerCharacter().getSpells());
//        storedPlayerListView.setAdapter(adapter);
//        storedPlayerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                loadPlayerCharacter(position);
//            }
//        });

        spellListView.setVisibility(View.INVISIBLE);
        itemListView.setVisibility(View.INVISIBLE);
    }

    //
    private void displayPlayer(Combatant playerCharacter){
        ImageView charIcon = (ImageView) findViewById(R.id.charIcon);

        TextView charName = (TextView) findViewById(R.id.charName);
        charName.setText(playerCharacter.getName());

        ProgressBar charHealth = (ProgressBar) findViewById(R.id.charHealth);
        charHealth.setMax(game.getPlayerCharacter().getMaxHealth());
        charHealth.getProgressDrawable().setColorFilter(Color.parseColor("#00AA00"), PorterDuff.Mode.SRC_IN);
        ProgressBar charMagic = (ProgressBar) findViewById(R.id.charMagic);
        charMagic.setMax(game.getPlayerCharacter().getMaxMagic());
        charMagic.getProgressDrawable().setColorFilter(Color.parseColor("#0055DD"), PorterDuff.Mode.SRC_IN);

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
        foeTextViews = new ArrayList<>();

        for (int i = 0; i < foes.size(); i++){
            TextView foeTextView = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(2,0,2,0);
            params.weight = equalWeight;
            foeTextView.setLayoutParams(params);
            foeTextView.setText(foes.get(i).getName() + "\n" + foes.get(i).getHealth());
            foeTextView.setBackgroundColor(Color.parseColor("#345678"));
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
                            game.getPlayerCharacter().getMove().setTarget(targetId);
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
            game.getPlayerCharacter().setMove(null);
            // restore buttons
            enableMoveButtons();
        } else {
            // set default attack move to player character
            game.getPlayerCharacter().setMove(new Move(Move.BASIC_ATTACK));

            // set views of other moves to disable (greyed out)
            disableMoveButons(BUTTON_INDEX_ATTACK);
        }
        selectingFoe = !selectingFoe;
    }

    //
    public void spellClick(View spellButton){
        if (selectingMove){
            game.getPlayerCharacter().setMove(null);

            findViewById(R.id.listSpells).setVisibility(View.INVISIBLE);

            // restore buttons
            enableMoveButtons();
        } else {
            // listview
            findViewById(R.id.listSpells).setVisibility(View.VISIBLE);

            // set views of other moves to disable (greyed out)
            disableMoveButons(BUTTON_INDEX_SPELL);
        }
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
        game.getPlayerCharacter().setMove(new Move(Move.BASIC_DEFEND));
        game.getPlayerCharacter().getMove().setTarget(Move.TARGET_SELF);

        // set views of other moves to disable (greyed out)
        disableMoveButons(BUTTON_INDEX_ALL);
        findViewById(R.id.logNotify).setVisibility(View.VISIBLE);
    }

    // TODO maybe dont change color, enable does maybe enough
    // enables all move buttons and restores their background color
    private void enableMoveButtons(){
        findViewById(R.id.attackButton).setEnabled(true);
//        findViewById(R.id.attackButton).setBackgroundColor(Color.parseColor("#FFFFFF"));
        findViewById(R.id.spellButton).setEnabled(true);
//        findViewById(R.id.spellButton).setBackgroundColor(Color.parseColor("#FFFFFF"));
        findViewById(R.id.itemButton).setEnabled(true);
//        findViewById(R.id.itemButton).setBackgroundColor(Color.parseColor("#FFFFFF"));
        findViewById(R.id.defendButton).setEnabled(true);
//        findViewById(R.id.defendButton).setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    // disables move buttons and greys them out. can ignore a button based on passed int
    private void disableMoveButons(int ignoreIndex){
        if (ignoreIndex != BUTTON_INDEX_ATTACK) {
            findViewById(R.id.attackButton).setEnabled(false);
//            findViewById(R.id.attackButton).setBackgroundColor(Color.parseColor("#999999"));
        }
        if (ignoreIndex != BUTTON_INDEX_SPELL) {
            findViewById(R.id.spellButton).setEnabled(false);
//            findViewById(R.id.spellButton).setBackgroundColor(Color.parseColor("#999999"));
        }
        if (ignoreIndex != BUTTON_INDEX_ITEM) {
            findViewById(R.id.itemButton).setEnabled(false);
//            findViewById(R.id.itemButton).setBackgroundColor(Color.parseColor("#999999"));
        }
        if (ignoreIndex != BUTTON_INDEX_DEFEND) {
            findViewById(R.id.defendButton).setEnabled(false);
//            findViewById(R.id.defendButton).setBackgroundColor(Color.parseColor("#999999"));
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

            updateFoeViews();
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
            startActivity(newPage);

        }
    }

    //
    private void updateFoeViews(){
        // TODO consider an alternative:
        // reset the foetextview array list and call the displayfoes again
        // this redoes all the listeners and stuff and basically removes a targetable foe
        //ArrayList<TextView> newFoeTextViews = new ArrayList<>();

        for (int i = 0; i < foeTextViews.size(); i++){
            foeTextViews.get(i).setText(game.getFoes().get(i).getName() + "\n" + game.getFoes().get(i).getHealth());
            if (game.getFoes().get(i).isDead()) foeTextViews.get(i).setBackgroundColor(Color.parseColor("#332222"));
        }

        // TODO temp , or rename this method and keep this here, since it is also called after foe attack pc
        ProgressBar charHealth = (ProgressBar) findViewById(R.id.charHealth);
        charHealth.setProgress(game.getPlayerCharacter().getHealth());
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
