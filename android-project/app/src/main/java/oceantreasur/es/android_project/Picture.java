package oceantreasur.es.android_project;

public class Picture {
    private int id;
    private String url;
    private boolean containsFullURL;

    Picture(int id, String url, boolean containsFullURL) {
        this.id = id;
        this.url = url;
        this.containsFullURL = containsFullURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isContainsFullURL() {
        return containsFullURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContainsFullURL(boolean containsFullURL) {
        this.containsFullURL = containsFullURL;
    }

    public String getResolvedUrl() {
        if (isContainsFullURL()) {
            return getUrl();
        }
        else {
//            return OceanTreasuresConstants.BASE_URL + getUrl();
            return getUrl();
        }
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
