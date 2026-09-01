package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluno;
import br.edu.infnet.biblioteca_irlan_api.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<Collection<Aluno>> obterAlunos() {
        return ResponseEntity.ok(alunoService.obterAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> obterAlunoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.obterAlunoPorId(id));
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Aluno> obterAlunoPorMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(alunoService.obterAlunoPorMatricula(matricula));
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        alunoService.cadastrarAluno(aluno);
        return ResponseEntity.status(CREATED).body(aluno);
    }

    @PutMapping
    public ResponseEntity<Aluno> alterarAluno(@RequestBody Aluno aluno) {
        alunoService.alterarAluno(aluno);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAluno(@PathVariable Long id) {
        alunoService.removerAlunoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
