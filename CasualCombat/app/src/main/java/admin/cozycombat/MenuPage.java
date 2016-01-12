package admin.cozycombat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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



        }
    }

    //
    public void addHealthClick(View addHealthButton){
        playerCharacter.subtractLevelPoint();
        if (playerCharacter.finishedLevelUp()) findViewById(R.id.readyButton).setEnabled(true);
        // hide level up buttons TODO
    }

    //
    public void addMagicClick(View addMagicButton){
        playerCharacter.subtractLevelPoint();
        if (playerCharacter.finishedLevelUp()) findViewById(R.id.readyButton).setEnabled(true);
    }

    //
    public void addStrengthClick(View addStrengthButton){
        playerCharacter.subtractLevelPoint();
        if (playerCharacter.finishedLevelUp()) findViewById(R.id.readyButton).setEnabled(true);
    }

    //
    public void addWillpowerClick(View addWillpowerButton){
        playerCharacter.subtractLevelPoint();
        if (playerCharacter.finishedLevelUp()) findViewById(R.id.readyButton).setEnabled(true);
    }

    //
    public void addDefenseClick(View addDefenseButton){
        playerCharacter.subtractLevelPoint();
        if (playerCharacter.finishedLevelUp()) findViewById(R.id.readyButton).setEnabled(true);
    }

    //
    public void addResistanceClick(View addResistanceButton){
        playerCharacter.subtractLevelPoint();
        if (playerCharacter.finishedLevelUp()) findViewById(R.id.readyButton).setEnabled(true);
    }

    //
    public void addSpeedClick(View addSpeedButton){
        playerCharacter.subtractLevelPoint();
        if (playerCharacter.finishedLevelUp()) findViewById(R.id.readyButton).setEnabled(true);
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
