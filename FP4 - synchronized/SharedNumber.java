public class SharedNumber {

    int number;

    public SharedNumber() {
        this.number = 0;
    }

    public void addNumber(int number) {
        this.number += number;
    }

    public int getNumber() {
        return this.number;
    }
}