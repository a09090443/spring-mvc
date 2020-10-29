package com.zipe.repository.secondary;

import com.zipe.model.secondary.Reinvpf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReinvpfRepository extends JpaRepository<Reinvpf, Integer> {
}
