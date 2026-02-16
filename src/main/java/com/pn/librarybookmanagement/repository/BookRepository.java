package com.pn.librarybookmanagement.repository;
import  com.pn.librarybookmanagement.model.Book;
public interface BookRepository {
    void addBook(Book book);
    Book findBook(String isbn);
}
