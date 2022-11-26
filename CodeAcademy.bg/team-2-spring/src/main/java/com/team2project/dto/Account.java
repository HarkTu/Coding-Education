package com.team2project.dto;

import java.math.BigDecimal;

public class Account {
    private Long accountId;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private FamilyStatus familyStatus;
    private int familyMembers;
    private PrimaryCurrency primaryCurrency;
    private String creditRating;
    private Long userId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public FamilyStatus getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(FamilyStatus familyStatus) {
        this.familyStatus = familyStatus;
    }

    public int getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(int familyMembers) {
        this.familyMembers = familyMembers;
    }

    public PrimaryCurrency getPrimaryCurrency() {
        return primaryCurrency;
    }

    public void setPrimaryCurrency(PrimaryCurrency primaryCurrency) {
        this.primaryCurrency = primaryCurrency;
    }


    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return " Your account information: " + "\n"
              +" Name - " + firstName + "\n"
              +" Surname - " + lastName + "\n"
              +" Salary - " + salary + "\n"
              +" Family Status - " + familyStatus + "\n"
              +" Family Members - " + familyMembers + "\n"
              +" Primary Currency - " + primaryCurrency;
    }

    public enum FamilyStatus {
        MARRIED,
        SINGLE
    }

    public enum PrimaryCurrency {
        EUR,
        BGN,
        USD
    }
}


