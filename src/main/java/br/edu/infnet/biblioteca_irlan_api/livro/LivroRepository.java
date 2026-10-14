package br.edu.infnet.biblioteca_irlan_api.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l WHERE l.titulo like %:titulo%")
    Optional<Livro> findByTitulo(String titulo);
}
