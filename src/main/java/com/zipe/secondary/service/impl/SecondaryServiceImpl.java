package com.zipe.secondary.service.impl;

import com.zipe.enums.ResourceEnum;
import com.zipe.jdbc.InvoiceJDBC;
import com.zipe.model.secondary.Reinvpf;
import com.zipe.secondary.service.SecondaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SecondaryServiceImpl implements SecondaryService {

    @Autowired
    private InvoiceJDBC jdbc;

    @Override
    @Transactional(readOnly = true)
    public void queryTest() {
        ResourceEnum resource = ResourceEnum.SQL.getResource("FIND_SECONDARY");
        List<Reinvpf> detailList = jdbc.queryForList(resource, Reinvpf.class);
        System.out.println(detailList.size());
    }
}
