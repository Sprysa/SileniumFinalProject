package com.epam.BO;

import com.epam.PO.GmailHomePO;
import com.epam.PO.GmailMessageWidowPO;
import com.epam.PO.SentMailsPO;
import com.epam.model.MailModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckMessageBO {

  private WebDriver driver;
  private WebDriverWait wait;
  private GmailHomePO gmailHomePO;

  private static final Logger LOG = LogManager.getLogger(CheckMessageBO.class);

  public CheckMessageBO(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public boolean checkIfMessageSent() {
    LOG.info("Check if message sent.");
    gmailHomePO = new GmailHomePO(driver, wait);
    return gmailHomePO.isMessageSent();
  }

  public boolean checkIfMailInSentFolder(MailModel mail) {
    LOG.info("Open 'Sent' folder.");
    gmailHomePO.openSentMailsPage();
    new SentMailsPO(driver, wait).openLastMail();
    LOG.info("Check if message in 'Sent' folder.");
    String expectedSubject = mail.getSubject();
    String expectedMessage = mail.getMessage();
    return new GmailMessageWidowPO(driver).checkIsItSameMail(expectedSubject, expectedMessage);
  }
}
