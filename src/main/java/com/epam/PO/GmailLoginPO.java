package com.epam.PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailLoginPO {

  @FindBy(id = "identifierId")
  private WebElement emailInputElement;

  @FindBy(className = "CwaK9")
  private WebElement mailNextButton;

  @FindBy(css = "input.whsOnd.zHQkBf[type=\"password\"]")
  private WebElement passwordInputElement;

  @FindBy(id = "passwordNext")
  private WebElement passwordNexButton;

  public GmailLoginPO(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void typeLoginAndSubmit(String login) {
    emailInputElement.sendKeys(login);
    mailNextButton.click();
  }

  public void typePasswordAndSubmit(String password) {
    passwordInputElement.sendKeys(password);
    passwordNexButton.click();
  }
}
