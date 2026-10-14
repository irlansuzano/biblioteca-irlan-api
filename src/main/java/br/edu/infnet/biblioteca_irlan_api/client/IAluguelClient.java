package br.edu.infnet.biblioteca_irlan_api.client;

import br.edu.infnet.biblioteca_irlan_api.aluguel.AluguelRequestDTO;
import br.edu.infnet.biblioteca_irlan_api.aluguel.AluguelResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "aluguel-service", url = "${client.biblioteca.url:http://localhost:8082}", path = "/alugueis")
public interface IAluguelClient {

    @GetMapping
    List<AluguelResponseDTO> obterAlugueis(
            @RequestParam(value = "idLivro", required = false) Long idLivro,
            @RequestParam(value = "idAluno", required = false) Long idAluno);

    @GetMapping("/ativos")
    String obterListaAlugueisAtivos();

    @GetMapping("/{id}")
    AluguelResponseDTO obterAluguelPorId(@PathVariable("id") Long id);

    @GetMapping("/livro")
    AluguelResponseDTO obterAluguelPorLivro(@RequestParam("idLivro") Long idLivro);

    @PostMapping("/alugar")
    AluguelResponseDTO alugar(@RequestBody AluguelRequestDTO aluguel);

    @PutMapping("/{id}/renovar")
    AluguelResponseDTO renovar(@PathVariable("id") Long id, @RequestParam("novaDataFim") LocalDate novaDataFim);
}
