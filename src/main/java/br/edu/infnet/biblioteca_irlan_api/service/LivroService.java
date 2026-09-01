package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Livro;

public class LivroService extends BaseService<Livro> {

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
        return this.obterLista().stream()
                .filter(livro -> livro.getTitulo().equals(titulo))
                .findFirst().orElse(null);
    }


}
