package oceantreasur.es;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import oceantreasur.es.R;
import oceantreasur.es.animations.AnimationController;
import oceantreasur.es.fragments.FragmentTags;
import oceantreasur.es.fragments.MainFragment;
import oceantreasur.es.network.model.NextWordResponse;

public class MainActivity extends AppCompatActivity {

    private android.app.FragmentManager fragmentManagaer;
    private android.app.FragmentTransaction fragmentTransaction;
    private NextWordResponse nextWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragments);

        fragmentManagaer = getFragmentManager();

        RelativeLayout background = (RelativeLayout) findViewById(R.id.fish_background);
        AnimationController animationController = new AnimationController(background);

        MainFragment mainFragment = new MainFragment();
        attachFragment(mainFragment, FragmentTags.MAIN_FRAGMENT_TAG);

    }

    public void attachFragment(android.app.Fragment fragment, String tag) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag)
                            .commit();
    }

}
