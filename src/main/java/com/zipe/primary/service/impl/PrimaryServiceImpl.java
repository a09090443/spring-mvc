package com.zipe.primary.service.impl;

import com.zipe.enums.ResourceEnum;
import com.zipe.jdbc.InvoiceJDBC;
import com.zipe.primary.service.PrimaryService;
import org.springframework.beans.factory.annotation.Autowired;

public class PrimaryServiceImpl implements PrimaryService {
    @Autowired
    private InvoiceJDBC jdbc;

    @Override
    public void queryTest() {
        ResourceEnum resource = ResourceEnum.SQL_JOBS.getResource("FIND_ALL_JOBS");
//        List<ScheduleJobVO> detailList = jdbc.queryForList(resource, ScheduleJobVO.class);
    }
}
