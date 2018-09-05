/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idempotent.xendbit.xendserver.models;

import java.util.HashMap;

/**
 * @author aardvocate
 */

public class User {
    public String getFullname() {
        return fullname;
    }

    String fullname;
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public enum WALLET_CODES {
        BTC, LTC, DASH, BTCTEST, DASHTEST, LTCTEST, ETH, ETHTEST, XND
    }

    private Long id;
    private String password;
    private String networkAddress;
    private String phoneNumber;
    private String emailAddress;
    private String surName;
    private String firstName;
    private String middleName;
    private String bvn;
    private String idImage;
    private String idType;
    private String idNumber;
    private boolean isApproved;
    private boolean isActivated;
    private String accountType;
    private String country;
    private String walletCode;
    private String walletType;
    private String bankName;
    private String bankCode;
    private String accountNumber;
    private String accountName;
    private long dateRegistered;
    private boolean isBeneficiary;
    private HashMap<String, Object> kyc;
    private String passphrase;
    private String publicKey;
    private String xendNetworkAddress;
    private String currencyId;    

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }       

    public HashMap<String, Object> getKyc() {
        return kyc;
    }

    public void setKyc(HashMap<String, Object> kyc) {
        this.kyc = kyc;
    }

    public String getXendNetworkAddress() {
        return xendNetworkAddress;
    }

    public void setXendNetworkAddress(String xendNetworkAddress) {
        this.xendNetworkAddress = xendNetworkAddress;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public boolean isBeneficiary() {
        return isBeneficiary;
    }

    public void setBeneficiary(boolean beneficiary) {
        isBeneficiary = beneficiary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
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

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWalletCode() {
        return walletCode;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public static final class AccountKYC {
        private String surName;
        private String firstName;
        private String middleName;
        private String motherMaidenName;
        private Long dob;
        private String gender;
        private String maritalStatus;
        private String email;
        private String phoneNumber;
        private String houseNumberOfBirth;
        private String streetOfBirth;
        private String cityOfBirth;
        private String countryOfBirth;
        private String houseNumberOfResidence;
        private String streetOfResidence;
        private String cityOfResidence;
        private String countryOfResidence;
        private String idType;
        private String idNumber;
        private Long idIssueDate;
        private Long idExpiryDate;
        private String issuerAuthority;
        private String proofOfResidentialAddress;
        private String proofOfIdentity;
        private String passportPhoto;
        private String bankName;
        private String bankCode;
        private String bankAccountName;
        private String bankAccountNumber;
        private String bvn;
        private Long dateRegistered;

        public void setDateRegistered(Long dateRegistered) {
            this.dateRegistered = dateRegistered;
        }

        public Long getDateRegistered() {
            return dateRegistered;
        }                

        public String getSurName() {
            return surName;
        }

        public void setSurName(String surName) {
            this.surName = surName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getMotherMaidenName() {
            return motherMaidenName;
        }

        public void setMotherMaidenName(String motherMaidenName) {
            this.motherMaidenName = motherMaidenName;
        }

        public Long getDob() {
            return dob;
        }

        public void setDob(Long dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }

        public void setMaritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getHouseNumberOfBirth() {
            return houseNumberOfBirth;
        }

        public void setHouseNumberOfBirth(String houseNumberOfBirth) {
            this.houseNumberOfBirth = houseNumberOfBirth;
        }

        public String getStreetOfBirth() {
            return streetOfBirth;
        }

        public void setStreetOfBirth(String streetOfBirth) {
            this.streetOfBirth = streetOfBirth;
        }

        public String getCityOfBirth() {
            return cityOfBirth;
        }

        public void setCityOfBirth(String cityOfBirth) {
            this.cityOfBirth = cityOfBirth;
        }

        public String getCountryOfBirth() {
            return countryOfBirth;
        }

        public void setCountryOfBirth(String countryOfBirth) {
            this.countryOfBirth = countryOfBirth;
        }

        public String getHouseNumberOfResidence() {
            return houseNumberOfResidence;
        }

        public void setHouseNumberOfResidence(String houseNumberOfResidence) {
            this.houseNumberOfResidence = houseNumberOfResidence;
        }

        public String getStreetOfResidence() {
            return streetOfResidence;
        }

        public void setStreetOfResidence(String streetOfResidence) {
            this.streetOfResidence = streetOfResidence;
        }

        public String getCityOfResidence() {
            return cityOfResidence;
        }

        public void setCityOfResidence(String cityOfResidence) {
            this.cityOfResidence = cityOfResidence;
        }

        public String getCountryOfResidence() {
            return countryOfResidence;
        }

        public void setCountryOfResidence(String countryOfResidence) {
            this.countryOfResidence = countryOfResidence;
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

        public Long getIdIssueDate() {
            return idIssueDate;
        }

        public void setIdIssueDate(Long idIssueDate) {
            this.idIssueDate = idIssueDate;
        }

        public Long getIdExpiryDate() {
            return idExpiryDate;
        }

        public void setIdExpiryDate(Long idExpiryDate) {
            this.idExpiryDate = idExpiryDate;
        }

        public String getIssuerAuthority() {
            return issuerAuthority;
        }

        public void setIssuerAuthority(String issuerAuthority) {
            this.issuerAuthority = issuerAuthority;
        }

        public String getProofOfResidentialAddress() {
            return proofOfResidentialAddress;
        }

        public void setProofOfResidentialAddress(String proofOfResidentialAddress) {
            this.proofOfResidentialAddress = proofOfResidentialAddress;
        }

        public String getProofOfIdentity() {
            return proofOfIdentity;
        }

        public void setProofOfIdentity(String proofOfIdentity) {
            this.proofOfIdentity = proofOfIdentity;
        }

        public String getPassportPhoto() {
            return passportPhoto;
        }

        public void setPassportPhoto(String passportPhoto) {
            this.passportPhoto = passportPhoto;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankAccountName() {
            return bankAccountName;
        }

        public void setBankAccountName(String bankAccountName) {
            this.bankAccountName = bankAccountName;
        }

        public String getBankAccountNumber() {
            return bankAccountNumber;
        }

        public void setBankAccountNumber(String bankAccountNumber) {
            this.bankAccountNumber = bankAccountNumber;
        }

        public String getBvn() {
            return bvn;
        }

        public void setBvn(String bvn) {
            this.bvn = bvn;
        }
    }
}
