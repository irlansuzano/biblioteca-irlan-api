package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Professor;
import br.edu.infnet.biblioteca_irlan_api.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProfessorService extends BaseService<Professor, ProfessorRepository> {

    public ProfessorService(ProfessorRepository repository) {
        super(repository);
    }

    public Professor obterProfessorPorId(Long id) {
        return this.obterPorId(id);
    }

    public Collection<Professor> obterProfessores() {
        return this.obterLista();
    }

    public void cadastrarProfessor(Professor professor) {
        if(professor.getId() != null && isProfessorExistente(professor.getId())) {
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
        return repository.findByRegistroProfissional(registroProfissional)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado Professor com o registro profissional " + registroProfissional));
    }

    public boolean isProfessorExistente(Long idProfessor){
        return repository.existsById(idProfessor);
    }
}
