package oceantreasur.es.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import oceantreasur.es.R;
import oceantreasur.es.network.model.CheckAnswerResponse;
import oceantreasur.es.network.model.NextWordResponse;
import oceantreasur.es.network.model.Picture;


public class GameFragment extends Fragment {

    private static final int STEP_SIZE = 10;
    private long mLastClickTime = 0;

    private String selectedPictureUrl;

    private ProgressBar progressBar;

    private int[] ids = {R.id.iv_1,
            R.id.iv_2,
            R.id.iv_3,
            R.id.iv_4};

    private ImageView[] imageViews = new ImageView[ids.length];

    private NextWordResponse nextWord;

    private TextView word;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_game, container, false);

        setupActivity(view);
        ((ActivityMain) getActivity()).getNextWord();

        return view;
    }

    public void setupActivity(View view) {
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
                ((ActivityMain) getActivity()).checkAnswer(nextWord.getWord().getId(), pic.getId());
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
        Bundle data = new Bundle();
        data.putString("EXTRA_WORD", response.getWord());
        data.putString("EXTRA_URL", selectedPictureUrl);
        data.putInt("EXTRA_PROGRESS_CURR", response.getProgress().getCurrent());
        data.putInt("EXTRA_PROGRESS_MAX", response.getProgress().getMax());

        if(response.isCorrect()) {
            CorrectAnswerFragment correctAnswerFragment = new CorrectAnswerFragment();
            correctAnswerFragment.setArguments(data);
            ((ActivityMain) getActivity()).attachFragment(correctAnswerFragment, "CORRECT_ANSWER_FRAGMENT");
        }
        else {
            WrongAnswerFragment wrongAnswerFragment = new WrongAnswerFragment();
            wrongAnswerFragment.setArguments(data);
            ((ActivityMain) getActivity()).attachFragment(wrongAnswerFragment, "WRONG_ANSWER_FRAGMENT");
        }
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
