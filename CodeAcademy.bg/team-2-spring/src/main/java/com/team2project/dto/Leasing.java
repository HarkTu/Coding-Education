package com.team2project.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class Leasing {

    private Long leasingId;
    private String item;
    private BigDecimal pmtAmount;
    private Date startDate;
    private Date endDate;
    private Status status;
    private Long userId;

    public Long getLeasingId() {
        return leasingId;
    }

    public void setLeasingId(Long leasingId) {
        this.leasingId = leasingId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getPmtAmount() {
        return pmtAmount;
    }

    public void setPmtAmount(BigDecimal pmtAmount) {
        this.pmtAmount = pmtAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public enum Status {
        ACTIVE, PAID
    }


}
