package Chapter3.Day13.superProblem;

public class Bus extends Car implements Cloneable {
    private String busNumber;

    public Bus(String name) {
        super(name);
    }

    /**
     * Car 반환.
     * busNumber 정보는 들어가지도 못함.
     */
    @Override
    protected Car clone() {
        return super.clone(); // 공변 변환 타이핑
    }
}
