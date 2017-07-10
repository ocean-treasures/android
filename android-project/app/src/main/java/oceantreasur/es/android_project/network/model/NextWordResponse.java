package oceantreasur.es.android_project.network.model;

import com.google.gson.annotations.SerializedName;

import oceantreasur.es.android_project.Picture;
import oceantreasur.es.android_project.Progress;
import oceantreasur.es.android_project.WordDetails;

public class NextWordResponse {
    private Progress progress;
    private WordDetails word;
    @SerializedName("pictures")
    private Picture[] pictures = null;

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public WordDetails getWord() {
        return word;
    }

    public void setWord(WordDetails word) {
        this.word = word;
    }

    public Picture[] getPictures() {
        return pictures;
    }
    
    public void setPictures(Picture[] pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "NextWordResponse{" +
                "progress=" + progress +
                ", word=" + word +
                ", pictures=" + pictures +
                '}';
    }
}
