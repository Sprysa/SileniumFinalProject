package com.epam;

import com.epam.BO.CheckMessageBO;
import com.epam.BO.LoginBO;
import com.epam.BO.SendMessageBO;
import com.epam.driver.DriverPool;
import com.epam.model.MailModel;
import com.epam.model.UserModel;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleMailTestSuite {
  private WebDriver driver;
  private WebDriverWait wait;
  private LoginBO loginBO;
  private SendMessageBO sendMessageBO;
  private CheckMessageBO checkMessageBO;
  private MailModel incorrectMail = new MailModel(INCORRECT_EMAIL_ADDRESS, SUBJECT, MESSAGE);
  private MailModel correctMail = new MailModel(CORRECT_EMAIL_ADDRESS, SUBJECT, MESSAGE);

  private static final String INITIAL_PAGE = "https://mail.google.com/";
  private static final String LOGIN = "epam.test.sprysa@gmail.com";
  private static final String PASSWORD = "die34nh2";
  private static final String INCORRECT_EMAIL_ADDRESS = "incorrectaddress";
  private static final String CORRECT_EMAIL_ADDRESS = "sprysa@gmail.com";
  private static final String SUBJECT = "Test";
  private static final String MESSAGE = "Hi from epam.test.sprysa@gmail.com";

  private static final Logger LOG = LogManager.getLogger(GoogleMailTestSuite.class);

  @DataProvider(name = "loginData", parallel = true)
  public Object[] loginData() throws Exception {
    return new Object[]{new UserModel(LOGIN, PASSWORD), new UserModel(LOGIN, PASSWORD)};
  }

  @BeforeMethod
  private void init() {
    driver = new DriverPool().getDriver();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    wait = (WebDriverWait) new WebDriverWait(driver, 30);//.ignoring(StaleElementReferenceException.class);
    loginBO = new LoginBO(driver, wait);
    sendMessageBO = new SendMessageBO(driver, wait);
    checkMessageBO = new CheckMessageBO(driver, wait);
  }

  @Test(dataProvider = "loginData")
  public void GmailTest(UserModel user) {
    driver.get(INITIAL_PAGE);
    loginBO.loginInToGmail(user);
    Assert.assertTrue(loginBO.checkIsUserLoged(user), "User is not logged in to account.");
    sendMessageBO.openFillAndSendMessage(incorrectMail);
    Assert.assertTrue(sendMessageBO.checkIsWarningMessageDisplay(), "Warning message is not appears.");
    sendMessageBO.fixMailAdrressAndSend(correctMail);
    Assert.assertTrue(checkMessageBO.checkIsMessageSent(), "Message is not sent.");
    Assert.assertTrue(checkMessageBO.checkIsMailInSentFolder(correctMail), "Message is not moved to 'Sent' folder.");
    driver.quit();
  }

//  @Test(priority = 1, dataProvider = "loginData")
//  public void openGmailAndLogin(String login, String password) {

//  @AfterSuite
//  private void deinit() {
//    DriverPool.quit();
//  }
}