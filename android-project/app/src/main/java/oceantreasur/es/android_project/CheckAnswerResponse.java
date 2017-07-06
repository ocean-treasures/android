package oceantreasur.es.android_project;

class CheckAnswerResponse {
    private String word;
    private boolean correct;

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

    @Override
    public String toString() {
        return "CheckAnswerResponse{" +
                "word='" + word + '\'' +
                ", correct=" + correct +
                '}';
    }
}
