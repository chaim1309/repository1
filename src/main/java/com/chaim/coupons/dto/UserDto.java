package com.chaim.coupons.dto;

import com.chaim.coupons.enums.UserType;


public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private UserType type;
    private String companyName;

    public UserDto(Long id, String userName, String password, UserType type, String companyName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
