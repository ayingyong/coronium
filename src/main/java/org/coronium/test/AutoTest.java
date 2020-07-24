package org.coronium.test;

import org.coronium.util.DriverManager;
import org.coronium.util.DriverManagerFactory;
import org.coronium.util.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public abstract class AutoTest {
    protected static String environment = System.getProperty("environment");
    protected static WebDriver driver = null;

    @BeforeClass
    public void beforeClass() {
        initDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
        driver = null;
    }

    public static void initDriver() {
        if (driver == null) {
            DriverManagerFactory driverManagerFactory = new DriverManagerFactory();
            DriverManager driverManager = driverManagerFactory.getDriverManager(DriverType.CHROME);
            driver = driverManager.getWebDriver();
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            driver.get(environment);
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}