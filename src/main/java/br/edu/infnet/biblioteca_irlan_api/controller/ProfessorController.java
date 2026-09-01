package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Professor;
import br.edu.infnet.biblioteca_irlan_api.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<Collection<Professor>> obterProfessores() {
        return ResponseEntity.ok(professorService.obterProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> obterProfessorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.obterProfessorPorId(id));
    }

    @GetMapping("/registro/{registroProfissional}")
    public ResponseEntity<Professor> obterProfessorPorRegistro(@PathVariable String registroProfissional) {
        return ResponseEntity.ok(professorService.obterProfessorPorRegistroProfissional(registroProfissional));
    }

    @PostMapping
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody Professor professor) {
        professorService.cadastrarProfessor(professor);
        return ResponseEntity.status(CREATED).body(professor);
    }

    @PutMapping
    public ResponseEntity<Professor> alterarProfessor(@RequestBody Professor professor) {
        professorService.alterarProfessor(professor);
        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProfessor(@PathVariable Long id) {
        professorService.removerProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
