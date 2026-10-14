package br.edu.infnet.biblioteca_irlan_api.bibliotecario;

import br.edu.infnet.biblioteca_irlan_api.common.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliotecarioService implements UserDetailsService {

    private final BibliotecarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public BibliotecarioService(BibliotecarioRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bibliotecário não encontrado com o login: " + username));
    }

    public Bibliotecario cadastrar(Bibliotecario bibliotecario) {
        bibliotecario.setSenha(passwordEncoder.encode(bibliotecario.getSenha()));
        return repository.save(bibliotecario);
    }

    public String login(String login, String senha, AuthenticationManager authenticationManager) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, senha)
        );
        var user = repository.findByLogin(login).orElseThrow();
        return jwtService.generateToken(user);
    }

    public List<Bibliotecario> listarTodos() {
        return repository.findAll();
    }
}
