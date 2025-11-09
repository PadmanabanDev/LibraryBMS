package com.pn.librarybookmanagement;

import com.pn.librarybookmanagement.model.Book;
import com.pn.librarybookmanagement.notification.NotificationService;
import com.pn.librarybookmanagement.repository.BookRepository;
import com.pn.librarybookmanagement.service.LibraryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryBookManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryBookManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner run(BookRepository bookRepository,
                          @Qualifier("email") NotificationService emailNotificationService,
                          @Qualifier("sms") NotificationService smsNotificationService) {
        return args -> {
            System.out.println("========== LIBRARY SERVICE STARTED ==========\n");

            // Using Email notification
            LibraryService emailLibrary = new LibraryService(bookRepository, emailNotificationService);
            emailLibrary.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", 3));
            emailLibrary.addBook(new Book("978-0596009205", "Head First Java", "Kathy Sierra", 1)); // triggers email alert

            emailLibrary.borrowBook("978-0134685991");
            emailLibrary.borrowBook("978-0134685991"); // triggers low stock alert
            System.out.println("Find Book: " + emailLibrary.findBook("978-0134685991") + "\n");

            // Using SMS notification
            System.out.println("========== SWITCHING TO SMS NOTIFICATIONS ==========\n");
            LibraryService smsLibrary = new LibraryService(bookRepository, smsNotificationService);
            smsLibrary.addBook(new Book("978-1617294945", "Spring in Action", "Craig Walls", 1)); // triggers SMS alert

            System.out.println("========== END ==========");
        };
    }
}
