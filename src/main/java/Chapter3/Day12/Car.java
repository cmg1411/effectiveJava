package Chapter3.Day12;

public class Car {
    private String name;
    private int position;

    public Car(String name, int position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public int hashCode() {
        return 15;
    }
}
