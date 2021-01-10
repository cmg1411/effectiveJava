package Day1;

public class Book {
    private String name;
    private String author;
    private String publisher;

    public static Book bookWithNameAndAuthor(String name, String author) {
         return new Book(name, author);
    }

    private Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    private Book(String name, String publisher) {
        this.name = name;
        this.publisher = publisher;
    }
}
