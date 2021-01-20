package Day13.superProblemSolve;

public class Car implements Cloneable {
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * try - catch 구문이 없으면 컴파일 에러.
     * Object 의 clone 메서드가 checkedException 을 던지도록 선언되었음
     *
     * uncheckedException 이기 때문에 불필요해보이는 코드가 추가되어야 함.
     */
    @Override
    protected Car clone() {
        try {
            return (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}