package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Professor;
import br.edu.infnet.biblioteca_irlan_api.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/professores")
@Tag(name = "Professor", description = "Endpoints para gestão de professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os professores", description = "Retorna uma lista de todos os professores cadastrados")
    public ResponseEntity<Collection<Professor>> obterProfessores() {
        return ResponseEntity.ok(professorService.obterProfessores());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter professor por ID", description = "Retorna os detalhes de um professor específico pelo seu ID")
    public ResponseEntity<Professor> obterProfessorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.obterProfessorPorId(id));
    }

    @GetMapping("/registro/{registroProfissional}")
    @Operation(summary = "Obter professor por registro profissional", description = "Busca um professor através do seu registro profissional")
    public ResponseEntity<Professor> obterProfessorPorRegistro(@PathVariable String registroProfissional) {
        return ResponseEntity.ok(professorService.obterProfessorPorRegistroProfissional(registroProfissional));
    }

    @PostMapping
    @Operation(summary = "Cadastrar professor", description = "Cria um novo registro de professor no sistema")
    public ResponseEntity<Professor> cadastrarProfessor(@Valid @RequestBody Professor professor) {
        professorService.cadastrarProfessor(professor);
        return ResponseEntity.status(CREATED).body(professor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar professor", description = "Atualiza os dados de um professor existente")
    public ResponseEntity<Professor> alterarProfessor(@PathVariable Long id, @Valid @RequestBody Professor professor) {
        professor.setId(id);
        professorService.alterarProfessor(professor);
        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover professor", description = "Exclui o registro de um professor do sistema")
    public ResponseEntity<Void> removerProfessor(@PathVariable Long id) {
        professorService.removerProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
