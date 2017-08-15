package oceantreasur.es.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import oceantreasur.es.MainActivity;
import oceantreasur.es.R;
import oceantreasur.es.network.NetworkManager;
import oceantreasur.es.network.model.CheckAnswerResponse;
import oceantreasur.es.network.model.NextWordResponse;
import oceantreasur.es.network.model.Picture;

import static oceantreasur.es.R.id.container;


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

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        fragmentManagaer = getFragmentManager();

        setupFragment(view);
        NetworkManager.getNextWord(new NetworkManager.SuccessListener<NextWordResponse>() {
            @Override
            public void onSuccess(NextWordResponse nextWord) {
                Fragment fragment = fragmentManagaer.findFragmentById(R.id.container);

                if (fragment instanceof GameFragment) {
                    GameFragment gameFragment = (GameFragment) fragment;

                    gameFragment.loadImages(nextWord);
                    gameFragment.setupProgressBar(nextWord.getProgress().getCurrent(), nextWord
                            .getProgress().getMax());
                    gameFragment.setTextToTextView(nextWord.getWord().getWord().toString());
                }
            }
        }, getActivity());

        return view;
    }

    public void setupFragment(View view) {
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.word = (TextView) view.findViewById(R.id.tv_word);

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = (ImageView) view.findViewById(ids[i]);
        }
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

                NetworkManager.checkAnswer(nextWord.getWord().getId(), pic.getId(), new
                        NetworkManager.SuccessListener<CheckAnswerResponse>() {
                    @Override
                    public void onSuccess(CheckAnswerResponse response) {

                        Fragment fragment = fragmentManagaer.findFragmentById(container);
                        if (fragment instanceof GameFragment) {
                            GameFragment gameFragment = (GameFragment) fragment;
                            gameFragment.chooseNextFragment(response);
                        }

                        Log.d("ZAX", response.toString());

                    }
                }, getActivity());
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

        Fragment nextFragmentToDisplay = response.isCorrect() ? new CorrectAnswerFragment() : new
                WrongAnswerFragment();

        nextFragmentToDisplay.setArguments(data);
        ((MainActivity) getActivity()).attachFragment(nextFragmentToDisplay);
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

    private void disableImageClicks() {
        for (ImageView iv : imageViews) {
            iv.setClickable(false);
            iv.setEnabled(false);
        }
    }
}
