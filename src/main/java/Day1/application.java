package Day1;

public class application {
    public static void main(String[] args) {
        Book book1 = new Book("effectiveJava");

        Book book2 = Book.createBookWithName("effectiveJava");
    }
}
