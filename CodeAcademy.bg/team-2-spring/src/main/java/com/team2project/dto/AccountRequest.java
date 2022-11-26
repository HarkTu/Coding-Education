package com.team2project.dto;

import com.team2project.dto.Account.FamilyStatus;
import com.team2project.dto.Account.PrimaryCurrency;
import java.math.BigDecimal;

public class AccountRequest {

  private String firstName;
  private String lastName;
  private BigDecimal salary;
  private FamilyStatus familyStatus;
  private Integer familyMembers;
  private PrimaryCurrency primaryCurrency;

  public AccountRequest() {
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

  public Integer getFamilyMembers() {
    return familyMembers;
  }

  public void setFamilyMembers(Integer familyMembers) {
    this.familyMembers = familyMembers;
  }

  public PrimaryCurrency getPrimaryCurrency() {
    return primaryCurrency;
  }

  public void setPrimaryCurrency(PrimaryCurrency primaryCurrency) {
    this.primaryCurrency = primaryCurrency;
  }

}
