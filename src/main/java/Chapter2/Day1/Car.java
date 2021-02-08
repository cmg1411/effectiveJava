package Chapter2.Day1;

public interface Car {
    static Car createBenzWithModelAndColor(String model, String color) {
        return new Benz(model, color);
    }

    static Car createRRWithModelAndPrice(String model, String price) {
        if (Integer.parseInt(price)  > 200000000) {
            return new RR(model, price);
        }
        return new RollsRoyceJunior(model, price);
    }
}
