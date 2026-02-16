package com.pn.librarybookmanagement.model;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private int availableCopies;

    public Book( String isbn, String title, String author , int  availableCopies){
        this.isbn = isbn;
        this.title =title;
        this.author = author;
        this.availableCopies =availableCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", availableCopies='" + availableCopies + '\'' +
                '}';
    }
}
