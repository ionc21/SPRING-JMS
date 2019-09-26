package com.pluralsight.practicaljms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Grant Little grant@grantlittle.me
 */
@Component
public class OrderMessageListener {

    @JmsListener(destination = "Orders",
            containerFactory = "TopicListenerContainerFactory",
            subscription = "orders")
    public void onMessage(Order order) {
        System.out.println(order);
    }

    /* To receive raw java.jms.Message objects then use the following instead
    @JmsListener(destination = "Orders",
            containerFactory = "TopicListenerContainerFactory",
            subscription = "orders")
    public void onMessage(javax.jms.Message order) {
        System.out.println(order);
    }
    */

}
