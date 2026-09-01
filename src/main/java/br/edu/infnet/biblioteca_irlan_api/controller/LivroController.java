package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Livro;
import br.edu.infnet.biblioteca_irlan_api.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<Collection<Livro>> obterLivros() {
        return ResponseEntity.ok(livroService.obterLista());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> obterLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.obterPorId(id));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Livro> buscarLivroPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroService.buscarLivroPorTitulo(titulo));
    }

    @PostMapping
    public ResponseEntity<Livro> incluirLivro(@RequestBody Livro livro) {
        livroService.incluir(livro);
        return ResponseEntity.status(CREATED).body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> alterarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        livroService.alterarLivro(id, livro);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id) {
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
