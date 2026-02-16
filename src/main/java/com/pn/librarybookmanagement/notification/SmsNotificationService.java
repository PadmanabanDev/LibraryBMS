package com.pn.librarybookmanagement.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("sms")
public class SmsNotificationService implements NotificationService {

    @Value("${librarian.phone}")
    private  String librarianPhone;

    @Override
    public void notify(String message, String recipient) {
        System.out.println("📱 SMS Notification");
        System.out.println("To: " + librarianPhone);
        System.out.println("Message: " + message);
        System.out.println("✅ SMS sent (simulated).");
    }
}
