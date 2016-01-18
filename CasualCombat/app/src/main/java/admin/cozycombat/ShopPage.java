package admin.cozycombat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ShopPage extends AppCompatActivity {

    // TODO
    // save button greys out (and disabled) after being pressed.
    // maybe too expensive buttons also greyed out.
    // after new purchase save button is enabled again.
    // never show weaker gear, since gear is replaced by what is bought

    // maybe the three columns of buyable things corresponds to spells/items/gear
    // so for each 2 are buyable after each fight

    PlayerCharacter playerCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);


        Intent previousPage = getIntent();
        playerCharacter = previousPage.getParcelableExtra(TitlePage.KEY_PLAYER);

        if (playerCharacter.isDead()) {
            findViewById(R.id.shopLayoutCharacter).setVisibility(View.INVISIBLE);
            findViewById(R.id.shopLayoutMiddle).setVisibility(View.INVISIBLE);
            findViewById(R.id.shopLayoutShop).setVisibility(View.INVISIBLE);
            findViewById(R.id.shopDeathText).setVisibility(View.VISIBLE);
        } else {
            setCharacterAvatar();
            updatePlayerSkillViews();

            updateShop();
        }
    }

    //
    public void addClick(View addButton){

        switch(addButton.getId()){
            case R.id.shopCharHealthAdd:
                playerCharacter.addHealth();
                break;
            case R.id.shopCharMagicAdd:
                playerCharacter.addMagic();
                break;
            case R.id.shopCharStrengthAdd:
                playerCharacter.addStrength();
                break;
            case R.id.shopCharWillpowerAdd:
                playerCharacter.addWillpower();
                break;
            case R.id.shopCharDefenseAdd:
                playerCharacter.addDefense();
                break;
            case R.id.shopCharResistanceAdd:
                playerCharacter.addResistance();
                break;
            case R.id.shopCharSpeedAdd:
                playerCharacter.addSpeed();
                break;
        }

        playerCharacter.subtractLevelPoint();
        updatePlayerSkillViews();

        if (playerCharacter.finishedLevelUp()) {
            findViewById(R.id.saveButton).setEnabled(true);
            findViewById(R.id.nextButton).setEnabled(true);
            setLevelUpButtonsVisibility(View.INVISIBLE);
        }
    }

    //
    private void updatePlayerSkillViews(){
        ProgressBar shopCharHealth = (ProgressBar) findViewById(R.id.shopCharHealth);
        shopCharHealth.setMax(playerCharacter.getMaxHealth());
        shopCharHealth.setProgress(playerCharacter.getHealth());

        ProgressBar shopCharMagic = (ProgressBar) findViewById(R.id.shopCharMagic);
        shopCharMagic.setMax(playerCharacter.getMaxMagic());
        shopCharMagic.setProgress(playerCharacter.getMagic());

        ((TextView) findViewById(R.id.shopCharName)).setText(playerCharacter.getName());
        ((TextView) findViewById(R.id.shopCharHealthText)).setText("" + playerCharacter.getMaxHealth());
        ((TextView) findViewById(R.id.shopCharMagicText)).setText("" + playerCharacter.getMaxMagic());
        ((TextView) findViewById(R.id.shopCharLevel)).setText("LEVEL " + playerCharacter.getLevel());
        ((TextView) findViewById(R.id.shopCharStrength)).setText("STR " + playerCharacter.getStrength());
        ((TextView) findViewById(R.id.shopCharWillpower)).setText("WIL " + playerCharacter.getWillpower());
        ((TextView) findViewById(R.id.shopCharDefense)).setText("DEF " + playerCharacter.getDefense());
        ((TextView) findViewById(R.id.shopCharResistance)).setText("RES " + playerCharacter.getResistance());
        ((TextView) findViewById(R.id.shopCharSpeed)).setText("SPD " + playerCharacter.getSpeed());

        ((TextView) findViewById(R.id.shopCharPoints)).setText("Points left to spend: " + playerCharacter.getLevelPoints());
    }

    //
    private void setLevelUpButtonsVisibility(int visibility){
        findViewById(R.id.shopCharNameChangeButton).setVisibility(visibility);
        findViewById(R.id.shopCharHealthAdd).setVisibility(visibility);
        findViewById(R.id.shopCharMagicAdd).setVisibility(visibility);
        findViewById(R.id.shopCharStrengthAdd).setVisibility(visibility);
        findViewById(R.id.shopCharWillpowerAdd).setVisibility(visibility);
        findViewById(R.id.shopCharDefenseAdd).setVisibility(visibility);
        findViewById(R.id.shopCharResistanceAdd).setVisibility(visibility);
        findViewById(R.id.shopCharSpeedAdd).setVisibility(visibility);
        findViewById(R.id.shopCharPoints).setVisibility(visibility);
    }

    //
    private void updateShop(){
        // check if enough gold
    }

    //
    public void usableClick(View usableView){

    }

    //
    public void equippableClick(View equippableView){

    }

    //
    public void spellClick(View spellView){

    }

    //
    public void saveClick(View saveButton){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, TitlePage.KEY_PREFS, MODE_PRIVATE);;
        complexPreferences.putObject(playerCharacter.getName(), playerCharacter);
        complexPreferences.commit();
        findViewById(R.id.saveButton).setEnabled(false);
    }

    //
    public void nextClick(View nextButton){
        // TODO also finish prev activity
        finish();
    }

    //
    public void titleClick(View deathText){
        // TODO also finish prev activity
        finish();
    }

    //
    private void setCharacterAvatar(){
        ImageView avatar = (ImageView) findViewById(R.id.shopCharIcon);
        Drawable d = ContextCompat.getDrawable(getBaseContext(), R.drawable.avatar);
        d.mutate().setColorFilter(Color.parseColor(playerCharacter.getColorString()), PorterDuff.Mode.MULTIPLY);
        avatar.setImageDrawable(d);
    }

    //
    public void secretClick(View characterAvatar){
        playerCharacter.changeColorString();
        setCharacterAvatar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_page, menu);
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
