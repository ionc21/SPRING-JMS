package com.pluralsight.practicaljms;

/**
 * Created by Grant Little grant@grantlittle.me
 */
public class EMailMessageListener {

    public void onMessage(Email email) {
        System.out.println(email);
    }

}
