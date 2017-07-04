package oceantreasur.es.android_project;

/**
 * Created by Student on 7/4/2017.
 */

public class WordDetails {
    private String word;
    private int id;

    WordDetails(int id, String word) {
        this.id = id;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


