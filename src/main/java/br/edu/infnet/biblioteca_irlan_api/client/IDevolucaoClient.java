package br.edu.infnet.biblioteca_irlan_api.client;

import br.edu.infnet.biblioteca_irlan_api.devolucao.DevolucaoRequestDTO;
import br.edu.infnet.biblioteca_irlan_api.devolucao.DevolucaoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(name = "biblioteca-service", url = "${client.biblioteca.url:http://localhost:8082}", path = "/devolucoes")
public interface IDevolucaoClient {

    @PostMapping
    DevolucaoResponseDTO devolver(@RequestBody DevolucaoRequestDTO devolucao);

    @GetMapping
    Collection<DevolucaoResponseDTO> obterDevolucoes();

    @GetMapping("/{id}")
    DevolucaoResponseDTO obterDevolucaoPorId(@PathVariable("id") Long id);
}
