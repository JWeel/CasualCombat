package admin.cozycombat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
        playerCharacter = previousPage.getParcelableExtra(MenuPage.KEY_PLAYER);

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
