package br.edu.infnet.biblioteca_irlan_api.bibliotecario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para registro e login de bibliotecários")
public class BibliotecarioController {

    private final BibliotecarioService service;
    private final BibliotecarioMapper mapper;
    private final AuthenticationManager authenticationManager;

    public BibliotecarioController(BibliotecarioService service, BibliotecarioMapper mapper, AuthenticationManager authenticationManager) {
        this.service = service;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registrar")
    @Operation(summary = "Registrar novo bibliotecário", description = "Cria um novo registro de bibliotecário no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bibliotecário registrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<BibliotecarioResponseDTO> registrar(@RequestBody BibliotecarioRequestDTO dto) {
        Bibliotecario bibliotecario = mapper.toEntity(dto);
        Bibliotecario salvo = service.cadastrar(bibliotecario);
        return ResponseEntity.ok(mapper.toResponseDto(salvo));
    }

    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Autentica um bibliotecário e retorna um token JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso, token retornado"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto) {
        String token = service.login(dto.login(), dto.senha(), authenticationManager);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/bibliotecarios")
    @Operation(summary = "Listar bibliotecários", description = "Retorna uma lista de todos os bibliotecários (Requer autenticação)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<BibliotecarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList()));
    }
}
