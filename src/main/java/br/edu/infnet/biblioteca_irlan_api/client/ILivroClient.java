package br.edu.infnet.biblioteca_irlan_api.client;

import br.edu.infnet.biblioteca_irlan_api.livro.LivroRequestDTO;
import br.edu.infnet.biblioteca_irlan_api.livro.LivroResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(name = "biblioteca-service", url = "${client.biblioteca.url:http://localhost:8082}", path = "/livros")
public interface ILivroClient {

    @GetMapping
    Collection<LivroResponseDTO> obterLivros();

    @GetMapping("/{id}")
    LivroResponseDTO obterLivroPorId(@PathVariable("id") Long id);

    @GetMapping("/titulo/{titulo}")
    LivroResponseDTO buscarLivroPorTitulo(@PathVariable("titulo") String titulo);

    @PostMapping
    LivroResponseDTO incluirLivro(@RequestBody LivroRequestDTO livro);

    @PutMapping("/{id}")
    LivroResponseDTO alterarLivro(@PathVariable("id") Long id, @RequestBody LivroRequestDTO livro);

    @DeleteMapping("/{id}")
    void excluirLivro(@PathVariable("id") Long id);
}
