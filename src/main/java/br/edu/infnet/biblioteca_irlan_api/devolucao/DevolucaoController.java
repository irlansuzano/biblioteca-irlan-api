package br.edu.infnet.biblioteca_irlan_api.devolucao;

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
@RequestMapping("/devolucoes")
@Tag(name = "Devolução", description = "Endpoints para gestão de devoluções de livros")
public class DevolucaoController {

    private final DevolucaoService devolucaoService;

    public DevolucaoController(DevolucaoService devolucaoService) {
        this.devolucaoService = devolucaoService;
    }

    @PostMapping
    @Operation(summary = "Registrar nova devolução", description = "Cria um novo registro de devolução de livro e finaliza o aluguel correspondente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Devolução registrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Aluguel já finalizado ou dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Aluguel não encontrado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<DevolucaoResponseDTO> devolver(@Valid @RequestBody DevolucaoRequestDTO devolucao) {
        return ResponseEntity.status(CREATED).body(devolucaoService.devolver(devolucao));
    }

    @GetMapping
    @Operation(summary = "Listar todas as devoluções", description = "Retorna uma lista de todos os registros de devoluções")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Collection<DevolucaoResponseDTO>> obterDevolucoes() {
        return ResponseEntity.ok(devolucaoService.obterDevolucoes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter devolução por ID", description = "Retorna os detalhes de uma devolução específica através do seu identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devolução encontrada"),
        @ApiResponse(responseCode = "404", description = "Devolução não encontrada")
    })
    public ResponseEntity<DevolucaoResponseDTO> obterDevolucaoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(devolucaoService.obterDevolucaoPorId(id));
    }
}
