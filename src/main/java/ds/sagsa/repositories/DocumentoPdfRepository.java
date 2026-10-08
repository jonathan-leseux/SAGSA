package ds.sagsa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ds.sagsa.models.DocumentoPdf;

@Repository
public interface DocumentoPdfRepository extends JpaRepository<DocumentoPdf, Long> {

    // Relação: Usuario gera DocumentoPdf
    List<DocumentoPdf> findByUsuario_id_usuario(Long id_usuario);
}
