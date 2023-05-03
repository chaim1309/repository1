package com.chaim.coupons.dto;

import java.util.Date;

public class CouponPurchaseDto {
    private int id;
    private int couponId;
    private String couponName;
    private String description;
    private float price;
    private Date startDate;
    private Date endDate;
    private Date timestamp;
    private int amount;

    public CouponPurchaseDto(int id, int couponId, String couponName,  Date timestamp, int amount) {
        this.id = id;
        this.couponId = couponId;
        this.couponName = couponName;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public CouponPurchaseDto(int id, int couponId, String couponName, String description,
                             float price, Date startDate, Date endDate, Date timestamp, int amount) {
        this.id = id;
        this.couponId = couponId;
        this.couponName = couponName;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CouponPurchaseDto{" +
                "id=" + id +
                ", couponId=" + couponId +
                ", couponName='" + couponName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                '}';
    }
}
