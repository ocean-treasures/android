package oceantreasur.es.ui;

import android.media.MediaPlayer;

import oceantreasur.es.R;


public class WrongAnswerFragment extends BaseAnswerFragment {
    @Override
    public String getMessage() {
        return getString(oceantreasur.es.R.string.wrong_answer);
    }

    @Override
    public int getColor() {
        return oceantreasur.es.R.color.red;
    }

    @Override
    public int getIndicatorImage() {
        return R.drawable.ic_sea_urchin;
    }

    @Override
    public void playMusic() {
        MediaPlayer mPlayer = MediaPlayer.create(getActivity(), R.raw.superior_fart_sound);
        mPlayer.start();
    }
}
