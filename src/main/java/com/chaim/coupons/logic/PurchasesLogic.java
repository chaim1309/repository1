package com.chaim.coupons.logic;

import com.chaim.coupons.conts.Const;
import com.chaim.coupons.dal.IPurchaseDal;
import com.chaim.coupons.dto.CouponPurchaseDto;
import com.chaim.coupons.dto.CustomerPurchasesDto;
import com.chaim.coupons.entitys.PurchaseEntity;
import com.chaim.coupons.enums.ErrorTypes;
import com.chaim.coupons.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class PurchasesLogic {

    private IPurchaseDal purchasesDal;

    @Autowired

    public PurchasesLogic(IPurchaseDal purchasesDal) {
        this.purchasesDal = purchasesDal;
    }


    public int addPurchases(PurchaseEntity purchase) throws ServerException {
        try {
            purchasesDal.save(purchase);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Failed to create purchases " + purchase.toString(), e);
        }
        return purchase.getId();
    }

    public void remove(Long id) throws ServerException {
        try {
            purchasesDal.deleteById(id);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Failed to remove purchase " + id, e);
        }


    }

    public CouponPurchaseDto getPurchase(int id) throws ServerException {
        CouponPurchaseDto MyPurchase;
        try {
            MyPurchase = purchasesDal.findPurchaseById(id);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR, "Error in getPurchases(), id = " + id, e);
        }
        return MyPurchase;
    }
    public CouponPurchaseDto getExtendedOnPurchase(int couponId)throws ServerException{
        CouponPurchaseDto extendedOnPurchase;
        try {
            extendedOnPurchase=purchasesDal.extendedOnMyPurchase(couponId);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Error in getPurchases(), couponId = " + couponId, e );
        }

        return extendedOnPurchase;
    }
    public List<CouponPurchaseDto> getExtendedOnPurchases(int customerId,int pageNumber) throws ServerException {
        List<CouponPurchaseDto> extendedOnPurchases;
        Pageable pageable= PageRequest.of(pageNumber-1,Const.AMOUNT_OF_ITEMS_PER_PAGE);
        try {
            extendedOnPurchases  = purchasesDal.extendedOnMyPurchases( customerId,pageable);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Error in getPurchases" + customerId , e);
        }

        return  extendedOnPurchases;
    }
    public List<CouponPurchaseDto> getPurchasesByCustomerId(int customerId, int pageNumber) throws ServerException {
        List<CouponPurchaseDto> myPurchases;
        Pageable pageable= PageRequest.of(pageNumber-1,Const.AMOUNT_OF_ITEMS_PER_PAGE);
        try {
            myPurchases = purchasesDal.findByCustomerId(customerId, pageable);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Error in getPurchases" + customerId, e);
        }
        return myPurchases;
    }


    public List<CustomerPurchasesDto> getAllCustomerPurchasesByCompanyId(int companyId, int pageNumber) throws ServerException {
        List<CustomerPurchasesDto>customerPurDto;
        Pageable pageable= PageRequest.of(pageNumber-1,Const.AMOUNT_OF_ITEMS_PER_PAGE);
        try {
            customerPurDto=purchasesDal.findByCompanyId(companyId,pageable);
        }catch (Exception e){
            throw new ServerException(ErrorTypes.GENERAL_ERROR,"Error in get purchases by company id "+ companyId, e);
        }
        return customerPurDto ;
    }
}
