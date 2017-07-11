package oceantreasur.es;

public class CorrectAnswerActivity extends BaseAnswerActivity {
    @Override
    public String getMessage() {
        return getString(oceantreasur.es.R.string.correct_answer);
    }

    @Override
    public int getColor() {
        return oceantreasur.es.R.color.light_green;
    }
}
