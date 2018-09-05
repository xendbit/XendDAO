package com.idempotent.xendbit.xendserver.models;

public class Transaction {
	private long confirmations;
	private int index;
	private String hash;
	private double value;
	private boolean isIncoming;
	private boolean isSpent;
	private String url;

	public long getConfirmations() {
		return confirmations;
	}

	public void setConfirmations(long confirmations) {
		this.confirmations = confirmations;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isIncoming() {
		return isIncoming;
	}

	public void setIncoming(boolean isIncoming) {
		this.isIncoming = isIncoming;
	}

	public boolean isSpent() {
		return isSpent;
	}

	public void setSpent(boolean isSpent) {
		this.isSpent = isSpent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}