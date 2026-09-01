package br.edu.infnet.biblioteca_irlan_api.repository;

import br.edu.infnet.biblioteca_irlan_api.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByRegistroProfissional(String registroProfissional);
}
