package oceantreasur.es.android_project;

import android.graphics.Color;
import android.os.Bundle;

public class WrongAnswerActivity extends BaseAnswerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String getMessage() {
        return getString(R.string.wrong_answer);
    }

    @Override
    public int getColor() {
        return R.color.red;
    }
}