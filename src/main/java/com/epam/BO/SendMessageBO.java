package com.epam.BO;

import com.epam.PO.GmailHomePO;
import com.epam.PO.GmailMessageWidgetPO;
import com.epam.model.MailModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SendMessageBO {
  private WebDriver driver;
  private WebDriverWait wait;
  private GmailMessageWidgetPO messageWidget;

  private static final Logger LOG = LogManager.getLogger(SendMessageBO.class);

  public SendMessageBO(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public void openFillAndSendMessage(MailModel mail) {
    messageWidget = new GmailMessageWidgetPO(driver);
    LOG.info("Open message widget.");
    new GmailHomePO(driver, wait).clickComposeButton();
//    boolean isMessageWindowIsOpen = messageWidget.isMessageWindowElementsIsVisible();
//    Assert.assertTrue(isMessageWindowIsOpen, "Message window is not open.");
    LOG.info("Fill message.");
    messageWidget.fillAddress(mail.getAdrres());
    messageWidget.fillSubject(mail.getSubject());
    messageWidget.fillMessage(mail.getMessage());
    LOG.info("Sent message.");
    messageWidget.clickSendButton();
  }

  public boolean checkIsWarningMessageDisplay(){
    return messageWidget.isWarningMessageIsVisible();
  }

  public void fixMailAdrressAndSend(MailModel mail) {
    messageWidget.clickWarningOkButton();
    LOG.info("Fix mail adrress.");
    messageWidget.fixAddress(mail.getAdrres());
    messageWidget.clickSendButton();
  }
}
