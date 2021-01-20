package Day13.superProblem;

public class Car implements Cloneable {
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 이 방식이라면 Cloneable 을 구현할 이유도 없음
     */
    @Override
    protected Car clone() {
        return new Car(this.name);
    }
}
