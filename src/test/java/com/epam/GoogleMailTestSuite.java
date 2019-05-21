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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.DataProvider.MailDataConst.*;

public class GoogleMailTestSuite {

  private WebDriverWait wait;
  private MailModel incorrectMail = new MailModel(INCORRECT_EMAIL_ADDRESS, SUBJECT, MESSAGE);
  private MailModel correctMail = new MailModel(CORRECT_EMAIL_ADDRESS, SUBJECT, MESSAGE);

  private static final Logger LOG = LogManager.getLogger(GoogleMailTestSuite.class);

  @BeforeMethod
  private void init() {
    WebDriver driver = DriverPool.getDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait = (WebDriverWait) new WebDriverWait(driver, 50)
        .ignoring(StaleElementReferenceException.class);
    DriverPool.setWebDriver(driver);
  }

  @Test(dataProvider = "loginData")
  public void GmailTest(UserModel user) {
    WebDriver driver = DriverPool.getDriver();
    LoginBO loginBO = new LoginBO(driver, wait);
    SendMessageBO sendMessageBO = new SendMessageBO(driver, wait);
    CheckMessageBO checkMessageBO = new CheckMessageBO(driver, wait);
    LOG.info("START TESTING");
    driver.get(INITIAL_PAGE);
    loginBO.loginInToGmail(user);
    Assert.assertTrue(loginBO.checkIsUserLogged(user), "User is not logged in to account.");
    sendMessageBO.openFillAndSendMessage(incorrectMail);
    Assert.assertTrue(sendMessageBO.checkIsWarningMessageDisplay(),
        "Warning message is not appears.");
    sendMessageBO.fixMailAdrressAndSend(correctMail);
    Assert.assertTrue(checkMessageBO.checkIfMessageSent(), "Message is not sent.");
    Assert.assertTrue(checkMessageBO.checkIfMailInSentFolder(correctMail),
        "Message is not moved to 'Sent' folder.");
    LOG.info("END TESTING");
  }

  @AfterMethod
  private void deinit() {
    DriverPool.quit();
  }

  @DataProvider(name = "loginData", parallel = true)
  private Object[] loginData() {
    return new Object[]{new UserModel(LOGIN, PASSWORD), new UserModel(LOGIN, PASSWORD),
        new UserModel(LOGIN, PASSWORD), new UserModel(LOGIN, PASSWORD),
        new UserModel(LOGIN, PASSWORD)};
  }
}