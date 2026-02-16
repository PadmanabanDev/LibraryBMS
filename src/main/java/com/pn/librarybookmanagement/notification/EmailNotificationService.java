package com.pn.librarybookmanagement.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("email")
public class EmailNotificationService implements NotificationService{

    @Value("${librarian.email}")
    private String librarianEmail;

    @Override
    public void notify(String message , String recipient){
        // recipient parameter can be ignored here or used to show where message would go
        System.out.println("📧 Email Notification");
        System.out.println("To: " + librarianEmail);
        System.out.println("Message: " + message);
        System.out.println("✅ Email sent (simulated).");
    }

}
