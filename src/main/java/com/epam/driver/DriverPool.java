package com.epam.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverPool {

  private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<WebDriver>();

  public WebDriver getDriver() {
    if (driverPool.get() == null) {
      driverPool.set(initWebDriver());
    }
    return driverPool.get();
  }

  private static WebDriver initWebDriver() {
    System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    WebDriver webDriver = new ChromeDriver();
    return webDriver;
  }
}
