package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluguel;
import br.edu.infnet.biblioteca_irlan_api.service.AluguelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        try{
            return ResponseEntity.ok(aluguelService.obterPorId(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/livro")
    public ResponseEntity<Aluguel> obterAluguelPorLivro(Long idLivro){
        return ResponseEntity.ok(aluguelService.buscarAluguelPorLivro(idLivro));
    }

    @PostMapping
    public void alugar(Aluguel aluguel) {
        aluguelService.alugar(aluguel);
    }

    @PutMapping("{id}/alterar")
    public void alterarLivroAlugado(@PathVariable Long id, Long idLivroAtual, Aluguel aluguelAlterado) {
        aluguelService.alterarLivroAlugado(idLivroAtual, aluguelAlterado.getLivro());
    }

    @PutMapping("{id}/devolver")
    public void devolver(@PathVariable Long id) {
        aluguelService.devolver(id);
    }
}
