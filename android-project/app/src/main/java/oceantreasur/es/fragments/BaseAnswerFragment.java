package oceantreasur.es.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import oceantreasur.es.MainActivity;
import oceantreasur.es.R;
import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.view.CustomButton;
import oceantreasur.es.view.CustomTextView;



public abstract class BaseAnswerFragment extends Fragment {

    public static final String EXTRA_URL = "EXTRA_URL";
    public static final String EXTRA_WORD = "EXTRA_WORD";
    public static final String EXTRA_PROGRESS_CUR = "EXTRA_PROGRESS_CURR";
    public static final String EXTRA_PROGRESS_MAX = "EXTRA_PROGRESS_MAX";

    private ImageView image;
    private ImageView indicator;
    private CustomTextView answerMessage;
    private CustomTextView answerWord;
    private ProgressBar progressBar;
    private CustomButton nextButton;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_answer, container, false);

        setupActivity(view);
        playMusic();

        Bundle intentData = getArguments();
        loadImage(intentData.getString(EXTRA_URL));
        setupProgress(intentData.getInt(EXTRA_PROGRESS_CUR), intentData.getInt(EXTRA_PROGRESS_MAX));
        answerWord.setText(intentData.getString(EXTRA_WORD));

        answerMessage.setText(getMessage());

        return view;
    }

    public abstract String getMessage();

    public abstract int getColor();

    public abstract int getIndicatorImage();

    View.OnClickListener nextClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            disableNextClick();

            if (progressBar.getProgress() != progressBar.getMax()) {
                GameFragment gameFragment = new GameFragment();
                ((MainActivity) getActivity()).attachFragment(gameFragment, FragmentTags.GAME_FRAGMENT_TAG);
            }
            else {
                EndGameFragment endGameFragment = new EndGameFragment();
                ((MainActivity) getActivity()).attachFragment(endGameFragment, FragmentTags.END_GAME_FRAGMENT_TAG);
            }
        }
    };

    public void disableNextClick(){
        nextButton.setClickable(false);
        nextButton.setEnabled(false);
        image.setClickable(false);
        image.setEnabled(false);
    }

    public void setupActivity(View view) {
        image = (ImageView) view.findViewById(R.id.iv_answer_pic);
        answerMessage = (CustomTextView) view.findViewById(R.id.tv_answer_msg);
        answerWord = (CustomTextView) view.findViewById(R.id.tv_answer_word);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        indicator = (ImageView) view.findViewById(R.id.iv_indicator);
        nextButton = (CustomButton) view.findViewById(R.id.btn_next);

        nextButton.setOnClickListener(nextClick);

        indicator.setImageResource(getIndicatorImage());

    }

    public void setupProgress(int cur, int max) {
        progressBar.setMax(max);
        progressBar.setProgress(cur);
    }

    public void loadImage(String url) {
        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(url)
                .fitCenter()
                .into(image);

        image.setOnClickListener(nextClick);
    }

    abstract void playMusic();

}

