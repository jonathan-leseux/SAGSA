package ds.sagsa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ds.sagsa.models.SAPZ;

@Repository
public interface SAPZRepository extends JpaRepository<SAPZ, Long> {

    // Relação: Usuario cria Sapz
    List<SAPZ> findByUsuario_IdUsuario(Long id_usuario);

    // Relação: Instrutor cria Sapz
    List<SAPZ> findByInstrutor_IdInstrutor(Long id_instrutor);
}
