package admin.cozycombat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class TitlePage extends AppCompatActivity {

    static final String KEY_PREFS = "CASUALCOMBATPREFS";
    static final String KEY_PLAYER = "CASUALCOMBATPLAYER";

    // TODO
    // maybe use camera to take selfie for character icon
    // an option button on the right side of the upper action bar where you can go to information
    // possibly improve the player screen buttons margins to be dynamically perfectly sized based on width. (screenWidth - 5 * 30) / 6 ?

    ArrayList<PlayerCharacter> storedPlayerCharacters;
    PlayerCharacter playerCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_page);

        prepareStoredPlayerList();

        setPlayerVisibility(View.INVISIBLE);
        setLevelUpButtonsVisibility(View.INVISIBLE);
    }

    //
    private void prepareStoredPlayerList(){
        storedPlayerCharacters = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences(KEY_PREFS, MODE_PRIVATE);
        Map<String, ?> keys = prefs.getAll();
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, KEY_PREFS, MODE_PRIVATE);
        for (String key : keys.keySet()) {
            PlayerCharacter pc = complexPreferences.getObject(key, PlayerCharacter.class);
            storedPlayerCharacters.add(pc);
        }

        PlayerCharacterAdapter adapter = new PlayerCharacterAdapter(this, R.layout.player_character_list, R.id.listCharName, storedPlayerCharacters);
        ListView storedPlayerListView = (ListView) findViewById(R.id.titlePlayerList);
        storedPlayerListView.setAdapter(adapter);
        storedPlayerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadPlayerCharacter(position);
            }
        });
        setLoadedPlayerListVisibility(View.INVISIBLE);
    }

    //
    private void setLoadedPlayerListVisibility(int visibility){
        findViewById(R.id.titleLayoutList).setVisibility(visibility);
    }

    //
    private void loadPlayerCharacter(int position){
        PlayerCharacter loadedCharacter = storedPlayerCharacters.get(position);
        playerCharacter = loadedCharacter.copy();

        updatePlayerSkillViews();
        setPlayerVisibility(View.VISIBLE);
        setLevelUpButtonsVisibility(View.INVISIBLE);
        setLoadedPlayerListVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.readyButton)).setText("Start");
        findViewById(R.id.readyButton).setEnabled(true);
    }

    //
    private void setPlayerVisibility(int visibility){
        findViewById(R.id.titleLayoutCharacter).setVisibility(visibility);
        findViewById(R.id.titleCancelButton).setVisibility(visibility);
    }

    //
    public void readyClick(View readyButton){

        if (playerCharacter != null && playerCharacter.finishedLevelUp()) {

            if (!exists(playerCharacter)) {
                ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, KEY_PREFS, MODE_PRIVATE);;
                complexPreferences.putObject(playerCharacter.getName(), playerCharacter);
                complexPreferences.commit();
            }

            Intent newPage = new Intent(this, PlayPage.class);
            newPage.putExtra(KEY_PLAYER, playerCharacter);

            startActivity(newPage);
        } else {

            ((Button) readyButton).setText("Start");
            readyButton.setEnabled(false);

            // prepare creatable character
            playerCharacter = new PlayerCharacter();
            nameChangeClick(null);

            updatePlayerSkillViews();
            setPlayerVisibility(View.VISIBLE);
            setLevelUpButtonsVisibility(View.VISIBLE);

        }
    }

    //
    private boolean exists(PlayerCharacter playerCharacter){
        for (PlayerCharacter pc : storedPlayerCharacters){
            if (pc.getName().equals(playerCharacter.getName())) return true;
        }
        return false;
    }

    //
    public void nameChangeClick(View nameButton){

        // http://stackoverflow.com/questions/10903754/input-text-dialog-android
        // http://stackoverflow.com/questions/2620444/how-to-prevent-a-dialog-from-closing-when-a-button-is-clicked
        // http://stackoverflow.com/questions/3285412/limit-text-length-of-edittext-in-android

        final EditText dialogEditText = new EditText(this);
        dialogEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        dialogEditText.setMaxLines(1);
        dialogEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
        dialogEditText.setText(playerCharacter.getName());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter character name:");
        builder.setView(dialogEditText);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing (this allows the default dismiss function to be overwritten)
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String newName = dialogEditText.getText().toString();
                if (!newName.isEmpty()) { // TODO and name does not exist (unless name is equal to already entered name)
                    renamePlayerCharacter(newName);
                    dialog.dismiss();
                }
            }
        });

//        dialogEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    //
    private void renamePlayerCharacter(String newName){
        playerCharacter.setName(newName);
        ((TextView) findViewById(R.id.titleCharName)).setText(playerCharacter.getName());
    }

    //
    public void addClick(View addButton){

        switch(addButton.getId()){
            case R.id.titleCharHealthAdd:
                playerCharacter.addHealth();
                break;
            case R.id.titleCharMagicAdd:
                playerCharacter.addMagic();
                break;
            case R.id.titleCharStrengthAdd:
                playerCharacter.addStrength();
                break;
            case R.id.titleCharWillpowerAdd:
                playerCharacter.addWillpower();
                break;
            case R.id.titleCharDefenseAdd:
                playerCharacter.addDefense();
                break;
            case R.id.titleCharResistanceAdd:
                playerCharacter.addResistance();
                break;
            case R.id.titleCharSpeedAdd:
                playerCharacter.addSpeed();
                break;
        }

        playerCharacter.subtractLevelPoint();
        updatePlayerSkillViews();

        if (playerCharacter.finishedLevelUp()) {
            findViewById(R.id.readyButton).setEnabled(true);
            setLevelUpButtonsVisibility(View.INVISIBLE);
        }
    }

    //
    private void updatePlayerSkillViews(){
        ((ProgressBar) findViewById(R.id.titleCharHealth)).setMax(playerCharacter.getMaxHealth());
        ((ProgressBar) findViewById(R.id.titleCharHealth)).setProgress(playerCharacter.getHealth());
        ((ProgressBar) findViewById(R.id.titleCharMagic)).setMax(playerCharacter.getMaxMagic());
        ((ProgressBar) findViewById(R.id.titleCharMagic)).setProgress(playerCharacter.getMagic());

        ((TextView) findViewById(R.id.titleCharName)).setText(playerCharacter.getName());
        ((TextView) findViewById(R.id.titleCharLevel)).setText("LEVEL " + playerCharacter.getLevel());

        ((TextView) findViewById(R.id.titleCharHealthText)).setText("" + playerCharacter.getMaxHealth());
        ((TextView) findViewById(R.id.titleCharMagicText)).setText("" + playerCharacter.getMaxMagic());
        ((TextView) findViewById(R.id.titleCharStrength)).setText("STR " + playerCharacter.getStrength());
        ((TextView) findViewById(R.id.titleCharWillpower)).setText("WIL " + playerCharacter.getWillpower());
        ((TextView) findViewById(R.id.titleCharDefense)).setText("DEF " + playerCharacter.getDefense());
        ((TextView) findViewById(R.id.titleCharResistance)).setText("RES " + playerCharacter.getResistance());
        ((TextView) findViewById(R.id.titleCharSpeed)).setText("SPD " + playerCharacter.getSpeed());

        ((TextView) findViewById(R.id.titleCharPoints)).setText("Points left to spend: " + playerCharacter.getLevelPoints());
    }

    //
    private void setLevelUpButtonsVisibility(int visibility){
        findViewById(R.id.titleCharNameChangeButton).setVisibility(visibility);
        findViewById(R.id.titleCharHealthAdd).setVisibility(visibility);
        findViewById(R.id.titleCharMagicAdd).setVisibility(visibility);
        findViewById(R.id.titleCharStrengthAdd).setVisibility(visibility);
        findViewById(R.id.titleCharWillpowerAdd).setVisibility(visibility);
        findViewById(R.id.titleCharDefenseAdd).setVisibility(visibility);
        findViewById(R.id.titleCharResistanceAdd).setVisibility(visibility);
        findViewById(R.id.titleCharSpeedAdd).setVisibility(visibility);
        findViewById(R.id.titleCharPoints).setVisibility(visibility);
    }

    //
    public void loadClick(View loadButton){

        if (findViewById(R.id.titleLayoutList).getVisibility() == View.VISIBLE){

            // only show player if one was being created
            if (((Button) findViewById(R.id.readyButton)).getText().equals("Start")) setPlayerVisibility(View.VISIBLE);

            setLoadedPlayerListVisibility(View.INVISIBLE);
            findViewById(R.id.readyButton).setEnabled(true);

        } else {
            setPlayerVisibility(View.INVISIBLE);
            setLoadedPlayerListVisibility(View.VISIBLE);

            findViewById(R.id.readyButton).setEnabled(false);

            if (storedPlayerCharacters.isEmpty()) findViewById(R.id.titlePlayerListEmpty).setVisibility(View.VISIBLE);
            else findViewById(R.id.titlePlayerListEmpty).setVisibility(View.INVISIBLE);

        }
    }

    //
    public void cancelClick(View cancelButton){

        playerCharacter = null;
        ((Button) findViewById(R.id.readyButton)).setText("New");
        findViewById(R.id.readyButton).setEnabled(true);

        setPlayerVisibility(View.INVISIBLE);
    }

    //
    public void leaderboardClick(View leaderButton){
        Intent newPage = new Intent(this, LeaderboardPage.class);
        startActivity(newPage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu title) {
        // Inflate the title; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_title_page, title);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
