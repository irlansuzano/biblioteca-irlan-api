package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluguel;
import br.edu.infnet.biblioteca_irlan_api.service.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "Listar todos os aluguéis", description = "Retorna uma lista de todos os registros de aluguéis")
    public ResponseEntity<List<Aluguel>> obterAlugueis() {
        return ResponseEntity.ok(aluguelService.obterLista().stream().toList());
    }

    @GetMapping("/ativos")
    @Operation(summary = "Listar aluguéis ativos", description = "Retorna uma representação em texto dos aluguéis que estão ativos no momento")
    public ResponseEntity<String> obterListaAlgueisAtivos() {
        return ResponseEntity.ok(aluguelService.obterListaAlgueisAtivos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter aluguel por ID", description = "Retorna os detalhes de um aluguel específico através do seu identificador")
    public ResponseEntity<Aluguel> obterAluguelPorId(@PathVariable Long id){
        return ResponseEntity.ok(aluguelService.obterPorId(id));
    }

    @GetMapping("/livro")
    @Operation(summary = "Obter aluguel por ID do Livro", description = "Busca o registro de aluguel associado a um livro específico")
    public ResponseEntity<Aluguel> obterAluguelPorLivro(@RequestParam Long idLivro){
        return ResponseEntity.ok(aluguelService.buscarAluguelPorLivro(idLivro));
    }

    @PostMapping("/alugar")
    @Operation(summary = "Registrar novo aluguel", description = "Cria um novo registro de aluguel de livro")
    public ResponseEntity<Aluguel> alugar(@RequestBody Aluguel aluguel) {
        aluguelService.alugar(aluguel);
        return ResponseEntity.status(CREATED).body(aluguel);
    }

    @PutMapping("/{id}/alterar")
    @Operation(summary = "Alterar livro de um aluguel", description = "Atualiza o livro associado a um aluguel existente")
    public ResponseEntity<Aluguel> alterarLivroAlugado(@PathVariable Long id, @RequestParam Long idLivroAtual, @RequestBody Aluguel aluguelAlterado) {
        aluguelService.alterarLivroAlugado(idLivroAtual, aluguelAlterado.getLivro());
        return ResponseEntity.ok(aluguelAlterado);
    }

    @PutMapping("/{id}/devolver")
    @Operation(summary = "Finalizar aluguel (Devolução)", description = "Marca um aluguel como inativo, indicando a devolução do livro")
    public ResponseEntity<Void> devolver(@PathVariable Long id) {
        aluguelService.finalizarAluguel(id);
        return ResponseEntity.noContent().build();
    }
}
