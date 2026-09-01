package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluno;
import br.edu.infnet.biblioteca_irlan_api.domain.Turma;
import br.edu.infnet.biblioteca_irlan_api.service.TurmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public ResponseEntity<Collection<Turma>> obterTurmas() {
        return ResponseEntity.ok(turmaService.obterTurmas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> obterTurmaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turmaService.obterTurmaPorId(id));
    }

    @GetMapping("/identificador/{identificador}")
    public ResponseEntity<Turma> obterTurmaPorIdentificador(@PathVariable String identificador) {
        return ResponseEntity.ok(turmaService.obterTurmaPorIdentificador(identificador));
    }

    @GetMapping("/{id}/ativa")
    public ResponseEntity<Boolean> isTurmaAtiva(@PathVariable Long id) {
        return ResponseEntity.ok(turmaService.isTurmaAtiva(id));
    }

    @PostMapping
    public ResponseEntity<Turma> cadastrarTurma(@RequestBody Turma turma) {
        turmaService.cadastrarTurma(turma);
        return ResponseEntity.status(CREATED).body(turma);
    }

    @PutMapping
    public ResponseEntity<Turma> alterarTurma(@RequestBody Turma turma) {
        turmaService.alterarTurma(turma);
        return ResponseEntity.ok(turma);
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> desativarTurma(@PathVariable Long id) {
        turmaService.desativarTurma(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/alunos")
    public ResponseEntity<Void> incluirAlunos(@PathVariable Long id, @RequestBody List<Aluno> alunos) {
        turmaService.incluirAlunos(id, alunos);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/aluno")
    public ResponseEntity<Void> incluirAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        turmaService.incluirAluno(id, aluno);
        return ResponseEntity.noContent().build();
    }
}
