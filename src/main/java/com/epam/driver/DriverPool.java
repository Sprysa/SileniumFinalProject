package com.epam.driver;

import com.epam.BO.LoginBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverPool {

  private static final Logger LOG = LogManager.getLogger(LoginBO.class);
  private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<WebDriver>();

  public static WebDriver getDriver() {
    if (driverPool.get() == null) {
      driverPool.set(initDriver());
    }
    return driverPool.get();
  }

  public static void setWebDriver(WebDriver driver) {
    driverPool.set(driver);
  }

  public static void quit() {
    driverPool.remove();
  }

  private static WebDriver initDriver() {
    LOG.info("Driver initialization.");
    System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    return driver;
  }
}
