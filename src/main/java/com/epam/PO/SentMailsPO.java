package com.epam.PO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentMailsPO {

  @FindBy(xpath = "//div[3]/div/div/table/tbody/tr/td[5]/div[2]/span")
  private WebElement lastMailElement;

  public SentMailsPO(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void openLastMail() {
    lastMailElement.click();
  }

}
