package com.epam.PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailMessageWidowPO {

  @FindBy(css = "h2.hP")
  private WebElement subjectElement;

  @FindBy(css = "div.ii.gt")
  private WebElement messageBodyElement;

  public GmailMessageWidowPO(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public boolean checkIsItSameMail(String expectedSubject, String expectedMessage) {
    String actualSubject = subjectElement.getText();
    String actualMessage = messageBodyElement.getText();
    return actualSubject.equals(expectedSubject) & actualMessage.equals(expectedMessage);
  }
}
