package com.stock.bean;


public class SDepart {

  private long dId;
  private String dDepart;

  @Override
  public String toString() {
    return "SDepart{" +
            "dId=" + dId +
            ", dDepart='" + dDepart + '\'' +
            '}';
  }

  public SDepart() {
  }

  public long getdId() {
    return dId;
  }

  public void setdId(long dId) {
    this.dId = dId;
  }

  public String getdDepart() {
    return dDepart;
  }

  public void setdDepart(String dDepart) {
    this.dDepart = dDepart;
  }

  public SDepart(long dId, String dDepart) {
    this.dId = dId;
    this.dDepart = dDepart;
  }

  public long getDId() {
    return dId;
  }

  public void setDId(long dId) {
    this.dId = dId;
  }


  public String getDDepart() {
    return dDepart;
  }

  public void setDDepart(String dDepart) {
    this.dDepart = dDepart;
  }

}
