package oceantreasur.es.fragments;

import android.media.MediaPlayer;

import oceantreasur.es.CorrectAnswerActivity;
import oceantreasur.es.R;



public class CorrectAnswerFragment extends BaseAnswerFragment {
    @Override
    public String getMessage() {
        return getString(oceantreasur.es.R.string.correct_answer);
    }

    @Override
    public int getColor() {
        return oceantreasur.es.R.color.light_green;
    }

    @Override
    public int getIndicatorImage() {
        return R.drawable.ic_seahorse;
    }

    @Override
    void playMusic() {
        MediaPlayer mPlayer = MediaPlayer.create(getActivity(), R.raw.yay);
        mPlayer.start();
    }
}
