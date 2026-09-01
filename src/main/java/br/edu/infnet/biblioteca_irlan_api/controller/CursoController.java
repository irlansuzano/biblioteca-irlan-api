package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Curso;
import br.edu.infnet.biblioteca_irlan_api.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<Collection<Curso>> obterCursos() {
        return ResponseEntity.ok(cursoService.obterLista());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obterCursoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obterPorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Curso> buscarCursoPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(cursoService.buscarCursoPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Curso> incluirCurso(@RequestBody Curso curso) {
        cursoService.incluir(curso);
        return ResponseEntity.status(CREATED).body(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> alterarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        cursoService.alterarCurso(id, curso);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurso(@PathVariable Long id) {
        cursoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
