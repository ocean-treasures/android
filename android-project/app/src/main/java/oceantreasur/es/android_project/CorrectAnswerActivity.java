package oceantreasur.es.android_project;

public class CorrectAnswerActivity extends BaseAnswerActivity {
    @Override
    public String getMessage() {
        return getString(R.string.correct_answer);
    }

    @Override
    public int getColor() {
        return R.color.light_green;
    }
}
