package com.pn.librarybookmanagement.repository;

import java.util.HashMap;
import java.util.Map;
import com.pn.librarybookmanagement.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookRepository implements  BookRepository {
    private final Map<String, Book> books = new HashMap<>();

    @Override
    public void addBook(Book book){
        if(books.containsKey(book.getIsbn())){
            throw new IllegalArgumentException("Book with ISBN already exists: "+book.getIsbn());
        }else{
            books.put(book.getIsbn(),book);
            System.out.println("✅ Book added: " + book);
        }
    }

    @Override
    public Book findBook(String isbn) {
        return books.get(isbn);
    }
    }

