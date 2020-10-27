package com.zipe.service.impl;

import com.zipe.model.primary.InvoM020;
import com.zipe.model.secondary.InvoM022;
import com.zipe.repository.primary.InvoM020Repository;
import com.zipe.repository.secondary.InvoM022Repository;
import com.zipe.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    @Autowired
    private InvoM020Repository invoM020Repository;

    @Autowired
    private InvoM022Repository invoM022Repository;

    @Override
    @Transactional(transactionManager="primaryTransactionManager")
    public String primaryData() {
        List<InvoM020> invoM020 = invoM020Repository.findAll();
        System.out.println(invoM020.size());
        System.out.println("test");
        System.out.println("test");
        System.out.println("test");
        System.out.println("test");
        return "Test";
    }

    @Override
    @Transactional(transactionManager="secondaryTransactionManager")
    public String secondaryData() {
        List<InvoM022> invoM022 = invoM022Repository.findAll();
        System.out.println(invoM022.size());
        return null;
    }
}
