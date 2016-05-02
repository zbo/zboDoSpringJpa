package org.zbo.done.util;

/**
 * Created by zbo on 4/23/16.
 */
public class DBInitUtil {
    public String getInitLocation() {
        return initLocation;
    }

    public void setInitLocation(String initLocation) {
        this.initLocation = initLocation;
    }

    public String getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(String dataLocation) {
        this.dataLocation = dataLocation;
    }

    private String initLocation;
    private String dataLocation;

    public String getSecurityLocation() {
        return securityLocation;
    }

    public void setSecurityLocation(String securityLocation) {
        this.securityLocation = securityLocation;
    }

    private String securityLocation;
}
