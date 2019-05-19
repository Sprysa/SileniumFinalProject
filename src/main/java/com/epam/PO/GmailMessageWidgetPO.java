package com.epam.PO;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailMessageWidgetPO {

  @FindBy(name = "to")
  private WebElement addressElement;

  @FindBy(name = "subjectbox")
  private WebElement subjectElement;

  @FindBy(css = "div.Am.Al.editable.LW-avf")
  private WebElement messageBodyElement;

  @FindBy(css = "div.dC>div:nth-child(1)")
  private WebElement sendButtonElement;

  @FindBy(css = "div.Kj-JD[role=\"alertdialog\"]")
  private WebElement warningElement;

  @FindBy(name = "ok")
  private WebElement warningOkButtonElement;

  @FindBy(className = "ata-asE")
  private WebElement inputAdrressElement;

  @FindBy(className = "vM")
  private WebElement closeIncorrectMailAddressElement;

  public GmailMessageWidgetPO(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public boolean isMessageWindowElementsIsVisible() {
    return addressElement.isDisplayed() & subjectElement.isDisplayed() &
        messageBodyElement.isDisplayed() & sendButtonElement.isDisplayed();
  }

  public void fillAddress(String address) {
    addressElement.sendKeys(address);
  }

  public void fillSubject(String subject) {
    subjectElement.sendKeys(subject);
  }

  public void fillMessage(String message) {
    messageBodyElement.sendKeys(message);
  }

  public void clickSendButton() {
    sendButtonElement.click();
  }

  public boolean isWarningMessageIsVisible() {
    return warningElement.isDisplayed();
  }

  public void clickWarningOkButton() {
    warningOkButtonElement.click();
  }

  public void fixAddress(String correctAddress) {
    messageBodyElement.sendKeys(Keys.chord(Keys.CONTROL, Keys.SHIFT, "T"));
    closeIncorrectMailAddressElement.click();
    addressElement.sendKeys(correctAddress);
  }
}
