package com.chaim.coupons.entitys;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(name ="name",unique = true,nullable =  false)
    private String name;
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "category")
    private List<CouponEntity> coupon;

    public CategoryEntity(){

    }

    public CategoryEntity(int id, String name) {
        this(name);
        this.id = id;
    }
    public CategoryEntity(String name) {
        this.name = name;
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

    public List<CouponEntity> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<CouponEntity> coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coupon=" + coupon +
                '}';
    }
}
