package org.zbo.done.amqp;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by zbo on 4/25/16.
 */
@Component
public class CooListenser {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "myQueueCoo", durable = "true"),
            exchange = @Exchange(value = "myExchange"),
            key = "coo.bar"))
    public void listen(String msg) {
        System.out.println("[COO:]"+msg);
    }
}
