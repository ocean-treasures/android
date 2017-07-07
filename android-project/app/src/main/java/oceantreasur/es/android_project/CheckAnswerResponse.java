package oceantreasur.es.android_project;

class CheckAnswerResponse {
    private String word;
    private boolean correct;
    private Progress progress;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "CheckAnswerResponse{" +
                "word='" + word + '\'' +
                ", correct=" + correct +
                ", progress=" + progress +
                '}';
    }
}
