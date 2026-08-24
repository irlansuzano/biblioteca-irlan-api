package br.edu.infnet.biblioteca_irlan_api;

import br.edu.infnet.biblioteca_irlan_api.domain.Aluno;
import br.edu.infnet.biblioteca_irlan_api.domain.Curso;
import br.edu.infnet.biblioteca_irlan_api.domain.Professor;
import br.edu.infnet.biblioteca_irlan_api.domain.Turma;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

public class Loader implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        LocalDate dataNascimento = LocalDate.of(1997, 5, 20);
        Aluno irlan = new Aluno("irlan", "123456789", "irlan123@gmail.com",
                dataNascimento, "2021001", "A", null);
        Professor professor = new Professor("Professor Luis", "987654321", "professor_luis123@gmail.com",
                dataNascimento, "A123456", null);

        Curso curso = new Curso(1L, "Engenharia de Software", "Curso de Engenharia de Software",
                professor);

        Turma turma = new Turma("1-2026-A", professor, true);
        curso.adicionarTurma(turma);
        turma.adicionarAluno(irlan);

        System.out.println(irlan);
        System.out.println(curso);
        System.out.println(turma);
        System.out.println(professor);
    }
}
