package com.stock.bean;


public class SAdmin {

  private String sAccount;
  private String sPassword;
  private String sPow;
  private String sName;
  private String dDepart;

  public SAdmin() {
  }

  public SAdmin(String sAccount, String sPassword, String sPow, String sName, String dDepart) {
    this.sAccount = sAccount;
    this.sPassword = sPassword;
    this.sPow = sPow;
    this.sName = sName;
    this.dDepart = dDepart;
  }


  public String getSAccount() {
    return sAccount;
  }

  public void setSAccount(String sAccount) {
    this.sAccount = sAccount;
  }


  public String getSPassword() {
    return sPassword;
  }

  public void setSPassword(String sPassword) {
    this.sPassword = sPassword;
  }


  public String getSPow() {
    return sPow;
  }

  public void setSPow(String sPow) {
    this.sPow = sPow;
  }


  public String getSName() {
    return sName;
  }

  public void setSName(String sName) {
    this.sName = sName;
  }


  public String getDDepart() {
    return dDepart;
  }

  public void setDDepart(String dDepart) {
    this.dDepart = dDepart;
  }

}
