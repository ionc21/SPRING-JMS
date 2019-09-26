package com.pluralsight.practicaljms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Grant Little grant@grantlittle.me
 */
@Component
public class EMailMessageListener {

    @JmsListener(destination = "Emails", concurrency = "3-10")
    public void onMessage(Email email) {
        System.out.println("Received: " + email);
    }

}
