package br.edu.infnet.biblioteca_irlan_api.livro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de livros recuperada com sucesso"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Collection<LivroResponseDTO>> obterLivros() {
        return ResponseEntity.ok(livroService.obterLivros());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter livro por ID", description = "Retorna os detalhes de um livro específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro encontrado"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    public ResponseEntity<LivroResponseDTO> obterLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.obterLivroPorId(id));
    }

    @GetMapping("/titulo/{titulo}")
    @Operation(summary = "Obter livro por título", description = "Busca um livro através do seu título")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro encontrado"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    public ResponseEntity<LivroResponseDTO> buscarLivroPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroService.buscarLivroPorTitulo(titulo));
    }

    @PostMapping
    @Operation(summary = "Incluir livro", description = "Cria um novo registro de livro no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<LivroResponseDTO> incluirLivro(@Valid @RequestBody LivroRequestDTO livro) {
        return ResponseEntity.status(CREATED).body(livroService.cadastrarLivro(livro));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar livro", description = "Atualiza os dados de um livro existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<LivroResponseDTO> alterarLivro(@PathVariable Long id, @Valid @RequestBody LivroRequestDTO livro) {
        return ResponseEntity.ok(livroService.alterarLivro(id, livro));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir livro", description = "Exclui o registro de um livro do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Livro excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id) {
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
