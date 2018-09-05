package com.idempotent.xendbit.xendserver.models;

import java.util.Date;

public class CashOrder {
    double amount;
    double btcValue;
    double price;
    User user;
    String status = "STARTED";
    String accountNumber;
    String bankCode;
    String transactionId;
    Date datetime;
    User soldToUser;
    Date timeSold;
    Date buyerStartTime;
    String buyerAddress;
    boolean active;
    String authCode;
    String acceptedPaymentMethods;
    String walletCode;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBtcValue() {
		return btcValue;
	}
	public void setBtcValue(double btcValue) {
		this.btcValue = btcValue;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public User getSoldToUser() {
		return soldToUser;
	}
	public void setSoldToUser(User soldToUser) {
		this.soldToUser = soldToUser;
	}
	public Date getTimeSold() {
		return timeSold;
	}
	public void setTimeSold(Date timeSold) {
		this.timeSold = timeSold;
	}
	public Date getBuyerStartTime() {
		return buyerStartTime;
	}
	public void setBuyerStartTime(Date buyerStartTime) {
		this.buyerStartTime = buyerStartTime;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getAcceptedPaymentMethods() {
		return acceptedPaymentMethods;
	}
	public void setAcceptedPaymentMethods(String acceptedPaymentMethods) {
		this.acceptedPaymentMethods = acceptedPaymentMethods;
	}
	public String getWalletCode() {
		return walletCode;
	}
	public void setWalletCode(String walletCode) {
		this.walletCode = walletCode;
	}
       
}
