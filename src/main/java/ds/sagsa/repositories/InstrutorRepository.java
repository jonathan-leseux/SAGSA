package ds.sagsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ds.sagsa.models.Instrutor;

@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    Instrutor findByUsername(String nome);
}