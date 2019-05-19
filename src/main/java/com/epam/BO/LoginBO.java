package com.epam.BO;

import com.epam.PO.GmailHomePO;
import com.epam.PO.GmailLoginPO;
import com.epam.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginBO {
  private WebDriver driver;
  private WebDriverWait wait;

  private static final Logger LOG = LogManager.getLogger(LoginBO.class);

  public LoginBO(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public void loginInToGmail(UserModel user) {
    LOG.info("Login to account.");
    GmailLoginPO loginPO = new GmailLoginPO(driver);
    String login = user.getLogin();
    loginPO.typeLoginAndSubmit(login);
    loginPO.typePasswordAndSubmit(user.getPassword());
  }

  public boolean checkIsUserLoged(UserModel user) {
    return new GmailHomePO(driver, wait).composeButtonIsVisible() & driver.getTitle().contains(user.getLogin());
  }
}
