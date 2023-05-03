package com.chaim.coupons.dal;

import com.chaim.coupons.dto.CouponPurchaseDto;
import com.chaim.coupons.dto.CustomerDto;
import com.chaim.coupons.entitys.CustomerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerDal extends CrudRepository<CustomerEntity, Long> {
    @Query("SELECT new com.chaim.coupons.dto.CustomerDto" +
            "(cu.id,cu.firstName,cu.lastName,cu.address,cu.phoneNumber) "
            + "FROM CustomerEntity cu   where cu.id = :id")
    CustomerDto findByCustomerId(@Param("id")long id);
    @Query("SELECT new com.chaim.coupons.dto.CustomerDto" +
            "(cu.id,cu.firstName,cu.lastName,cu.address,cu.phoneNumber) "
            + "FROM CustomerEntity cu ")
    List<CustomerDto> findCustomers(Pageable pageable);

}
