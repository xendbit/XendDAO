/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idempotent.xendbit.xendserver.models;


import java.util.Date;

public class SendObject {
    private String transactionHex;

    private String toAddress;
    private String btcValue;
    private String emailAddress;
    private String password;

    private String bankCode;
    private String accountNumber;

    //buybit
    private String sellOrderTransactionId;
    private User soldTo;

    //xnd
    private String passphrase;

    //Exchange
    private double amountToSell;
    private double amountToRecieve;
    private String fromCoin;
    private String toCoin;
    private double rate;
    private long sellerId;
    private long buyerId;
    private String buyerFromAddress;
    private String buyerToAddress;
    private String sellerFromAddress;
    private String sellerToAddress;
    private Date datetime;
    private Date trxDate;
    private boolean active;
    private String networkAddress;
    private String currencyId;
    private String status;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }
    
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
	public String getNetworkAddress() {
		return networkAddress;
	}

	public void setNetworkAddress(String networkAddress) {
		this.networkAddress = networkAddress;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTransactionHex() {
		return transactionHex;
	}

	public void setTransactionHex(String transactionHex) {
		this.transactionHex = transactionHex;
	}

	public String getToAddress() {
		return toAddress;
	}

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public void setBtcValue(String btcValue) {
        this.btcValue = btcValue;
    }

    public String getBtcValue() {
		return btcValue;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSellOrderTransactionId() {
		return sellOrderTransactionId;
	}

	public void setSellOrderTransactionId(String sellOrderTransactionId) {
		this.sellOrderTransactionId = sellOrderTransactionId;
	}

	public User getSoldTo() {
		return soldTo;
	}

	public void setSoldTo(User soldTo) {
		this.soldTo = soldTo;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}
}
