package com.zipe.model.primary;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "InvoM020")
public class InvoM020 implements Serializable {
    @Id
    private String companyNo;
    private String branchNo;
    private String invoiceYYYYMM;
    private String fSChar;
    private String startNo;
    private Double invoiceBook;
    private String processInvoiceNo;
    private String controlDepartNo;
}
