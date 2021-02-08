package Chapter4.Day24;

public class Person {
    private String firstName;
    private String lastName;

    private static class Computer {
        private String name;
        private int price;

        public Computer(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }
}
