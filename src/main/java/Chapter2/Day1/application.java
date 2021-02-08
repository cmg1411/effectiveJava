package Chapter2.Day1;

public class application {
    public static void main(String[] args) {
        //Book book1 = new Book("effectiveJava");

        Book book2 = Book.createBookWithName("effectiveJava");

        Car benz = Car.createBenzWithModelAndColor("S100", "Red");
        Car rollsRoyce = Car.createRRWithModelAndPrice("Wraith", "400000000");

        System.out.println(rollsRoyce.getClass().toString());
    }
}
