package com.zipe.model.secondary;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "InvoM022")
public class InvoM022 implements Serializable {
    @Id
    private String companyNo;
    private String branchNo;
    private String invoiceYYYYMM;
    private String fSChar;
    private String startNo;
    private Double invoiceBook;
    private String invoiceStartNo;
    private String invoiceEndNo;
    private String departNo;
    private String projectNo;
    private String invoiceKind;
    private String useYYYYMM;
    private String maxInvoiceNo;
    private String maxInvoiceDate;
    private String endYES;
    private String processInvoiceNo;
    private String closeYES;
}
