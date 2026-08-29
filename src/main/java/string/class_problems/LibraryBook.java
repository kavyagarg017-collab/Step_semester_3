class LibraryBook {
    String title;
    String isbn;

    LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    LibraryBook(String title) {
        this(title, "PENDING");
    }

    void printDetails() {
        System.out.println(title + " | " + isbn + " | Catalogued: " + !isbn.equals("PENDING"));
    }

    public static void main(String[] args) {
        LibraryBook[] books = {
            new LibraryBook("Clean Code", "978-0132350884"),
            new LibraryBook("Untitled Draft"),
            new LibraryBook("1984", "9780451524935"),
            new LibraryBook("Notes")
        };

        for (LibraryBook book : books) {
            book.printDetails();
        }
    }
}