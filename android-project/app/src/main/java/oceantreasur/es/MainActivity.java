package oceantreasur.es;


import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.squareup.seismic.ShakeDetector;

import oceantreasur.es.animations.AnimationController;

import oceantreasur.es.network.OceanTreasuresConstants;
import oceantreasur.es.ui.StartGameFragment;

public class MainActivity extends AppCompatActivity {

    private android.app.FragmentManager fragmentManagaer;
    private android.app.FragmentTransaction fragmentTransaction;

    private int shakes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManagaer = getFragmentManager();

        RelativeLayout background = (RelativeLayout) findViewById(R.id.fish_background);

        AnimationController animationController = new AnimationController(background);
        animationController.animateFishes();

        StartGameFragment startGameFragment = new StartGameFragment();
        attachFragment(startGameFragment);

    }

    public void attachFragment(android.app.Fragment fragment) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment)
                            .commit();
        shakes = 0;
    }

    private ShakeDetector shakeDetector = new ShakeDetector(new ShakeDetector.Listener() {
        @Override
        public void hearShake() {
            if(!OceanTreasuresConstants.IS_MOCK){
                shakes++;
                if(shakes == OceanTreasuresConstants.NUMBER_OF_SHAKES_REQUIRED){
                    startActivity(new Intent(MainActivity.this, SecretActivity.class));
                    shakes = 0;
                }
            }
        }
    });

    @Override
    protected void onStart() {
        super.onStart();
        shakeDetector.start((SensorManager) getSystemService(Context.SENSOR_SERVICE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        shakeDetector.stop();
    }
}
