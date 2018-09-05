package com.idempotent.xendbit.xendserver.models;

public class APIUser {
    private int id;
    private String apiKey;
    
    public int getId() {
		return id;
	}
    
    public void setId(int id) {
		this.id = id;
	}
    
    public String getApiKey() {
		return apiKey;
	}
    
    public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
