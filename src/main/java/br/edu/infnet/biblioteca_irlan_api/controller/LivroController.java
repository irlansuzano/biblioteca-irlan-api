package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Livro;
import br.edu.infnet.biblioteca_irlan_api.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/livros")
@Tag(name = "Livro", description = "Endpoints para gestão de livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista de todos os livros cadastrados")
    public ResponseEntity<Collection<Livro>> obterLivros() {
        return ResponseEntity.ok(livroService.obterLista());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter livro por ID", description = "Retorna os detalhes de um livro específico pelo seu ID")
    public ResponseEntity<Livro> obterLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.obterPorId(id));
    }

    @GetMapping("/titulo/{titulo}")
    @Operation(summary = "Obter livro por título", description = "Busca um livro através do seu título")
    public ResponseEntity<Livro> buscarLivroPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroService.buscarLivroPorTitulo(titulo));
    }

    @PostMapping
    @Operation(summary = "Incluir livro", description = "Cria um novo registro de livro no sistema")
    public ResponseEntity<Livro> incluirLivro(@Valid @RequestBody Livro livro) {
        livroService.incluir(livro);
        return ResponseEntity.status(CREATED).body(livro);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar livro", description = "Atualiza os dados de um livro existente")
    public ResponseEntity<Livro> alterarLivro(@PathVariable Long id, @Valid @RequestBody Livro livro) {
        livroService.alterarLivro(id, livro);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir livro", description = "Exclui o registro de um livro do sistema")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id) {
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
