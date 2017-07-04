package oceantreasur.es.android_project;

public class CheckAnswerRequest {
    private int pic_id;

    CheckAnswerRequest(int pic_id) {
        this.pic_id = pic_id;
    }

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }
}
