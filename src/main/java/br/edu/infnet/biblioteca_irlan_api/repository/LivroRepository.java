package br.edu.infnet.biblioteca_irlan_api.repository;

import br.edu.infnet.biblioteca_irlan_api.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);
}
