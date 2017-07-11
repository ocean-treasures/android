package oceantreasur.es;

public class WrongAnswerActivity extends BaseAnswerActivity {

    @Override
    public String getMessage() {
        return getString(oceantreasur.es.R.string.wrong_answer);
    }

    @Override
    public int getColor() {
        return oceantreasur.es.R.color.red;
    }
}