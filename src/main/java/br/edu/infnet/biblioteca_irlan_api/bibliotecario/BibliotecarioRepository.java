package br.edu.infnet.biblioteca_irlan_api.bibliotecario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Long> {
    Optional<Bibliotecario> findByLogin(String login);
}
