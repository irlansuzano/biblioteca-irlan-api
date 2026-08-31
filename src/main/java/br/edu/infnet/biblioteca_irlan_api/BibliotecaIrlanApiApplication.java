package br.edu.infnet.biblioteca_irlan_api;

import br.edu.infnet.biblioteca_irlan_api.domain.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BibliotecaIrlanApiApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BibliotecaIrlanApiApplication.class, args);


        LocalDate dataNascimento = LocalDate.of(1997, 5, 20);
        Aluno irlan = new Aluno(1L, "irlan", "123456789", "irlan123@gmail.com",
                dataNascimento, "2021001", "A", null);
        Professor professor = new Professor(2L,"Professor Luis", "987654321", "professor_luis123@gmail.com",
                dataNascimento, "A123456", null);

        Curso curso = new Curso(1L, "Engenharia de Software", "Curso de Engenharia de Software",
                professor);

        curso.setCoordenador(professor);
        Turma turma = new Turma("1-2026-A", professor, true);
        curso.adicionarTurma(turma);
        turma.adicionarAluno(irlan);

        Livro senhorDosAneis = new Livro(1L, "Senhor dos Anéis", "J.R.R. Tolkien");
        Aluguel aluguel = new Aluguel();
        aluguel.setId(1L);
        aluguel.setDataInicioAluguel(LocalDate.now());
        aluguel.setDataFimAluguel(LocalDate.now().plusDays(7));
        aluguel.setLivro(senhorDosAneis);
        aluguel.setAluno(irlan);


        System.out.println(irlan);
        System.out.println(curso);
        System.out.println(turma);
        System.out.println(professor);
        System.out.println(senhorDosAneis);
        System.out.println(aluguel);
    }

}
