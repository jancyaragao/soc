package com.jancyaragao.soc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jancyaragao.soc.model.Marcacao;

@Repository
public interface MarcacaoRepository extends JpaRepository<Marcacao, Long> {

    @Query("SELECT obj FROM Marcacao obj WHERE obj.dataExame BETWEEN :min AND :max ORDER BY obj.dataExame ASC")
    List<Marcacao> findMarcacoesByDate(LocalDate min, LocalDate max);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Marcacao m WHERE m.funcionario.codigo = :funcionarioCodigo AND m.exame.codigo = :exameCodigo AND m.dataExame = :dataExame")
    boolean exists(@Param("funcionarioCodigo") Long funcionarioCodigo, @Param("exameCodigo") Long exameCodigo,
            @Param("dataExame") LocalDate dataExame);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Marcacao m WHERE m.exame.codigo = :exameCodigo")
    boolean existsByExame(@Param("exameCodigo") Long exameCodigo);

}
