package oceantreasur.es.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.SocketTimeoutException;

import oceantreasur.es.MainActivity;
import oceantreasur.es.R;
import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.network.model.CheckAnswerRequest;
import oceantreasur.es.network.model.CheckAnswerResponse;
import oceantreasur.es.network.model.NextWordResponse;
import oceantreasur.es.network.model.Picture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GameFragment extends Fragment {

    private static final int STEP_SIZE = 10;

    private String selectedPictureUrl;

    private ProgressBar progressBar;

    private int[] ids = {R.id.iv_1,
            R.id.iv_2,
            R.id.iv_3,
            R.id.iv_4};

    private ImageView[] imageViews = new ImageView[ids.length];

    private NextWordResponse nextWord;

    private TextView word;

    private android.app.FragmentManager fragmentManagaer;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        fragmentManagaer = getFragmentManager();

        setupActivity(view);
        getNextWord();

        return view;
    }

    public void setupActivity(View view) {
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.word = (TextView) view.findViewById(R.id.tv_word);

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = (ImageView) view.findViewById(ids[i]);
        }
    }

    public void getNextWord() {
        Call<NextWordResponse> call = OceanTreasuresApplication.getApi().getNextWord();

        call.enqueue(new Callback<NextWordResponse>() {
            @Override
            public void onResponse(Call<NextWordResponse> call, Response<NextWordResponse> response) {

                if (response.code() == 404) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            StartGameFragment mf = new StartGameFragment();
                            ((MainActivity)getActivity()).attachFragment(mf, FragmentTags.START_GAME_FRAGMENT_TAG);
                        }
                    });

                    final AlertDialog dialog = builder.create();
                    LayoutInflater inflater = getActivity().getLayoutInflater();
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
                    GameFragment gameFragment =  (GameFragment) fragmentManagaer.findFragmentByTag(FragmentTags.GAME_FRAGMENT_TAG);

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

                GameFragment gameFragment =  (GameFragment) fragmentManagaer.findFragmentByTag(FragmentTags.GAME_FRAGMENT_TAG);

                gameFragment.chooseNextFragment(serverResponse);

                Log.d("ZAX", serverResponse.toString());
            }

            @Override
            public void onFailure(Call<CheckAnswerResponse> call, Throwable t) {
                Log.d("ZAX", "ERROR");
            }
        });
    }

    public void setupProgressBar(int cur, int max) {
        progressBar.setMax(max * STEP_SIZE);
        progressBar.setProgress(cur * STEP_SIZE);
    }

    public void loadImages(final NextWordResponse nextWord) {
        View.OnClickListener imageOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableImageClicks();
                Picture pic = (Picture) v.getTag();
                selectedPictureUrl = pic.getResolvedUrl();
                checkAnswer(nextWord.getWord().getId(), pic.getId());
            }
        };


        for (int i = 0; i < imageViews.length; i++) {
            Glide.with(this)
                    .load(nextWord.getPictures()[i].getResolvedUrl())
                    .fitCenter()
                    .into(imageViews[i]);

            imageViews[i].setTag(nextWord.getPictures()[i]);
            imageViews[i].setOnClickListener(imageOnClickListener);
        }
    }

    public void chooseNextFragment(CheckAnswerResponse response) {
        Bundle data = bundleExtras(response);

        Fragment nextFragmentToDisplay = response.isCorrect() ? new CorrectAnswerFragment() : new WrongAnswerFragment();
        String tag = response.isCorrect() ? FragmentTags.CORRECT_ANSWER_FRAGMENT_TAG : FragmentTags.WRONG_ANSWER_FRAGMENT_TAG;

        nextFragmentToDisplay.setArguments(data);
        ((MainActivity) getActivity()).attachFragment(nextFragmentToDisplay, tag);
    }

    private Bundle bundleExtras(CheckAnswerResponse response) {
        Bundle data = new Bundle();
        data.putString(BaseAnswerFragment.EXTRA_WORD, response.getWord());
        data.putString(BaseAnswerFragment.EXTRA_URL, selectedPictureUrl);
        data.putInt(BaseAnswerFragment.EXTRA_PROGRESS_CUR, response.getProgress().getCurrent());
        data.putInt(BaseAnswerFragment.EXTRA_PROGRESS_MAX, response.getProgress().getMax());

        return data;
    }

    public void setTextToTextView(String stringToBeVisualized) {
        word.setText(stringToBeVisualized);
    }

    private void disableImageClicks(){
        for(ImageView iv : imageViews){
            iv.setClickable(false);
            iv.setEnabled(false);
        }
    }
}
