package com.pn.librarybookmanagement.service;

import com.pn.librarybookmanagement.model.Book;
import com.pn.librarybookmanagement.notification.NotificationService;
import com.pn.librarybookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    private final BookRepository bookRepository;
    private final NotificationService notificationService;

    public LibraryService(BookRepository bookRepository, @Qualifier("email") NotificationService notificationService) {
        this.bookRepository = bookRepository;
        this.notificationService = notificationService;
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);

        checkAndNotifyLowCopies(book);
    }

    public Book findBook(String isbn) {
        Book b = bookRepository.findBook(isbn);
        if (b == null) {
            System.out.println("🔍 Book not found for ISBN: " + isbn);
        }
        return b;

    }

    public void borrowBook(String isbn) {
        Book b = bookRepository.findBook(isbn);
        if (b == null) {
            throw new IllegalArgumentException("Book not found: " + isbn);
        }
        if (b.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No copies available for ISBN: " + isbn);
        }
        b.setAvailableCopies(b.getAvailableCopies() - 1);
        System.out.println("📚 Borrowed: " + b.getTitle() + " (remaining copies: " + b.getAvailableCopies() + ")");
        checkAndNotifyLowCopies(b);
    }

    private void checkAndNotifyLowCopies(Book book) {
        if (book.getAvailableCopies() < 2) {
            String message = "Low stock alert for book '" + book.getTitle() + "' (ISBN: " + book.getIsbn()
                    + "). Available copies: " + book.getAvailableCopies();
            notificationService.notify(message, null);
        }
    }
}
