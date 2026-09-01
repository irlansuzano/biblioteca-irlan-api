package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Livro;
import br.edu.infnet.biblioteca_irlan_api.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class LivroService extends BaseService<Livro, LivroRepository> {

    public LivroService(LivroRepository repository) {
        super(repository);
    }

    public void alterarLivro(Long idLivro, Livro livroAlterado){
        Livro livroExistente = this.obterPorId(idLivro);

        if (livroExistente == null) {
            this.incluir(livroAlterado);
            return;
        }

        livroExistente.setTitulo(livroAlterado.getTitulo());
        livroExistente.setAutor(livroAlterado.getAutor());
        this.alterar(livroExistente);
    }

    public Livro buscarLivroPorTitulo(String titulo){
        return repository.findByTitulo(titulo)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado com o título: " + titulo));
    }


}
