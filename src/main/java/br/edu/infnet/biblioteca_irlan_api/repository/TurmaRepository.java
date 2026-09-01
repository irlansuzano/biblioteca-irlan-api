package br.edu.infnet.biblioteca_irlan_api.repository;

import br.edu.infnet.biblioteca_irlan_api.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    Optional<Turma> findByIdentificador(String identificador);
}
