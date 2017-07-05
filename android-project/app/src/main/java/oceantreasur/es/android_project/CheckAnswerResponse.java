package oceantreasur.es.android_project;

class CheckAnswerResponse {
    private WordDetails word;
    private boolean correct;

    CheckAnswerResponse(WordDetails word, boolean correct) {
        this.word = word;
        this.correct = correct;
    }

    public WordDetails getWord() {
        return word;
    }

    public void setWord(WordDetails word) {
        this.word = word;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
