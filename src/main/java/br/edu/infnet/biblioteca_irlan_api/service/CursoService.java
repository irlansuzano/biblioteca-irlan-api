package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Curso;

public class CursoService extends BaseService<Curso> {

    public void alterarCurso(Long idCurso, Curso cursoAlterado){
        Curso cursoExistente = this.obterPorId(idCurso);

        if (cursoExistente == null) {
            this.incluir(cursoAlterado);
            return;
        }

        cursoExistente.setNome(cursoAlterado.getNome());
        cursoExistente.setDescricao(cursoAlterado.getDescricao());
        this.alterar(cursoExistente);
    }

    public Curso buscarCursoPorNome(String nome){
        return this.obterLista().stream()
                .filter(curso -> curso.getNome().equals(nome))
                .findFirst().orElse(null);
    }

    public boolean isCursoExistente(Long idCurso){
        return this.obterLista().stream().anyMatch(curso -> curso.getId().equals(idCurso));
    }

}
