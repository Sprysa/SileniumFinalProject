package com.epam.PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SentMailsPO {

  @FindBy(xpath = "//div[3]/div/div/table/tbody/tr/td[5]/div[2]/span")
  private WebElement lastMailElement;

  private WebDriverWait wait;

  public SentMailsPO(WebDriver driver, WebDriverWait wait) {
    this.wait = wait;
    PageFactory.initElements(driver, this);
  }

  public void openLastMail() {
    wait.until(ExpectedConditions.visibilityOf(lastMailElement)).click();
  }

}
