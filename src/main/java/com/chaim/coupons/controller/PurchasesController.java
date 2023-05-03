package com.chaim.coupons.controller;

import com.chaim.coupons.dto.CouponPurchaseDto;
import com.chaim.coupons.dto.CustomerPurchasesDto;
import com.chaim.coupons.entitys.PurchaseEntity;
import com.chaim.coupons.exceptions.ServerException;
import com.chaim.coupons.logic.PurchasesLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {
    private PurchasesLogic purchasesLogic;

    @Autowired
    public PurchasesController(PurchasesLogic purchasesLogic) {
        this.purchasesLogic = purchasesLogic;
    }

    @PostMapping
    public void createPurchase(@RequestBody PurchaseEntity purchase) throws ServerException {
        this.purchasesLogic.addPurchases(purchase);
    }

    @DeleteMapping("{purchaseId}")
    public void deletePurchase(@PathVariable("purchaseId") long id) throws ServerException {
        this.purchasesLogic.remove(id);

    }

    @GetMapping("{purchaseId}")
    public CouponPurchaseDto getPurchase(@PathVariable("purchaseId") int id) throws ServerException {
        CouponPurchaseDto couponPurchaseDto = this.purchasesLogic.getPurchase(id);
        return couponPurchaseDto;
    }
    @GetMapping("/getExtendedOnPurchase")
    public CouponPurchaseDto getExtendedNyPurchase(@RequestParam("couponId") int couponId )throws ServerException {
        CouponPurchaseDto moreInformation = purchasesLogic.getExtendedOnPurchase(couponId);
        return moreInformation;

    }
    @GetMapping("/getMyPurchases")
    public Iterable<CouponPurchaseDto> getNyPurchases(@RequestParam("customerId") int customerId, @RequestParam("pageNumber") int pageNumber) throws ServerException {
        Iterable<CouponPurchaseDto> myPurchases = purchasesLogic.getPurchasesByCustomerId(customerId, pageNumber);
        return myPurchases;

    }
    @GetMapping("/getExtendedOnPurchases")
    public Iterable<CouponPurchaseDto> getExtendedNyPurchases(@RequestParam("customerId")int customerId, @RequestParam("pageNumber") int pageNumber)throws ServerException {
       Iterable<CouponPurchaseDto> moreInformation = purchasesLogic.getExtendedOnPurchases(customerId,pageNumber);
        return moreInformation;

    }

    @GetMapping("/getCustomersPurchaseByCompanyId")
    public Iterable<CustomerPurchasesDto> getCusPurByCompanyIdByPageNumber(@RequestParam("companyId") int companyId, @RequestParam("pageNUmber") int pageNumber) throws ServerException {
        Iterable<CustomerPurchasesDto>customerPurDto=purchasesLogic.getAllCustomerPurchasesByCompanyId(companyId, pageNumber);
        return customerPurDto;
    }


}
