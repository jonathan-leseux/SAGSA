package ds.sagsa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ds.sagsa.models.SAPZ;

@Repository
public interface SAPZRepository extends JpaRepository<SAPZ, Long> {

    // Relação: Instrutor cria Sapz
    List<SAPZ> findByInstrutor_Id_instrutor(Long id_instrutor);
}
