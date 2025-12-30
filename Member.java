package Library_Management_System;

import java.util.ArrayList;

public class Member {
    private String memberId;
    private String name;
    private String contact;
    private ArrayList<Book> borrowedBooks;

    public Member(String memberId, String name, String contact) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }

    public boolean borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    public void displayInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contact);
        System.out.println("Books Borrowed: " + borrowedBooks.size());

        if (!borrowedBooks.isEmpty()) {
            System.out.println("Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println("  - " + book.getTitle());
            }
        }
        System.out.println();
    }
}
