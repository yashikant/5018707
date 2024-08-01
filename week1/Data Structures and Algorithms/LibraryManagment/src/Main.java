public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book(2, "Moby Dick", "Herman Melville"));
        library.addBook(new Book(3, "1984", "George Orwell"));
        library.addBook(new Book(4, "To Kill a Mockingbird", "Harper Lee"));

        // Linear search
        System.out.println("Linear Search for '1984':");
        Book book = library.linearSearchByTitle("1984");
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book not found");
        }

        // Binary search
        System.out.println("\nBinary Search for 'Moby Dick':");
        book = library.binarySearchByTitle("Moby Dick");
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book not found");
        }

        // Print all books
        System.out.println("\nAll books:");
        library.printBooks();
    }
}
