package ds.sagsa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ds.sagsa.models.PlanoDeCurso;

@Repository
public interface PlanoDeCursoRepository extends JpaRepository<PlanoDeCurso, Long> {

    // Relação: Sapz compõe PlanoDeCurso
    List<PlanoDeCurso> findBySapz_IdSapz(Long id_sapz);
}