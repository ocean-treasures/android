package oceantreasur.es;

import android.media.MediaPlayer;

public class CorrectAnswerActivity extends BaseAnswerActivity {
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
        MediaPlayer mPlayer = MediaPlayer.create(CorrectAnswerActivity.this, R.raw.yay);
        mPlayer.start();
    }

}
