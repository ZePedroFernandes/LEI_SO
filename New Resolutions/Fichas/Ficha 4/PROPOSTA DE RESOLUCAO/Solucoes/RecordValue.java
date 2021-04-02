
public class RecordValue {

    private String name = null;
    private int max = 0;

    public void setMaxPair(String name, int max) {
        this.name = name;
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }

    public String getName() {
        return this.name;
    }
}
