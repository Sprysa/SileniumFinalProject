package com.epam.BO;

import com.epam.PO.GmailHomePO;
import com.epam.PO.GmailMessageWidgetPO;
import com.epam.model.MailModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMessageBO {

  private WebDriver driver;
  private WebDriverWait wait;

  private static final Logger LOG = LogManager.getLogger(SendMessageBO.class);

  public SendMessageBO(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public void openFillAndSendMessage(MailModel mail) {
    GmailMessageWidgetPO messageWidget = new GmailMessageWidgetPO(driver);
    LOG.info("Open message widget.");
    new GmailHomePO(driver, wait).clickComposeButton();
    LOG.info("Fill message.");
    messageWidget.fillAddress(mail.getAddress());
    messageWidget.fillSubject(mail.getSubject());
    messageWidget.fillMessage(mail.getMessage());
    LOG.info("Sent message.");
    messageWidget.clickSendButton();
  }

  public boolean checkIsWarningMessageDisplay() {
    GmailMessageWidgetPO messageWidget = new GmailMessageWidgetPO(driver);
    return messageWidget.isWarningMessageIsVisible();
  }

  public void fixMailAdrressAndSend(MailModel mail) {
    GmailMessageWidgetPO messageWidget = new GmailMessageWidgetPO(driver);
    messageWidget.clickWarningOkButton();
    LOG.info("Fix mail adrress.");
    messageWidget.fixAddress(mail.getAddress());
    messageWidget.clickSendButton();
  }
}
