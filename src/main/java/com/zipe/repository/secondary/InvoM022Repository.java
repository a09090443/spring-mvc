package com.zipe.repository.secondary;

import com.zipe.model.secondary.InvoM022;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoM022Repository extends JpaRepository<InvoM022, Integer> {
}
