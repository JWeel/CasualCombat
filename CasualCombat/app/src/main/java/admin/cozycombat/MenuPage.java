package admin.cozycombat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPage extends AppCompatActivity {

    // TODO
    // alternative startup would be having a set amount of points that can be distributed
    // over various skills
    // this also then on ShopPage

    PlayerCharacter playerCharacter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);



        SharedPreferences prefs = getSharedPreferences("PREFS", MODE_PRIVATE);

        ((Button) findViewById(R.id.readyButton)).setText("NEW");

        setPlayerVisibility(View.INVISIBLE);
        setLevelUpButtonsVisibility(View.INVISIBLE);

    }

    //
    private void setPlayerVisibility(int visibility){
        findViewById(R.id.menuCharIcon).setVisibility(visibility);
        findViewById(R.id.menuCharName).setVisibility(visibility);
        findViewById(R.id.menuCharHealth).setVisibility(visibility);
        findViewById(R.id.menuCharMagic).setVisibility(visibility);
        findViewById(R.id.menuCharLevel).setVisibility(visibility);
        findViewById(R.id.menuCharStrength).setVisibility(visibility);
        findViewById(R.id.menuCharWillpower).setVisibility(visibility);
        findViewById(R.id.menuCharDefense).setVisibility(visibility);
        findViewById(R.id.menuCharResistance).setVisibility(visibility);
        findViewById(R.id.menuCharSpeed).setVisibility(visibility);
    }

    //
    public void readyClick(View readyButton){

        if (playerCharacter != null && playerCharacter.finishedLevelUp()) {

            // check if hero name already exists in database

            Intent newPage = new Intent(this, PlayPage.class);
            startActivity(newPage);
        } else {

            ((Button) readyButton).setText("Start");
            readyButton.setEnabled(false);

            // prepare creatable character
            playerCharacter = new PlayerCharacter();

            setPlayerVisibility(View.VISIBLE);
            setLevelUpButtonsVisibility(View.VISIBLE);

        }
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

        playerCharacter.subtractLevelPoint();
        ((TextView) findViewById(R.id.menuCharPoints)).setText("Points left to spend: " + playerCharacter.getLevelPoints());
        if (playerCharacter.finishedLevelUp()) {
            findViewById(R.id.readyButton).setEnabled(true);
            setLevelUpButtonsVisibility(View.INVISIBLE);
        }
    }

    //
    private void setLevelUpButtonsVisibility(int visibility){
        findViewById(R.id.menuCharNameEdit).setVisibility(visibility);
        findViewById(R.id.menuCharHealthAdd).setVisibility(visibility);
        findViewById(R.id.menuCharHealthText).setVisibility(visibility);
        findViewById(R.id.menuCharMagicAdd).setVisibility(visibility);
        findViewById(R.id.menuCharMagicText).setVisibility(visibility);
        findViewById(R.id.menuCharStrengthAdd).setVisibility(visibility);
        findViewById(R.id.menuCharWillpowerAdd).setVisibility(visibility);
        findViewById(R.id.menuCharDefenseAdd).setVisibility(visibility);
        findViewById(R.id.menuCharResistanceAdd).setVisibility(visibility);
        findViewById(R.id.menuCharSpeedAdd).setVisibility(visibility);
        findViewById(R.id.menuCharPoints).setVisibility(visibility);
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
