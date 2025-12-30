package Library_Management_System;

import java.util.Scanner;
import java.util.ArrayList;

public class LibrarySystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        initializeLibrary();
        boolean running = true;

        while (running) {
            showMenu();
            int choice = getValidInt(1, 8);

            switch (choice) {
                case 1 -> addBook();
                case 2 -> addMember();
                case 3 -> library.displayAllBooks();
                case 4 -> library.displayAvailableBooks();
                case 5 -> searchBooks();
                case 6 -> borrowBook();
                case 7 -> returnBook();
                case 8 -> {
                    running = false;
                    System.out.println("Thank you for using Library Management System!");
                }
            }
        }
        scanner.close();
    }

    private static void initializeLibrary() {
        library.addBook(new Book("978-3-16-148410-2", "The Magic of Lost Temple", "Sudha Murthy", "Story"));
        library.addBook(new Book("978-0-262-03384-8", "Never Never", "Collin Grover", "Fiction"));
        library.addBook(new Book("978-0-13-468599-1", "The God of Small Things", "Arundhati Roy", "Novel"));
    }

    private static void showMenu() {
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1. Add New Book");
        System.out.println("2. Register New Member");
        System.out.println("3. Display All Books");
        System.out.println("4. Display Available Books");
        System.out.println("5. Search Books by Title/Author");
        System.out.println("6. Borrow Book");
        System.out.println("7. Return Book");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getValidInt(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) return value;
                System.out.print("Please enter number between " + min + " and " + max + ": ");
            } catch (Exception e) {
                System.out.print("Invalid input! Enter a number: ");
            }
        }
    }

    private static void addBook() {
        System.out.println("\n=== ADD NEW BOOK ===");
        System.out.print("Enter ISBN: "); String isbn = scanner.nextLine();
        System.out.print("Enter Title: "); String title = scanner.nextLine();
        System.out.print("Enter Author: "); String author = scanner.nextLine();
        System.out.print("Enter Genre: "); String genre = scanner.nextLine();

        Book book = new Book(isbn, title, author, genre);
        library.addBook(book);
        System.out.println("Book added successfully!");
    }

    private static void addMember() {
        System.out.println("\n=== REGISTER NEW MEMBER ===");
        System.out.print("Enter Member ID: "); String id = scanner.nextLine();
        System.out.print("Enter Name: "); String name = scanner.nextLine();
        System.out.print("Enter Contact: "); String contact = scanner.nextLine();

        Member member = new Member(id, name, contact);
        library.addMember(member);
        System.out.println("Member registered successfully!");
    }

    private static void searchBooks() {
        System.out.println("\n=== SEARCH BOOKS ===");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        ArrayList<Book> results = library.searchBooksByTitleOrAuthor(keyword);
        if (results.isEmpty()) System.out.println("No books found!");
        else {
            System.out.println("Search Results:");
            for (Book book : results) book.displayInfo();
        }
    }

    private static void borrowBook() {
        System.out.println("\n=== BORROW BOOK ===");
        System.out.print("Enter Member ID: "); String memberId = scanner.nextLine();
        Member member = library.findMemberById(memberId);
        if (member == null) { System.out.println("Member not found!"); return; }

        System.out.print("Enter Book ISBN: "); String isbn = scanner.nextLine();
        Book book = library.findBookByIsbn(isbn);
        if (book == null) { System.out.println("Book not found!"); return; }

        if (member.borrowBook(book)) System.out.println("Book borrowed successfully!");
        else System.out.println("Book is not available!");
    }

    private static void returnBook() {
        System.out.println("\n=== RETURN BOOK ===");
        System.out.print("Enter Member ID: "); String memberId = scanner.nextLine();
        Member member = library.findMemberById(memberId);
        if (member == null) { System.out.println("Member not found!"); return; }

        System.out.print("Enter Book ISBN: "); String isbn = scanner.nextLine();
        Book book = library.findBookByIsbn(isbn);
        if (book == null) { System.out.println("Book not found!"); return; }

        if (member.returnBook(book)) System.out.println("Book returned successfully!");
        else System.out.println("Return failed! The member did not borrow this book.");
    }
}
