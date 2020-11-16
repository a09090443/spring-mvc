package com.zipe.primary.service.impl;

import com.zipe.enums.ResourceEnum;
import com.zipe.jdbc.InvoiceJDBC;
import com.zipe.model.primary.InvoM020;
import com.zipe.primary.service.PrimaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PrimaryServiceImpl implements PrimaryService {

    private final InvoiceJDBC jdbc;
    
    @Autowired
    PrimaryServiceImpl(InvoiceJDBC jdbc){
        this.jdbc = jdbc;
    }

    @Override
    @Transactional(readOnly = true)
    public void queryTest() {
        ResourceEnum resource = ResourceEnum.SQL.getResource("FIND_PRIMARY");
        List<InvoM020> detailList = jdbc.queryForList(resource, InvoM020.class);
        System.out.println(detailList.size());
    }
}
