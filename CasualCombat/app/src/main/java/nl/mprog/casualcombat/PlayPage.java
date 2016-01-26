// UNIVERSITEIT VAN AMSTERDAM - MINOR PROGRAMMEREN - PROGRAMMEERPROJECT
// CasualCombat - created by Joseph Weel, 10321624, josefweel@gmail.com

package nl.mprog.casualcombat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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

import foe.Foe;
import item.UsableItem;
import move.Move;

// this page creates an instance of Combat and interacts with it based on user input
public class PlayPage extends AppCompatActivity {

    private static final int BUTTON_INDEX_ALL = 0;
    private static final int BUTTON_INDEX_ATTACK = 1;
    private static final int BUTTON_INDEX_SPELL = 2;
    private static final int BUTTON_INDEX_ITEM = 3;
    private static final int BUTTON_INDEX_DEFEND = 4;

    // these two booleans affect what parts of the screen interact with touch
    private boolean selectingFoe = false;
    private boolean selectingMove = false;

    private ArrayList<TextView> foeTextViews;

    private Combat combat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get player from previous activity
        Intent previousPage = getIntent();
        PlayerCharacter playerCharacter = previousPage.getParcelableExtra(TitlePage.KEY_PLAYER);

        // initialization of combat object creates foes
        combat = new Combat(playerCharacter);

        // update views
        displayPlayerValues(combat.getPlayerCharacter());
        displayFoes(combat.getFoes());
        resizeButtons();
        prepareLists();
        prepareLogView();
    }

    // resizes the action buttons to nice even sizes
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

    // prepares lists corresponding to the player character's spells and items
    private void prepareLists() {
        ListView spellListView = (ListView) findViewById(R.id.listSpells);
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (40 * scale + 0.5f);

        int halfScreenWidth = (int) ((getResources().getDisplayMetrics().widthPixels - pixels) / 2.0);
        int topMargin = (int)(getResources().getDisplayMetrics().heightPixels / 16.0 * scale + 0.5f);
        int height = (int)(getResources().getDisplayMetrics().heightPixels / 7.0 * scale + 0.5f) * 3;

        RelativeLayout.LayoutParams spellParams = new RelativeLayout.LayoutParams(halfScreenWidth, height);
        spellParams.setMargins(24, topMargin, 24, 0);
        spellParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        spellListView.setLayoutParams(spellParams);
        setSpellAdapter(spellListView);

        ListView itemListView = (ListView) findViewById(R.id.listItems);
        RelativeLayout.LayoutParams itemParams = new RelativeLayout.LayoutParams(halfScreenWidth, height);
        itemParams.setMargins(24, topMargin, 24, 0);
        itemParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        itemListView.setLayoutParams(itemParams);
        setItemAdapter(itemListView);

        spellListView.setVisibility(View.INVISIBLE);
        itemListView.setVisibility(View.INVISIBLE);
    }

    // sets an adapter for the spell list, with a listener to allow the user to progress their selection
    private void setSpellAdapter(ListView spellListView){
        final SpellAdapter spellAdapter = new SpellAdapter(this, R.layout.spell_list, R.id.listSpellName, new ArrayList<>(combat.getPlayerCharacter().getSpells()));
        spellAdapter.updateUsableSpells(combat.getPlayerCharacter());
        spellListView.setAdapter(spellAdapter);
        spellListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // when a castable (nonnull means enough magic) spell is selected, prepare target selection
                // or proceed if target is self
                Move move = spellAdapter.getListMove(position);
                if (move != null) {
                    combat.pickMove(move);
                    selectingMove = false;
                    if (move.getRange() == Move.RANGE_SELF) {
                        combat.pickTarget(Move.TARGET_SELF);
                        disableMoveButons(BUTTON_INDEX_ALL);
                        findViewById(R.id.logNotify).setVisibility(View.VISIBLE);
                    } else {
                        selectingFoe = true;
                        disableMoveButons(BUTTON_INDEX_SPELL);
                    }
                    findViewById(R.id.listSpells).setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    // sets an adapter for the item list, with a listener to allow the user to progress their selection
    private void setItemAdapter(ListView itemListView){
        final ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.item_list, R.id.listItemName, new ArrayList<>(combat.getPlayerCharacter().getUsableItems()));
        itemListView.setAdapter(itemAdapter);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // when an item is selected, prepare target selection, or proceed if target is self
                UsableItem item = itemAdapter.getListItem(position);
                combat.pickMove(item.getItemMove());
                selectingMove = false;
                if (item.getItemMove().getRange() == Move.RANGE_SELF) {
                    combat.pickTarget(Move.TARGET_SELF);
                    disableMoveButons(BUTTON_INDEX_ALL);
                    findViewById(R.id.logNotify).setVisibility(View.VISIBLE);
                } else {
                    selectingFoe = true;
                    disableMoveButons(BUTTON_INDEX_ITEM);
                }
                findViewById(R.id.listItems).setVisibility(View.INVISIBLE);
            }
        });
    }

    // sets up the views that show the status of the player character
    private void displayPlayerValues(PlayerCharacter playerCharacter){
        ImageView charIcon = (ImageView) findViewById(R.id.charIcon);
        Drawable d = ContextCompat.getDrawable(getBaseContext(), R.drawable.avatar);
        d.mutate().setColorFilter(Color.parseColor(combat.getPlayerCharacter().getColorString()), PorterDuff.Mode.MULTIPLY);
        charIcon.setImageDrawable(d);

        ProgressBar charHealth = (ProgressBar) findViewById(R.id.charHealth);
        charHealth.setMax(combat.getPlayerCharacter().getMaxHealth());
        charHealth.setProgress(combat.getPlayerCharacter().getHealth());

        ProgressBar charMagic = (ProgressBar) findViewById(R.id.charMagic);
        charMagic.setMax(combat.getPlayerCharacter().getMaxMagic());
        charMagic.setProgress(combat.getPlayerCharacter().getMagic());

        ((TextView) findViewById(R.id.charName)).setText(playerCharacter.getName());
        ((TextView) findViewById(R.id.charHealthText)).setText("" + playerCharacter.getHealth() + "/" + playerCharacter.getMaxHealth());
        ((TextView) findViewById(R.id.charMagicText)).setText("" + playerCharacter.getMagic() + "/" + playerCharacter.getMaxMagic());
        ((TextView) findViewById(R.id.charLevel)).setText("LVL " + playerCharacter.getLevel());
        ((TextView) findViewById(R.id.charStrength)).setText("STR " + playerCharacter.getStrength());
        ((TextView) findViewById(R.id.charWillpower)).setText("WIL " + playerCharacter.getWillpower());
        ((TextView) findViewById(R.id.charDefense)).setText("DEF " + playerCharacter.getDefense());
        ((TextView) findViewById(R.id.charResistance)).setText("RES " + playerCharacter.getResistance());
        ((TextView) findViewById(R.id.charSpeed)).setText("SPD " + playerCharacter.getSpeed());
    }

    // sets up the views that show the status of the foes, with a listener that handles that foe being targeted
    private void displayFoes(ArrayList<Foe> foes){
        LinearLayout layout = (LinearLayout)findViewById(R.id.foeLayout);
        float equalWeight = layout.getWeightSum() / foes.size();
        foeTextViews = new ArrayList<>();

        // width of the view is depends on amount of foes
        for (int i = 0; i < foes.size(); i++){
            TextView foeTextView = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(2,0,2,0);
            params.weight = equalWeight;
            foeTextView.setLayoutParams(params);

            foeTextView.setText(foes.get(i).getName() + "\n" + foes.get(i).getHealth() + " HP");
            foeTextView.setGravity(Gravity.CENTER);
            foeTextView.setTextColor(Color.parseColor("#FFFFFF"));

            // every subclass of foe has their own background color
            foeTextView.setBackgroundColor(Color.parseColor(foes.get(i).getColor()));

            final int targetId = i;
            foeTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // when clicked at appropriate time, player character target is set to this foe
                    if (selectingFoe) {
                        if (!combat.getFoes().get(targetId).isDead()) {
                            combat.pickTarget(targetId);
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

    // prepares the scrollable TextView that displays all messages received from the Combat object
    private void prepareLogView(){
        TextView logView = (TextView) findViewById(R.id.logText);
        logView.getLayoutParams().height = logView.getHeight() - logView.getLineHeight();
        logView.setLayoutParams(logView.getLayoutParams());
        logView.setMovementMethod(new ScrollingMovementMethod());

        // the visibility of the notification message corresponds to when it can be clicked
        findViewById(R.id.logNotify).setVisibility(View.INVISIBLE);
    }

    // button that corresponds to the default attack move
    public void attackClick(View attackButton){
        // if already was selecting (with attack or different move), cancel
        if (selectingFoe) {
            combat.pickMove(null);
            enableMoveButtons();
        }
        // else set player character's move to be the default attack
        else {
            combat.pickMove(Move.findMoveById(Move.BASIC_ATTACK));
            disableMoveButons(BUTTON_INDEX_ATTACK);
        }
        selectingFoe = !selectingFoe;
    }

    // button that corresponds to displaying the list of spells
    public void spellClick(View spellButton){
        // if already was selecting (with attack or different move), cancel
        if (selectingMove){
            findViewById(R.id.listSpells).setVisibility(View.INVISIBLE);
            enableMoveButtons();
        }
        // else show spell list, where user can pick specific move
        else {
            findViewById(R.id.listSpells).setVisibility(View.VISIBLE);
            selectingFoe = false;
            disableMoveButons(BUTTON_INDEX_SPELL);
        }
        combat.getPlayerCharacter().setMove(null);
        selectingMove = !selectingMove;
    }

    // button that corresponds to displaying the list of usable items
    public void itemClick(View itemButton){
        // if already was selecting (with attack or different move), cancel
        if (selectingMove){
            findViewById(R.id.listItems).setVisibility(View.INVISIBLE);
            findViewById(R.id.listItemsEmptyText).setVisibility(View.INVISIBLE);
            enableMoveButtons();
        }
        // else show spell list, where user can pick specific move
        else {
            findViewById(R.id.listItems).setVisibility(View.VISIBLE);

            // display no items left message, if applicable
            if (combat.getPlayerCharacter().getUsableItems().isEmpty()) findViewById(R.id.listItemsEmptyText).setVisibility(View.VISIBLE);

            selectingFoe = false;
            disableMoveButons(BUTTON_INDEX_ITEM);
        }
        combat.getPlayerCharacter().setMove(null);
        selectingMove = !selectingMove;
    }

    // button that corresponds to the default defend move
    public void defendClick(View defendButton){
        // no selection necessary, combat can be progressed right away
        combat.getPlayerCharacter().setMove(Move.findMoveById(Move.BASIC_DEFEND));
        combat.pickTarget(Move.TARGET_SELF);
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

    // pressing anywhere in the log window can lead to advancing the combat
    // text in the scrollable TextView is updated afterwards
    public void logClick(View logLayoutView) {
        combat.advance();
        String poppedMessage = combat.pop();

        if (!poppedMessage.isEmpty()) {
            TextView logTextView = (TextView) findViewById(R.id.logText);
            if (logTextView.getText().length() == 0) logTextView.setText(poppedMessage);
            else logTextView.setText(logTextView.getText() + "\n" + poppedMessage);
            updateHealthMagicDisplays();
        }

        // if a round is over, the notification message can be hidden, and more user input is needed
        if (!combat.roundInProgress()) {
            findViewById(R.id.logNotify).setVisibility(View.INVISIBLE);

            if (!selectingFoe && !selectingMove) enableMoveButtons();
            ((SpellAdapter)(((ListView) findViewById(R.id.listSpells)).getAdapter())).updateUsableSpells(combat.getPlayerCharacter());
            ((ItemAdapter)(((ListView) findViewById(R.id.listItems)).getAdapter())).updateUsableItems(combat.getPlayerCharacter());
        }

        // if the combat is over, PlayPage can be closed
        if (combat.gameOver()){
            setResult(RESULT_OK, getIntent().putExtra(TitlePage.KEY_PLAYER, combat.getPlayerCharacter()));
            finish();
        }
    }

    // updates the views that show the status of the combatants
    private void updateHealthMagicDisplays(){
        for (int i = 0; i < foeTextViews.size(); i++){
            foeTextViews.get(i).setText(combat.getFoes().get(i).getName() + "\n" + combat.getFoes().get(i).getHealth() + " HP");
            if (combat.getFoes().get(i).isDead()) foeTextViews.get(i).setBackgroundColor(Color.parseColor("#332222"));
        }

        ((ProgressBar) findViewById(R.id.charHealth)).setProgress(combat.getPlayerCharacter().getHealth());
        ((ProgressBar) findViewById(R.id.charMagic)).setProgress(combat.getPlayerCharacter().getMagic());
        ((TextView) findViewById(R.id.charHealthText)).setText("" + combat.getPlayerCharacter().getHealth() + "/" + combat.getPlayerCharacter().getMaxHealth());
        ((TextView) findViewById(R.id.charMagicText)).setText("" + combat.getPlayerCharacter().getMagic() + "/" + combat.getPlayerCharacter().getMaxMagic());
    }

    // pressing back can cancel a selection. if no selection made, a dialog is started to exit PlayPage early
    @Override
    public void onBackPressed(){
        if (selectingFoe) {
            selectingFoe = false;
            combat.getPlayerCharacter().setMove(null);
            enableMoveButtons();
        } else if (selectingMove) {
            selectingMove = false;
            findViewById(R.id.listSpells).setVisibility(View.INVISIBLE);
            findViewById(R.id.listItems).setVisibility(View.INVISIBLE);
            enableMoveButtons();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You are about to leave this page.\nUnsaved progress will be lost.");
            builder.setPositiveButton("Leave combat", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setResult(RESULT_CANCELED);
                    finish();
                }
            });
            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing -> dismisses dialog
                }
            });
            builder.setNegativeButton("Exit app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setResult(TitlePage.RESULT_EXIT);
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    // support for action bar back press button, and button to go to information page
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if (item.getItemId() == R.id.help) {
            Intent newPage = new Intent(this, InfoPage.class);
            startActivity(newPage);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // add back press button and button to go to information page to the top action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play_page, menu);
        return true;
    }
}
