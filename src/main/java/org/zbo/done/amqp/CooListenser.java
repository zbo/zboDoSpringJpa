package org.zbo.done.amqp;

import org.springframework.stereotype.Component;

/**
 * Created by zbo on 4/25/16.
 */
@Component
public class CooListenser {
    public void listen(String msg) {
        System.out.println(msg);
    }
}
