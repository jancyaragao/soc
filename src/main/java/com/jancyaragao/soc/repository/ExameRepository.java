package com.jancyaragao.soc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jancyaragao.soc.model.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {
    
}
