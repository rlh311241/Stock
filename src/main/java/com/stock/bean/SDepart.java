package com.stock.bean;


public class SDepart {

  private String dId;
  private String dDepart;

  @Override
  public String toString() {
    return "SDepart{" +
            "dId='" + dId + '\'' +
            ", dDepart='" + dDepart + '\'' +
            '}';
  }

  public String getdId() {
    return dId;
  }

  public void setdId(String dId) {
    this.dId = dId;
  }

  public String getdDepart() {
    return dDepart;
  }

  public void setdDepart(String dDepart) {
    this.dDepart = dDepart;
  }

  public SDepart() {
  }

  public SDepart(String dId, String dDepart) {
    this.dId = dId;
    this.dDepart = dDepart;
  }
}
