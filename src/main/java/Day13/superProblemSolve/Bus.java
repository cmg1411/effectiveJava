package Day13.superProblemSolve;

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
    protected Bus clone() {
        var result = (Bus) super.clone();
        result.busNumber = this.busNumber;
        return result;
    }
}
