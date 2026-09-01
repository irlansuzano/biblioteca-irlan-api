package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Curso;
import br.edu.infnet.biblioteca_irlan_api.repository.CursoRepository;
import org.springframework.stereotype.Service;

@Service
public class CursoService extends BaseService<Curso, CursoRepository> {

    public CursoService(CursoRepository repository) {
        super(repository);
    }

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
        return repository.findByNome(nome)
                .orElseThrow(() -> new IllegalArgumentException("não existe curso com o nome " + nome));
    }

    public boolean isCursoExistente(Long idCurso){
        return repository.existsById(idCurso);
    }

}
