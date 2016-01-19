package admin.cozycombat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class LeaderboardPage extends AppCompatActivity {

    // TODO possibility for login online to share scores
    // maybe local and global

    ArrayList<PlayerCharacter> storedPlayerCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prepareStoredPlayerList();
        handleListTextVisibility();
    }

    //
    private void prepareStoredPlayerList(){
        storedPlayerCharacters = new ArrayList<>();
        Map<String, ?> keys = getSharedPreferences(TitlePage.KEY_PREFS, MODE_PRIVATE).getAll();
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, TitlePage.KEY_PREFS, MODE_PRIVATE);
        for (String key : keys.keySet()) {
            PlayerCharacter pc = complexPreferences.getObject(key, PlayerCharacter.class);
            pc.restoreAfterSave();
            storedPlayerCharacters.add(pc);
        }

        final PlayerCharacterAdapter adapter = new PlayerCharacterAdapter(this, R.layout.player_character_list, R.id.listCharName, storedPlayerCharacters);
        ListView storedPlayerListView = (ListView) findViewById(R.id.leaderboardPlayerList);
        storedPlayerListView.setAdapter(adapter);
        storedPlayerListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                getSharedPreferences(TitlePage.KEY_PREFS, MODE_PRIVATE).edit().remove(storedPlayerCharacters.get(position).getName()).commit();
                storedPlayerCharacters.remove(position);
                adapter.notifyDataSetChanged();
                handleListTextVisibility();
                return false;
            }
        });
    }

    //
    private void handleListTextVisibility(){
        if (storedPlayerCharacters.isEmpty()) {
            findViewById(R.id.leaderboardPlayerListEmpty).setVisibility(View.VISIBLE);
            findViewById(R.id.leaderboardInfoText).setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.leaderboardPlayerListEmpty).setVisibility(View.INVISIBLE);
            findViewById(R.id.leaderboardInfoText).setVisibility(View.VISIBLE);
        }
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
        getMenuInflater().inflate(R.menu.menu_leaderboard_page, menu);
        return true;
    }
}
