package oceantreasur.es.android_project;

import com.google.gson.annotations.SerializedName;

public class CheckAnswerRequest {
    @SerializedName("word_id")
    private int wordId;
    @SerializedName("pic_id")
    private int picId;

    CheckAnswerRequest(int wordId, int picId) {
        this.wordId = wordId;
        this.picId = picId;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
