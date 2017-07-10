package oceantreasur.es.android_project;

public class WrongAnswerActivity extends BaseAnswerActivity {

    @Override
    public String getMessage() {
        return getString(R.string.wrong_answer);
    }

    @Override
    public int getColor() {
        return R.color.red;
    }
}