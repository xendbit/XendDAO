package com.idempotent.xendbit.xendserver.models;

import com.idempotent.xendbit.xendserver.models.dao.UserDAO;

import java.sql.SQLException;
import java.util.Date;

public class Exchange {
  private long id;
  private double amountToSell;
  private double amountToRecieve;
  private String fromCoin;
  private String toCoin;
  private double rate;
  private long sellerId;
  private long buyerId;
  private String status;
  private String buyerFromAddress;
  private String buyerToAddress;
  private String sellerFromAddress;
  private String sellerToAddress;
  private Date datetime;
  private Date trxDate;
  private String trxId;
  private boolean active;

  public String getTrxId() {
    return trxId;
  }

  public void setTrxId(String trxId) {
    this.trxId = trxId;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getAmountToSell() {
    return amountToSell;
  }

  public void setAmountToSell(double amountToSell) {
    this.amountToSell = amountToSell;
  }

  public double getAmountToRecieve() {
    return amountToRecieve;
  }

  public void setAmountToRecieve(double amountToRecieve) {
    this.amountToRecieve = amountToRecieve;
  }

  public String getFromCoin() {
    return fromCoin;
  }

  public void setFromCoin(String fromCoin) {
    this.fromCoin = fromCoin;
  }

  public String getToCoin() {
    return toCoin;
  }

  public void setToCoin(String toCoin) {
    this.toCoin = toCoin;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public long getSellerId() {
    return sellerId;
  }

  public void setSellerId(long sellerId) {
    this.sellerId = sellerId;
  }

  public long getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(long buyerId) {
    this.buyerId = buyerId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getBuyerFromAddress() {
    return buyerFromAddress;
  }

  public void setBuyerFromAddress(String buyerFromAddress) {
    this.buyerFromAddress = buyerFromAddress;
  }

  public String getBuyerToAddress() {
    return buyerToAddress;
  }

  public void setBuyerToAddress(String buyerToAddress) {
    this.buyerToAddress = buyerToAddress;
  }

  public String getSellerFromAddress() {
    return sellerFromAddress;
  }

  public void setSellerFromAddress(String sellerFromAddress) {
    this.sellerFromAddress = sellerFromAddress;
  }

  public String getSellerToAddress() {
    return sellerToAddress;
  }

  public void setSellerToAddress(String sellerToAddress) {
    this.sellerToAddress = sellerToAddress;
  }

  public Date getDatetime() {
    return datetime;
  }

  public void setDatetime(Date datetime) {
    this.datetime = datetime;
  }

  public Date getTrxDate() {
    return trxDate;
  }

  public void setTrxDate(Date trxDate) {
    this.trxDate = trxDate;
  }

  public User getSeller() throws SQLException {
      System.out.println("SELLER ID: " + this.getSellerId());
      User u = UserDAO.findByColumn("ID", this.getSellerId() + "");
      System.out.println(u);
      return u;
  }
}
