package com.idempotent.xendbit.xendserver.models;


public class BuyRequest {
    private User user;
    private String nairaAmount;
    private String reference;
    private String bitcoinAmount;
    private String status;
    private String provider;
    private String sellOrderId;

    //Flutterwave
    private String flutterwaveAuthToken;
    private SendObject sendObject;
    
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getNairaAmount() {
		return nairaAmount;
	}
	public void setNairaAmount(String nairaAmount) {
		this.nairaAmount = nairaAmount;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getBitcoinAmount() {
		return bitcoinAmount;
	}
	public void setBitcoinAmount(String bitcoinAmount) {
		this.bitcoinAmount = bitcoinAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getSellOrderId() {
		return sellOrderId;
	}
	public void setSellOrderId(String sellOrderId) {
		this.sellOrderId = sellOrderId;
	}
	public String getFlutterwaveAuthToken() {
		return flutterwaveAuthToken;
	}
	public void setFlutterwaveAuthToken(String flutterwaveAuthToken) {
		this.flutterwaveAuthToken = flutterwaveAuthToken;
	}
	public SendObject getSendObject() {
		return sendObject;
	}
	public void setSendObject(SendObject sendObject) {
		this.sendObject = sendObject;
	}
    
    
}
