package com.epam.PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailHomePO {

  @FindBy(css = "div.T-I.J-J5-Ji.T-I-KE.L3")
  private WebElement composeButton;

  @FindBy(css = "div.vh")
  private WebElement sentMessageIdentifier;

  @FindBy(css = "a[href=\"https://mail.google.com/mail/u/0/#sent\"]")
  private WebElement sentPageOpenElement;

  private WebDriverWait wait;

  public GmailHomePO(WebDriver driver, WebDriverWait wait) {
    this.wait = wait;
    PageFactory.initElements(driver, this);
  }

  public boolean composeButtonIsVisible() {
    return wait.until(ExpectedConditions.visibilityOf(composeButton)).isDisplayed();
  }

  public void clickComposeButton() {
    wait.until(ExpectedConditions.visibilityOf(composeButton)).click();
  }

  public boolean isMessageSent() {
    return sentMessageIdentifier.isDisplayed();
  }

  public void openSentMailsPage() {
    wait.until(ExpectedConditions.visibilityOf(sentPageOpenElement)).click();
  }
}
