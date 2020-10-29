package com.zipe.service.impl;

import com.zipe.aspect.TargetDataSource;
import com.zipe.model.primary.InvoM020;
import com.zipe.model.secondary.Reinvpf;
import com.zipe.repository.primary.InvoM020Repository;
import com.zipe.repository.secondary.ReinvpfRepository;
import com.zipe.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    @Autowired
    private InvoM020Repository invoM020Repository;

    @Autowired
    private ReinvpfRepository reinvpfRepository;

    @Override
    @TargetDataSource(value = "primary")
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
    @TargetDataSource(value = "secondary")
    public String secondaryData() {
        List<Reinvpf> reinvpf = reinvpfRepository.findAll();
        System.out.println(reinvpf.size());
        return null;
    }
}
