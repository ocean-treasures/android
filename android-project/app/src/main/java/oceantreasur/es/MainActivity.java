package oceantreasur.es;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import oceantreasur.es.animations.AnimationController;
import oceantreasur.es.ui.FragmentTags;
import oceantreasur.es.ui.StartGameFragment;

public class MainActivity extends AppCompatActivity {

    private android.app.FragmentManager fragmentManagaer;
    private android.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManagaer = getFragmentManager();

        RelativeLayout background = (RelativeLayout) findViewById(R.id.fish_background);

        AnimationController animationController = new AnimationController(background);

        StartGameFragment startGameFragment = new StartGameFragment();
        attachFragment(startGameFragment, FragmentTags.START_GAME_FRAGMENT_TAG);

    }

    public void attachFragment(android.app.Fragment fragment, String tag) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag)
                            .commit();
    }
}
