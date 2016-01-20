package admin.cozycombat;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import item.EquippableItem;
import item.Item;
import item.UsableItem;
import move.Move;

public class ShopPage extends AppCompatActivity {

    // TODO
    // save button greys out (and disabled) after being pressed.
    // maybe too expensive buttons also greyed out.
    // after new purchase save button is enabled again.
    // never show weaker gear, since gear is replaced by what is bought

    // maybe the three columns of buyable things corresponds to spells/items/gear
    // so for each 2 are buyable after each fight

    private static final int RESTORE_HEALTH_PRICE = 1;
    private static final int RESTORE_MAGIC_PRICE = 1;

    private PlayerCharacter playerCharacter;

    private boolean firstVisit;

    //
    private UsableItem buyableUsableItem;
    private EquippableItem buyableEquippableItem;
    private Move buyableSpell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);

        System.err.println("LISTEN UP I CAME FROM : " + this.getCallingActivity());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstVisit = true;

        Intent previousPage = getIntent();
        playerCharacter = previousPage.getParcelableExtra(TitlePage.KEY_PLAYER);

        preparePage();
    }

    private void preparePage(){
        if (firstVisit){
            nextClick(null);
        } else {
            if (playerCharacter.isDead()) {
                findViewById(R.id.shopLayoutCharacter).setVisibility(View.INVISIBLE);
                findViewById(R.id.shopLayoutEquipment).setVisibility(View.INVISIBLE);
                findViewById(R.id.shopLayoutMiddle).setVisibility(View.INVISIBLE);
                findViewById(R.id.shopLayoutShop).setVisibility(View.INVISIBLE);
                findViewById(R.id.shopDeathText).setVisibility(View.VISIBLE);
            } else {
                setCharacterAvatar();
                updatePlayerSkillViews();
                updatePlayerEquipmentViews();

                initializeShop();
            }
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
        updatePlayerBars();

        ((TextView) findViewById(R.id.shopCharName)).setText(playerCharacter.getName());
        ((TextView) findViewById(R.id.shopCharHealthText)).setText("" + playerCharacter.getHealth() + "/" + playerCharacter.getMaxHealth());
        ((TextView) findViewById(R.id.shopCharMagicText)).setText("" + playerCharacter.getMagic() + "/" + playerCharacter.getMaxMagic());
        ((TextView) findViewById(R.id.shopCharLevel)).setText("LEVEL " + playerCharacter.getLevel());
        ((TextView) findViewById(R.id.shopCharStrength)).setText("STR " + playerCharacter.getStrength());
        ((TextView) findViewById(R.id.shopCharWillpower)).setText("WIL " + playerCharacter.getWillpower());
        ((TextView) findViewById(R.id.shopCharDefense)).setText("DEF " + playerCharacter.getDefense());
        ((TextView) findViewById(R.id.shopCharResistance)).setText("RES " + playerCharacter.getResistance());
        ((TextView) findViewById(R.id.shopCharSpeed)).setText("SPD " + playerCharacter.getSpeed());

        ((TextView) findViewById(R.id.shopCharPoints)).setText("Points left to spend: " + playerCharacter.getLevelPoints());
    }

    //
    private void updatePlayerBars(){
        ProgressBar shopCharHealth = (ProgressBar) findViewById(R.id.shopCharHealth);
        shopCharHealth.setMax(playerCharacter.getMaxHealth());
        shopCharHealth.setProgress(playerCharacter.getHealth());
        ((TextView) findViewById(R.id.shopCharHealthText)).setText("" + playerCharacter.getHealth() + "/" + playerCharacter.getMaxHealth());

        ProgressBar shopCharMagic = (ProgressBar) findViewById(R.id.shopCharMagic);
        shopCharMagic.setMax(playerCharacter.getMaxMagic());
        shopCharMagic.setProgress(playerCharacter.getMagic());
        ((TextView) findViewById(R.id.shopCharMagicText)).setText("" + playerCharacter.getMagic() + "/" + playerCharacter.getMaxMagic());
    }

    //
    private void updatePlayerEquipmentViews(){
        if (playerCharacter.getWeapon() == null)
            ((TextView) findViewById(R.id.shopCharWeapon)).setText("Weapon missing");
        else
            ((TextView) findViewById(R.id.shopCharWeapon)).setText(playerCharacter.getWeapon().getName() + "\n" + playerCharacter.getWeapon().getStatBonusAsString());

        if (playerCharacter.getArmor() == null)
            ((TextView) findViewById(R.id.shopCharArmor)).setText("Armor missing");
        else
            ((TextView) findViewById(R.id.shopCharArmor)).setText(playerCharacter.getArmor().getName() + "\n" + playerCharacter.getArmor().getStatBonusAsString());

        if (playerCharacter.getBoots() == null)
            ((TextView) findViewById(R.id.shopCharBoots)).setText("Boots missing");
        else
            ((TextView) findViewById(R.id.shopCharBoots)).setText(playerCharacter.getBoots().getName() + "\n" + playerCharacter.getBoots().getStatBonusAsString());
    }

    //
    private void setLevelUpButtonsVisibility(int visibility){
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
    private void initializeShop(){

        // TODO randomly get buyable items and spell
        // TODO check for spell (and equippable item?) that playerCharacter does not already have them
        buyableUsableItem = (UsableItem) Item.findItemById(Item.HERB);
        buyableEquippableItem = (EquippableItem) Item.findItemById(Item.WOODEN_SWORD);
        buyableSpell = Move.findMoveById(Move.SHOCKWAVE);

        displayBuyable(R.id.shopPriceUsable, R.id.shopNameUsable, R.id.shopInfoUsable, buyableUsableItem);
        displayBuyable(R.id.shopPriceEquippable, R.id.shopNameEquippable, R.id.shopInfoEquippable, buyableEquippableItem);
        displayBuyable(R.id.shopPriceSpell, R.id.shopNameSpell, R.id.shopInfoSpell, buyableSpell);

        updateShop();
    }

    //
    private void displayBuyable(int priceId, int nameId, int infoId, Item item){
        ((TextView) findViewById(priceId)).setText("" + item.getPrice() + "G");
        ((TextView) findViewById(nameId)).setText(item.getName());
        ((TextView) findViewById(infoId)).setText(item.getInfo());
    }

    //
    private void displayBuyable(int priceId, int nameId, int infoId, Move spell){
        ((TextView) findViewById(priceId)).setText("" + spell.getPrice() + "G");
        ((TextView) findViewById(nameId)).setText(spell.getName());
        ((TextView) findViewById(infoId)).setText(spell.getInfo());
    }

    //
    private void updateShop(){
        // set character gold view
        ((TextView) findViewById(R.id.shopCharMoney)).setText("Current gold: " + playerCharacter.getMoney());

        // setup restore health and magic buttons
        if (playerCharacter.getMoney() < RESTORE_HEALTH_PRICE || playerCharacter.getHealth() == playerCharacter.getMaxHealth())
            findViewById(R.id.shopHealthRegen).setEnabled(false);
        else findViewById(R.id.shopHealthRegen).setEnabled(true);
        if (playerCharacter.getMoney() < RESTORE_MAGIC_PRICE || playerCharacter.getMagic() == playerCharacter.getMaxMagic())
            findViewById(R.id.shopMagicRegen).setEnabled(false);
        else findViewById(R.id.shopMagicRegen).setEnabled(true);

        // check enough gold for buyable items and spell, and check if already have it
        TextView usableWarningText = (TextView) findViewById(R.id.shopWarningUsable);
        if (buyableUsableItem != null) {
            if (playerCharacter.getMoney() < buyableUsableItem.getPrice()) {
                findViewById(R.id.shopLayoutUsable).setBackgroundColor(Color.parseColor("#996666"));
                usableWarningText.setVisibility(View.VISIBLE);
                usableWarningText.setText("INSUFFICIENT GOLD");
                findViewById(R.id.shopLayoutUsableInner).setVisibility(View.INVISIBLE);
            } else {
                usableWarningText.setVisibility(View.INVISIBLE);
            }
        // sold out
        } else {
            usableWarningText.setVisibility(View.VISIBLE);
            findViewById(R.id.shopLayoutUsableInner).setVisibility(View.INVISIBLE);
        }
        TextView equippableWarningText = (TextView) findViewById(R.id.shopWarningEquippable);
        if (buyableEquippableItem != null) {
            if (playerCharacter.getMoney() < buyableEquippableItem.getPrice()) {
                findViewById(R.id.shopLayoutEquippable).setBackgroundColor(Color.parseColor("#996666"));
                equippableWarningText.setVisibility(View.VISIBLE);
                equippableWarningText.setText("INSUFFICIENT GOLD");
                findViewById(R.id.shopLayoutEquippableInner).setVisibility(View.INVISIBLE);
            } else if (playerCharacter.alreadyHasEquipment(buyableEquippableItem)) {
                findViewById(R.id.shopLayoutEquippable).setBackgroundColor(Color.parseColor("#669966"));
                equippableWarningText.setVisibility(View.VISIBLE);
                equippableWarningText.setText("ALREADY OWNED");
                findViewById(R.id.shopLayoutEquippableInner).setVisibility(View.INVISIBLE);

            // otherwise buyable as intended
            } else {
                equippableWarningText.setVisibility(View.INVISIBLE);
            }
        // sold out
        } else {
            equippableWarningText.setVisibility(View.VISIBLE);
            findViewById(R.id.shopLayoutEquippableInner).setVisibility(View.INVISIBLE);
        }
        TextView spellWarningText = (TextView) findViewById(R.id.shopWarningSpell);
        if (buyableSpell != null) {
            if (playerCharacter.getMoney() < buyableSpell.getPrice()) {
                findViewById(R.id.shopLayoutSpell).setBackgroundColor(Color.parseColor("#996666"));
                spellWarningText.setVisibility(View.VISIBLE);
                spellWarningText.setText("INSUFFICIENT GOLD");
                findViewById(R.id.shopLayoutSpellInner).setVisibility(View.INVISIBLE);
            } else if (playerCharacter.getSpells().contains(buyableSpell.getId())) {
                findViewById(R.id.shopLayoutSpell).setBackgroundColor(Color.parseColor("#669966"));
                spellWarningText.setVisibility(View.VISIBLE);
                spellWarningText.setText("ALREADY OWNED");
                findViewById(R.id.shopLayoutSpellInner).setVisibility(View.INVISIBLE);

            // otherwise buyable as intended
            } else {
                    spellWarningText.setVisibility(View.INVISIBLE);
            }
        // sold out
        } else {
            spellWarningText.setVisibility(View.VISIBLE);
            findViewById(R.id.shopLayoutSpellInner).setVisibility(View.INVISIBLE);
        }
    }

    //
    public void restoreHealthClick(View restoreHealthButton){
        playerCharacter.restoreHealthFully();
        playerCharacter.subtractMoney(RESTORE_HEALTH_PRICE);
        updatePlayerBars();
        updateShop();
        if (findViewById(R.id.nextButton).isEnabled()) findViewById(R.id.saveButton).setEnabled(true);
    }

    //
    public void restoreMagicClick(View restoreMagicButton){
        playerCharacter.restoreMagicFully();
        playerCharacter.subtractMoney(RESTORE_MAGIC_PRICE);
        updatePlayerBars();
        updateShop();
        if (findViewById(R.id.nextButton).isEnabled()) findViewById(R.id.saveButton).setEnabled(true);
    }

    //
    public void usableClick(View usableView){
        if (buyableUsableItem != null) {
            if (playerCharacter.getMoney() >= buyableUsableItem.getPrice()){
                playerCharacter.subtractMoney(buyableUsableItem.getPrice());
                playerCharacter.addUsableItem(buyableUsableItem);
                buyableUsableItem = null;
            }
        }
        updateShop();
    }

    //
    public void equippableClick(View equippableView){
        if (buyableEquippableItem != null) {
            if (playerCharacter.getMoney() >= buyableEquippableItem.getPrice()){
                playerCharacter.subtractMoney(buyableEquippableItem.getPrice());
                playerCharacter.equipItem(buyableEquippableItem);
                buyableEquippableItem = null;
                updatePlayerSkillViews();
                updatePlayerEquipmentViews();
            }
        }
        updateShop();
    }

    //
    public void spellClick(View spellView){
        if (buyableSpell != null) {
            if (playerCharacter.getMoney() >= buyableSpell.getPrice()){
                playerCharacter.subtractMoney(buyableSpell.getPrice());
                playerCharacter.addSpell(buyableSpell);
                buyableSpell = null;
            }
        }
        updateShop();
    }

    //
    public void saveClick(View saveButton){
        playerCharacter.prepareForSave();
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, TitlePage.KEY_PREFS, MODE_PRIVATE);;
        complexPreferences.putObject(playerCharacter.getName(), playerCharacter);
        complexPreferences.commit();
        findViewById(R.id.saveButton).setEnabled(false);
        playerCharacter.restoreAfterSave();
    }

    //
    public void nextClick(View nextButton){
        Intent newPage = new Intent(this, PlayPage.class);
        newPage.putExtra(TitlePage.KEY_PLAYER, playerCharacter);
        startActivityForResult(newPage, TitlePage.REQUEST_CODE_PLAY_PAGE);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("YO THIS IS THE REQUEST CODE: " + requestCode);
        System.out.println("YO THIS IS THE RESULT CODE: " + resultCode);

        // can come here from PlayPage TODO or InfoPage
        if (requestCode == TitlePage.REQUEST_CODE_PLAY_PAGE){
            if (resultCode == TitlePage.RESULT_EXIT) {
                setResult(resultCode);
                finish();
            }
            if (resultCode == RESULT_CANCELED){
                if (firstVisit) {
                    setResult(resultCode);
                    finish();
                }
            }
            if (resultCode == RESULT_OK) {
                firstVisit = false;
                playerCharacter = data.getExtras().getParcelable(TitlePage.KEY_PLAYER);
                preparePage();
            }
        }
    }

    //
    @Override
    public void onBackPressed() {
        if (playerCharacter.isDead()){
            setResult(TitlePage.RESULT_EXIT);
            finish();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You are about to leave this page.\nUnsaved progress will be lost.");
        builder.setPositiveButton("Return to title screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(RESULT_OK, null);
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

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_page, menu);
        return true;
    }
}
