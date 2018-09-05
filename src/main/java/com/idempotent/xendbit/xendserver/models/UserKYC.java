package com.idempotent.xendbit.xendserver.models;

public class UserKYC {
    private Long id;
    private Long userId;
    private String idType;
    private String idNumber;
    private String idImage;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getIdImage() {
		return idImage;
	}
	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}
       
}
