package com.chaim.coupons.entitys;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
   // @Column(name = "ID")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private UserEntity user;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<PurchaseEntity> purchaseEntities;

    public CustomerEntity() {

    }

    public CustomerEntity(Long id, UserEntity user, String firstName, String lastName, String address, String phoneNumber) {
        this(user,firstName,  lastName, address,  phoneNumber);
        this.id = id;

    }

    public CustomerEntity(UserEntity user, String firstName, String lastName, String address, String phoneNumber) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<PurchaseEntity> getPurchaseEntities() {
        return purchaseEntities;
    }

    public void setPurchaseEntities(List<PurchaseEntity> purchaseEntities) {
        this.purchaseEntities = purchaseEntities;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
