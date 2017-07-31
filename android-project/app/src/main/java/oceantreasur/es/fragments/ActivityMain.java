package oceantreasur.es.fragments;


import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.net.SocketTimeoutException;

import oceantreasur.es.R;
import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.network.model.CheckAnswerRequest;
import oceantreasur.es.network.model.CheckAnswerResponse;
import oceantreasur.es.network.model.NextWordResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity {

    android.app.FragmentManager fragmentManagaer;
    android.app.FragmentTransaction fragmentTransaction;
    private NextWordResponse nextWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragments);

        fragmentManagaer = getFragmentManager();

        RelativeLayout background = (RelativeLayout) findViewById(R.id.fish_background);
        AnimationController animationController = new AnimationController(background);

        MainFragment mainFragment = new MainFragment();
        attachFragment(mainFragment, "MAIN_FRAGMENT");

    }

    public void attachFragment(android.app.Fragment fragment, String tag) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag)
                            .commit();
    }

    public void getNextWord() {
        Call<NextWordResponse> call = OceanTreasuresApplication.getApi().getNextWord();

        call.enqueue(new Callback<NextWordResponse>() {
            @Override
            public void onResponse(Call<NextWordResponse> call, Response<NextWordResponse> response) {

                if (response.code() == 404) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMain.this);

                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainFragment mf = new MainFragment();
                            attachFragment(mf, "MAIN_FRAGMENT");
                        }
                    });

                    final AlertDialog dialog = builder.create();
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogLayout = inflater.inflate(R.layout.alert_dialog, null);
                    dialog.setView(dialogLayout);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.show();

                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface d) {
                            Context context = OceanTreasuresApplication.getStaticContext();
                            ImageView image = (ImageView) dialog.findViewById(R.id.iv_dialog);

                            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                                    R.drawable.fish);

                            float imageWidthInPX = (float)image.getWidth();

                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(imageWidthInPX),
                                    Math.round(imageWidthInPX * (float)icon.getHeight() / (float)icon.getWidth()));

                            image.setLayoutParams(layoutParams);
                        }
                    });

                } else {
                    GameFragment gameFragment =  (GameFragment) fragmentManagaer.findFragmentByTag("GAME_FRAGMENT");

                    nextWord = response.body();
                    gameFragment.loadImages(nextWord);
                    gameFragment.setupProgressBar(nextWord.getProgress().getCurrent(), nextWord.getProgress().getMax());
                    gameFragment.setTextToTextView(nextWord.getWord().getWord().toString());

                }
            }

            @Override
            public void onFailure(Call<NextWordResponse> call, Throwable t) {
                Log.d("ZAX", "ERROR");

                if(t instanceof SocketTimeoutException){
                    Log.d("ZAX", "Server Timeout!");
                    getNextWord();
                }

            }
        });
    }

    public void checkAnswer(int wordId, int picId) {
        CheckAnswerRequest req = new CheckAnswerRequest(wordId, picId);
        Call<CheckAnswerResponse> call = OceanTreasuresApplication.getApi().checkAnswer(req);

        call.enqueue(new Callback<CheckAnswerResponse>() {
            @Override
            public void onResponse(Call<CheckAnswerResponse> call, Response<CheckAnswerResponse> response) {
                CheckAnswerResponse serverResponse = response.body();

                GameFragment gameFragment =  (GameFragment) fragmentManagaer.findFragmentByTag("GAME_FRAGMENT");

                gameFragment.chooseNextFragment(serverResponse);

                Log.d("ZAX", serverResponse.toString());
            }

            @Override
            public void onFailure(Call<CheckAnswerResponse> call, Throwable t) {
                Log.d("ZAX", "ERROR");
            }
        });
    }


}
