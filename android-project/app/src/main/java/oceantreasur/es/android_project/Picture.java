package oceantreasur.es.android_project;

/**
 * Created by Student on 7/4/2017.
 */

public class Picture {
    private int id;
    private String url;

    Picture(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}