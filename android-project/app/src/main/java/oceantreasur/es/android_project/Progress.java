package oceantreasur.es.android_project;

public class Progress {
    private int current;
    private int max;

    public Progress (int  current, int max) {
        this.current = current;
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public int getMax() {
        return max;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "current=" + current +
                ", max=" + max +
                '}';
    }
}
