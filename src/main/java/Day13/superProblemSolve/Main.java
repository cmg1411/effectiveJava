package Day13.superProblemSolve;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Car car = new Car("genesis");
        Bus bus = new Bus("cityBus");

        Car clone = car.clone();

        System.out.println(car.clone());
        System.out.println(bus.clone());
    }
}
