package org.coronium.util;

import org.coronium.page.core.ui.common.Property;
import org.coronium.page.core.ui.driver.Driver;
import org.coronium.util.drivers.*;
import org.openqa.selenium.Capabilities;

public class DriverManagerFactory {

    /** Supported remote grids. */
    private enum RemoteGrid {
        SAUCE, BROWSERSTACK, GRID
    }

    //Platforms for remote grids
    public enum Platform {
        WINDOWS, OSX, IOS, ANDROID, NONE
    }
    private static Driver instantiateDesiredRemote(Driver driver) {

        Capabilities capabilities = driver.getCapabilities();
        Platform platform = getPlatformType();
        switch (getRemoteType()) {
            case GRID:
                return new GridManager(capabilities);
            default:
                return driver;
        }
    }

    public static DriverManager getDriverManager (DriverType type) {

        switch (type) {
            case IE:
                /*driverManager = new IEDriverManager();*/
                return new IEDriverManager();
            case EDGE:
                /*driverManager = new EdgeDriverManager();*/
                return new EdgeDriverManager();
            case FIREFOX:
                /*driverManager = new FirefoxDriverManager();*/
                return new FirefoxDriverManager();
            case SAFARI:
                /*driverManager = new SafariDriverManager();*/
                return new SafariDriverManager();
            case CHROME:
                return new ChromeDriverManager();
            default:
                throw new IllegalArgumentException("Invalid Browser specified");
        }
    }

    //for diff OS
    private static Platform getPlatformType() {
        if (Property.PLATFORM.isSpecified()) {
            return Platform.valueOf(Property.PLATFORM.getValue().toUpperCase());
        } else {
            return Platform.NONE;
        }
    }

    private static RemoteGrid getRemoteType() {
            return RemoteGrid.GRID;
    }
}
