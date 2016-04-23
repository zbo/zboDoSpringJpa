package org.zbo.done.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropConstants {

    @Value("${jdbc.initLocation}")
    private String initLocation;

    @Value("${jdbc.dataLocation}")
    private String dataLocation;

    public String getDataLocation() {
        return dataLocation;
    }

    public String getInitLocation() {
        return initLocation;
    }

}