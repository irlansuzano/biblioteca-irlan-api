package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluno;
import br.edu.infnet.biblioteca_irlan_api.domain.Turma;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TurmaService extends BaseService<Turma> {

    public Collection<Turma> obterTurmas() {
        return this.obterLista();
    }

    public Turma obterTurmaPorId(Long id) {
        return this.obterPorId(id);
    }

    public void cadastrarTurma(Turma turma) {
        this.incluir(turma);
    }

    public boolean isTurmaAtiva(Long id) {
        Turma turma = this.obterPorId(id);
        return turma != null && turma.isAtivo();
    }

    public void desativarTurma(Long id) {
        Turma turma = this.obterPorId(id);
        if (turma != null) {
            turma.setAtivo(false);
            this.alterar(turma);
        }
    }

    public void incluirAlunos(Long idTurma, List<Aluno> alunos) {
        Turma turma = this.obterPorId(idTurma);
        if (turma != null && alunos != null) {
            for (Aluno aluno : alunos) {
                turma.adicionarAluno(aluno);
            }
            this.alterar(turma);
        }
    }

    public void incluirAluno(Long idTurma, Aluno aluno) {
        Turma turma = this.obterPorId(idTurma);
        if (turma != null && aluno != null) {
            turma.adicionarAluno(aluno);
            this.alterar(turma);
        }
    }

    public void alterarTurma(Turma turma) {
        this.alterar(turma);
    }

    public Turma obterTurmaPorIdentificador(String identificador) {
        return this.obterLista().stream()
                .filter(turma -> turma.getIdentificador().equals(identificador))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada com o identificador: " + identificador));
    }
}
