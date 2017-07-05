package oceantreasur.es.android_project;

import java.util.List;

public class NextWordResponse {
    private Progress progress;
    private WordDetails word;
    private List<Picture> pics = null;

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

    public List<Picture> getPics() {
        return pics;
    }

    public void setPics(List<Picture> pics) {
        this.pics = pics;
    }
}
