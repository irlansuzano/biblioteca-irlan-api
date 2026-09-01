package br.edu.infnet.biblioteca_irlan_api.controller;

import br.edu.infnet.biblioteca_irlan_api.domain.Curso;
import br.edu.infnet.biblioteca_irlan_api.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Curso", description = "Endpoints para gestão de cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os cursos", description = "Retorna uma lista de todos os cursos cadastrados")
    public ResponseEntity<Collection<Curso>> obterCursos() {
        return ResponseEntity.ok(cursoService.obterLista());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter curso por ID", description = "Retorna os detalhes de um curso específico pelo seu ID")
    public ResponseEntity<Curso> obterCursoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obterPorId(id));
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Obter curso por nome", description = "Busca um curso através do seu nome")
    public ResponseEntity<Curso> buscarCursoPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(cursoService.buscarCursoPorNome(nome));
    }

    @PostMapping
    @Operation(summary = "Incluir curso", description = "Cria um novo registro de curso no sistema")
    public ResponseEntity<Curso> incluirCurso(@RequestBody Curso curso) {
        cursoService.incluir(curso);
        return ResponseEntity.status(CREATED).body(curso);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar curso", description = "Atualiza os dados de um curso existente")
    public ResponseEntity<Curso> alterarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        cursoService.alterarCurso(id, curso);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir curso", description = "Exclui o registro de um curso do sistema")
    public ResponseEntity<Void> excluirCurso(@PathVariable Long id) {
        cursoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
