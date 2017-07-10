package oceantreasur.es.android_project;

import android.os.Bundle;

public class CorrectAnswerActivity extends BaseAnswerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String getMessage() {
        return getString(R.string.correct_answer);
    }

    @Override
    public int getColor() {
        return R.color.light_green;
    }
}
