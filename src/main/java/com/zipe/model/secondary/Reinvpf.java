package com.zipe.model.secondary;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Reinvpf implements Serializable {

    @Id
    @Column(name = "INV_BILLNO")
    private String invBillNo;
    private String sysStatus;
    private String creator;
    private LocalDate createTime;
    private String updator;
    private LocalDate updateTime;
    @Column(name = "INV_BILLDATE")
    private LocalDate invBillDate;
    @Column(name = "INV_PRICE")
    private Long invPrice;
    @Column(name = "INV_TAX")
    private Long invTax;
    @Column(name = "INV_DISCOUNTMONEY")
    private Long invDiscountMoney;
    @Column(name = "INV_DISCOUNTTIMES")
    private Long invDiscountTimes;
    @Column(name = "INV_PRINTYES")
    private String invPrintYes;
    @Column(name = "INV_PRINTTIMES")
    private Long invPrintTimes;
    @Column(name = "INV_DELYES")
    private String invDelYes;
    @Column(name = "INV_POINTNO")
    private String invPointNo;
    @Column(name = "INV_DEIDATE")
    private LocalDate invDeiDate;
    @Column(name = "INV_ISGETBACK")
    private String invIsGetBack;
    @Column(name = "INV_NAME")
    private String invName;
    @Column(name = "INV_ID")
    private String invId;
    @Column(name = "INV_ADDRESS")
    private String invAddress;
    @Column(name = "INV_EXPAY")
    private String invExPay;
    @Column(name = "INV_REMARK")
    private String invRemark;
    @Column(name = "INV_CARBONPRINTYES")
    private String invCarbonPrintYes;
    @Column(name = "INV_CARBONPRINTDAY")
    private LocalDate invCarbonPrintDay;
    @Column(name = "INV_BILL")
    private String invBill;
    @Column(name = "INV_ACCOUNTING")
    private String invAccounting;
    @Column(name = "INV_TRYSTATUS")
    private String invTryStatus;
    @Column(name = "INV_TAXING")
    private String invTaxing;
    @Column(name = "INV_DELACC")
    private String invDelAcc;
}
