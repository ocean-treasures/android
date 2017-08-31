package oceantreasur.es;


import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.seismic.ShakeDetector;

import java.net.InetAddress;

import oceantreasur.OceanTreasuresApplication;
import oceantreasur.es.animations.AnimationController;

import oceantreasur.es.network.OceanTreasuresConstants;
import oceantreasur.es.ui.StartGameFragment;

public class MainActivity extends AppCompatActivity {

    private NsdManager mNsdManager;
    private NsdManager.DiscoveryListener mDiscoveryListener;
    private NsdManager.ResolveListener mResolveListener;
    private NsdServiceInfo mServiceInfo;
    public String mRPiAddress;

    // The NSD service type that the RPi exposes.
    private static final String SERVICE_TYPE = "_workstation._tcp.";

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

        mRPiAddress = "";
        mNsdManager = (NsdManager)(getApplicationContext().getSystemService(Context.NSD_SERVICE));
        initializeResolveListener();
        initializeDiscoveryListener();
        Log.d("NSD", "Start");
        mNsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);
    }

    private void initializeDiscoveryListener() {

        // Instantiate a new DiscoveryListener
        mDiscoveryListener = new NsdManager.DiscoveryListener() {

            //  Called as soon as service discovery begins.
            @Override
            public void onDiscoveryStarted(String regType) {
            }

            @Override
            public void onServiceFound(NsdServiceInfo service) {
                // A service was found!  Do something with it.
                String name = service.getServiceName();
                String type = service.getServiceType();
                Log.d("NSD", "Service Name=" + name);
                Log.d("NSD", "Service Type=" + type);
                if (type.equals(SERVICE_TYPE) && name.contains(OceanTreasuresConstants.NSD_LOCAL_DOMAIN_NAME)) {
                    Log.d("NSD", "Service Found @ '" + name + "'");
                    mNsdManager.resolveService(service, mResolveListener);
                }
            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                // When the network service is no longer available.
                // Internal bookkeeping code goes here.
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                mNsdManager.stopServiceDiscovery(this);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                mNsdManager.stopServiceDiscovery(this);
            }
        };
    }

    private void initializeResolveListener() {
        mResolveListener = new NsdManager.ResolveListener() {

            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                // Called when the resolve fails.  Use the error code to debug.
                Log.e("NSD", "Resolve failed" + errorCode);
            }

            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {
                mServiceInfo = serviceInfo;

                // Port is being returned as 9. Not needed.
                //int port = mServiceInfo.getPort();

                InetAddress host = mServiceInfo.getHost();
                String address = host.getHostAddress();
                Log.d("NSD", "Resolved address = " + address);
                mRPiAddress = address;
                OceanTreasuresApplication.rebuildRetrofitIntstance("http://"+mRPiAddress+"/game/");
                Toast.makeText(MainActivity.this, "raspberry ip: " + mRPiAddress, Toast.LENGTH_SHORT).show();
                mNsdManager.stopServiceDiscovery(mDiscoveryListener);
            }
        };
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
