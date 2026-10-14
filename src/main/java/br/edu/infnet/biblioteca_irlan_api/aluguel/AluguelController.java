package br.edu.infnet.biblioteca_irlan_api.aluguel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/alugueis")
@Tag(name = "Aluguel", description = "Endpoints para gestão de aluguéis de livros")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os aluguéis", description = "Retorna uma lista de todos os registros de aluguéis, podendo filtrar por livro ou aluno")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de aluguéis recuperada com sucesso"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<AluguelResponseDTO>> obterAlugueis(
            @RequestParam(required = false) Long idLivro,
            @RequestParam(required = false) Long idAluno) {

        if (idLivro != null) {
            return ResponseEntity.ok(aluguelService.buscarAlugueisPorLivro(idLivro));
        }
        if (idAluno != null) {
            return ResponseEntity.ok(aluguelService.buscarAlugueisPorAluno(idAluno));
        }

        return ResponseEntity.ok(aluguelService.obterAlugueis().stream().toList());
    }

    @GetMapping("/ativos")
    @Operation(summary = "Listar aluguéis ativos", description = "Retorna uma representação em texto dos aluguéis que estão ativos no momento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    })
    public ResponseEntity<String> obterListaAlugueisAtivos() {
        return ResponseEntity.ok(aluguelService.obterListaAlugueisAtivos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter aluguel por ID", description = "Retorna os detalhes de um aluguel específico através do seu identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluguel encontrado"),
        @ApiResponse(responseCode = "404", description = "Aluguel não encontrado")
    })
    public ResponseEntity<AluguelResponseDTO> obterAluguelPorId(@PathVariable Long id) {
        return ResponseEntity.ok(aluguelService.obterAluguelPorId(id));
    }

    @GetMapping("/livro")
    @Operation(summary = "Obter aluguel por ID do Livro", description = "Busca o registro de aluguel ativo associado a um livro específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluguel encontrado"),
        @ApiResponse(responseCode = "404", description = "Nenhum aluguel ativo para este livro")
    })
    public ResponseEntity<AluguelResponseDTO> obterAluguelPorLivro(@RequestParam Long idLivro) {
        return ResponseEntity.ok(aluguelService.buscarAluguelPorLivro(idLivro));
    }

    @PostMapping("/alugar")
    @Operation(summary = "Registrar novo aluguel", description = "Cria um novo registro de aluguel de livro")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Aluguel realizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Livro já alugado ou dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<AluguelResponseDTO> alugar(@Valid @RequestBody AluguelRequestDTO aluguel) {
        return ResponseEntity.status(CREATED).body(aluguelService.alugar(aluguel));
    }

    @PutMapping("/{id}/renovar")
    @Operation(summary = "Renovar aluguel", description = "Renova um aluguel existente com uma nova data de fim")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluguel renovado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Data inválida ou aluguel inativo"),
        @ApiResponse(responseCode = "404", description = "Aluguel não encontrado")
    })
    public ResponseEntity<AluguelResponseDTO> renovar(@PathVariable Long id, @RequestParam LocalDate novaDataFim) {
        return ResponseEntity.ok(aluguelService.renovar(id, novaDataFim));
    }
}
