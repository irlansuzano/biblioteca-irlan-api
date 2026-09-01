package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluno;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AlunoService extends BaseService<Aluno> {

    public Aluno obterAlunoPorId(Long id) {
        return this.obterPorId(id);
    }

    public Collection<Aluno> obterAlunos() {
        return this.obterLista();
    }

    public void cadastrarAluno(Aluno aluno) {
        if(isAlunoExistente(aluno.getId())){
            throw new IllegalArgumentException("Aluno já cadastrado.");
        }
        this.incluir(aluno);
    }

    public void removerAlunoPorId(Long id) {
        if(!isAlunoExistente(id)) {
            throw new IllegalArgumentException("Cadastro do Aluno não encontrado.");
        }
        this.excluir(id);
    }

    public void alterarAluno(Aluno aluno) {
        if(!isAlunoExistente(aluno.getId())) {
            throw new IllegalArgumentException("Cadastro do Aluno não encontrado.");
        }
        this.alterar(aluno);
    }

    public Aluno obterAlunoPorMatricula(String matricula) {
        return this.obterLista().stream()
                .filter(aluno -> aluno.getMatricula().equals(matricula))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com a matrícula: " + matricula));
    }

    private boolean isAlunoExistente(Long idAluno) {
        if (!obterLista().contains(idAluno)) {
            return false;
        }
        return true;
    }


}
