package Day13.superProblem;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Car car = new Car("genesis");
        Bus bus = new Bus("cityBus");

        System.out.println(car.clone());
        System.out.println(bus.clone());
    }
}
