package com.chaim.coupons.dto;

import java.util.Date;

public class CouponDto {
    private int id;
    private String name;
    private float price;
    private String description;
    private Date startDate;
    private Date endDate;
    private String imgUrl;
    private String companyName;
    private String categoryName;
    public CouponDto(int id,String name,float price ,String imgUrl){
        this.id=id;
        this.name=name;
        this.price=price;

        this.imgUrl=imgUrl;
    }
    public CouponDto(int id, String name, float price, String description, Date startDate,
                     Date endDate,String imgUrl, String companyName, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imgUrl=imgUrl;
        this.companyName = companyName;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CouponDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", imgUrl='" + imgUrl + '\'' +
                ", companyName='" + companyName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
