package com.jancyaragao.soc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jancyaragao.soc.model.ExameFuncionario;

@Repository
public interface ExameFuncionarioRepository extends JpaRepository<ExameFuncionario, Long> {
    
}
