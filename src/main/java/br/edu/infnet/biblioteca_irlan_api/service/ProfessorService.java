package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Professor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProfessorService extends BaseService<Professor> {

    public Professor obterProfessorPorId(Long id) {
        return this.obterPorId(id);
    }

    public Collection<Professor> obterProfessores() {
        return this.obterLista();
    }

    public void cadastrarProfessor(Professor professor) {
        if(isProfessorExistente(professor.getId())) {
            throw new IllegalArgumentException("Cadastro do Professor já existe.");
        }
        this.incluir(professor);
    }

    public void removerProfessor(Long id) {
        if(!isProfessorExistente(id)) {
            throw new IllegalArgumentException("Cadastro do Professor não encontrado.");
        }
        this.excluir(id);
    }

    public void alterarProfessor(Professor professor) {
        if(!isProfessorExistente(professor.getId())) {
            throw new IllegalArgumentException("Cadastro do Professor não encontrado.");
        }
        this.alterar(professor);
    }

    public Professor obterProfessorPorRegistroProfissional(String registroProfissional) {
        if (registroProfissional == null || registroProfissional.isEmpty()) {
            throw new IllegalArgumentException("Registro profissional inválido.");
        }
        Collection<Professor> professores = this.obterLista();
        return professores.stream().filter(professor ->
                        professor.getRegistroProfissional().equals(registroProfissional)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado Professor com o registro profissional " + registroProfissional));
    }

    public boolean isProfessorExistente(Long idProfessor){
        return this.obterLista().stream().anyMatch(professor -> professor.getId().equals(idProfessor));
    }
}
