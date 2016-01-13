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

public class MenuPage extends AppCompatActivity {

    // TODO
    // bundle or serializable or database or complex prefs
    // possibly improve the player screen buttons margins to be dynamically perfectly sized based on width. (screenWidth - 5 * 30) / 6 ?

    ArrayList<PlayerCharacter> storedPlayerCharacters;
    PlayerCharacter playerCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);


        ((Button) findViewById(R.id.readyButton)).setText("NEW");
        ((ProgressBar) findViewById(R.id.menuCharHealth)).getProgressDrawable().setColorFilter(Color.parseColor("#00AA00"), PorterDuff.Mode.SRC_IN);
        ((ProgressBar) findViewById(R.id.menuCharMagic)).getProgressDrawable().setColorFilter(Color.parseColor("#0055DD"), PorterDuff.Mode.SRC_IN);

        prepareStoredPlayerList();

        setPlayerVisibility(View.INVISIBLE);
        setLevelUpButtonsVisibility(View.INVISIBLE);
    }

    //
    private void prepareStoredPlayerList(){
        storedPlayerCharacters = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences("PREFS", MODE_PRIVATE);

        // TODO temp
        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());
//        storedPlayerCharacters.add(new PlayerCharacter());


        PlayerCharacterAdapter adapter = new PlayerCharacterAdapter(this, R.layout.player_character_list, R.id.listCharName, storedPlayerCharacters);
        ListView storedPlayerListView = (ListView) findViewById(R.id.menuPlayerList);
        storedPlayerListView.setAdapter(adapter);
        storedPlayerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //itemLoadPlayer(parent, position);
            }
        });
        //storedPlayerListView.setEmptyView(findViewById(android.R.id.empty));

        setLoadedPlayerListVisibility(View.INVISIBLE);
        //findViewById(R.id.menuPlayerListEmpty).setVisibility(View.INVISIBLE);
    }

    //
    private void setLoadedPlayerListVisibility(int visibility){
        findViewById(R.id.menuLayoutList).setVisibility(visibility);
    }

    //
    private void setPlayerVisibility(int visibility){

        findViewById(R.id.menuLayoutCharacter).setVisibility(visibility);

//        findViewById(R.id.menuCharIcon).setVisibility(visibility);
//        findViewById(R.id.menuCharName).setVisibility(visibility);
//        findViewById(R.id.menuCharHealth).setVisibility(visibility);
//        findViewById(R.id.menuCharMagic).setVisibility(visibility);
//        findViewById(R.id.menuCharLevel).setVisibility(visibility);
//        findViewById(R.id.menuCharStrength).setVisibility(visibility);
//        findViewById(R.id.menuCharWillpower).setVisibility(visibility);
//        findViewById(R.id.menuCharDefense).setVisibility(visibility);
//        findViewById(R.id.menuCharResistance).setVisibility(visibility);
//        findViewById(R.id.menuCharSpeed).setVisibility(visibility);
    }

    //
    public void readyClick(View readyButton){

        if (playerCharacter != null && playerCharacter.finishedLevelUp()) {

            if (!exists(playerCharacter)) {
                SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
                //editor.put????
                editor.apply();
            }

            Intent newPage = new Intent(this, PlayPage.class);
            newPage.putExtra("player", playerCharacter); // TODO key

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
                //do nothing
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
        ((TextView) findViewById(R.id.menuCharName)).setText(playerCharacter.getName());
    }

    //
    public void addClick(View addSpeedButton){

        switch(addSpeedButton.getId()){
            case R.id.menuCharHealthAdd:
                playerCharacter.addHealth();
                break;
            case R.id.menuCharMagicAdd:
                playerCharacter.addMagic();
                break;
            case R.id.menuCharStrengthAdd:
                playerCharacter.addStrength();
                break;
            case R.id.menuCharWillpowerAdd:
                playerCharacter.addWillpower();
                break;
            case R.id.menuCharDefenseAdd:
                playerCharacter.addDefense();
                break;
            case R.id.menuCharResistanceAdd:
                playerCharacter.addResistance();
                break;
            case R.id.menuCharSpeedAdd:
                playerCharacter.addSpeed();
                break;
        }

        updatePlayerSkillViews();

        playerCharacter.subtractLevelPoint();
        ((TextView) findViewById(R.id.menuCharPoints)).setText("Points left to spend: " + playerCharacter.getLevelPoints());
        if (playerCharacter.finishedLevelUp()) {
            findViewById(R.id.readyButton).setEnabled(true);
            setLevelUpButtonsVisibility(View.INVISIBLE);
        }
    }

    //
    private void updatePlayerSkillViews(){
        ((ProgressBar) findViewById(R.id.menuCharHealth)).setMax(playerCharacter.getMaxHealth());
        ((ProgressBar) findViewById(R.id.menuCharHealth)).setMax(playerCharacter.getMaxHealth());
        ((ProgressBar) findViewById(R.id.menuCharMagic)).setProgress(playerCharacter.getHealth());
        ((ProgressBar) findViewById(R.id.menuCharMagic)).setProgress(playerCharacter.getMagic());

        ((TextView) findViewById(R.id.menuCharLevel)).setText("LEVEL " + playerCharacter.getLevel());

        ((TextView) findViewById(R.id.menuCharHealthText)).setText("" + playerCharacter.getMaxHealth());
        ((TextView) findViewById(R.id.menuCharMagicText)).setText("" + playerCharacter.getMaxMagic());
        ((TextView) findViewById(R.id.menuCharStrength)).setText("STR " + playerCharacter.getStrength());
        ((TextView) findViewById(R.id.menuCharWillpower)).setText("WIL " + playerCharacter.getWillpower());
        ((TextView) findViewById(R.id.menuCharDefense)).setText("DEF " + playerCharacter.getDefense());
        ((TextView) findViewById(R.id.menuCharResistance)).setText("RES " + playerCharacter.getResistance());
        ((TextView) findViewById(R.id.menuCharSpeed)).setText("SPD " + playerCharacter.getSpeed());
    }

    //
    private void setLevelUpButtonsVisibility(int visibility){
        findViewById(R.id.menuCharNameChangeButton).setVisibility(visibility);
        findViewById(R.id.menuCharHealthAdd).setVisibility(visibility);
        //findViewById(R.id.menuCharHealthText).setVisibility(visibility);
        findViewById(R.id.menuCharMagicAdd).setVisibility(visibility);
        //findViewById(R.id.menuCharMagicText).setVisibility(visibility);
        findViewById(R.id.menuCharStrengthAdd).setVisibility(visibility);
        findViewById(R.id.menuCharWillpowerAdd).setVisibility(visibility);
        findViewById(R.id.menuCharDefenseAdd).setVisibility(visibility);
        findViewById(R.id.menuCharResistanceAdd).setVisibility(visibility);
        findViewById(R.id.menuCharSpeedAdd).setVisibility(visibility);
        findViewById(R.id.menuCharPoints).setVisibility(visibility);
    }

    //
    public void loadClick(View loadButton){

        if (findViewById(R.id.menuLayoutList).getVisibility() == View.VISIBLE){

            // only show player if one was being created
            if (((Button) findViewById(R.id.readyButton)).getText().equals("Start")) setPlayerVisibility(View.VISIBLE);


            setLoadedPlayerListVisibility(View.INVISIBLE);

            findViewById(R.id.readyButton).setEnabled(true);


            //findViewById(R.id.menuPlayerListEmpty).setVisibility(View.INVISIBLE);

        } else {
            setPlayerVisibility(View.INVISIBLE);
            setLoadedPlayerListVisibility(View.VISIBLE);

            findViewById(R.id.readyButton).setEnabled(false);

            if (storedPlayerCharacters.isEmpty()) findViewById(R.id.menuPlayerListEmpty).setVisibility(View.VISIBLE);
            else findViewById(R.id.menuPlayerListEmpty).setVisibility(View.INVISIBLE);

        }
    }

    //
    public void leaderboardClick(View leaderButton){
        Intent newPage = new Intent(this, LeaderboardPage.class);
        startActivity(newPage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_page, menu);
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
