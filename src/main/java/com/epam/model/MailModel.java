package com.epam.model;

public class MailModel {
  private String adrres;
  private String subject;
  private String message;

  public MailModel(String adrres, String subject, String message) {
    this.adrres = adrres;
    this.subject = subject;
    this.message = message;
  }

  public String getAdrres() {
    return adrres;
  }

  public void setAdrres(String adrres) {
    this.adrres = adrres;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
