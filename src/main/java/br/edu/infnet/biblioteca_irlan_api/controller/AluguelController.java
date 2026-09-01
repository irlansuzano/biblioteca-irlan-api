package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluguel;
import br.edu.infnet.biblioteca_irlan_api.service.AluguelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping
    public ResponseEntity<List<Aluguel>> obterAlugueis() {
        return ResponseEntity.ok(aluguelService.obterLista().stream().toList());
    }

    @GetMapping("/ativos")
    public ResponseEntity<String> obterListaAlgueisAtivos() {
        return ResponseEntity.ok(aluguelService.obterListaAlgueisAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluguel> obterAluguelPorId(@PathVariable Long id){
        return ResponseEntity.ok(aluguelService.obterPorId(id));
    }

    @GetMapping("/livro")
    public ResponseEntity<Aluguel> obterAluguelPorLivro(@RequestParam Long idLivro){
        return ResponseEntity.ok(aluguelService.buscarAluguelPorLivro(idLivro));
    }

    @PostMapping("/alugar")
    public ResponseEntity<Aluguel> alugar(@RequestBody Aluguel aluguel) {
        aluguelService.alugar(aluguel);
        return ResponseEntity.status(CREATED).body(aluguel);
    }

    @PutMapping("/{id}/alterar")
    public ResponseEntity<Aluguel> alterarLivroAlugado(@PathVariable Long id, @RequestParam Long idLivroAtual, @RequestBody Aluguel aluguelAlterado) {
        aluguelService.alterarLivroAlugado(idLivroAtual, aluguelAlterado.getLivro());
        return ResponseEntity.ok(aluguelAlterado);
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Void> devolver(@PathVariable Long id) {
        aluguelService.finalizarAluguel(id);
        return ResponseEntity.noContent().build();
    }
}
