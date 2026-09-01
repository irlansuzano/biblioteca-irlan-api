package br.edu.infnet.biblioteca_irlan_api;

import br.edu.infnet.biblioteca_irlan_api.domain.*;
import br.edu.infnet.biblioteca_irlan_api.service.AluguelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class BibliotecaIrlanApiApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BibliotecaIrlanApiApplication.class, args);

        LocalDate dataNascimento = LocalDate.of(1997, 5, 20);
        Aluno irlan = new Aluno(1L, "irlan", "123456789", "irlan123@gmail.com",
                dataNascimento, "2021001", "A", null);
        Professor professor = new Professor(2L, "Professor Luis", "987654321", "professor_luis123@gmail.com",
                dataNascimento, "A123456", null);

        Curso curso = new Curso(1L, "Engenharia de Software", "Curso de Engenharia de Software",
                professor);

        curso.setCoordenador(professor);
        Turma turma = new Turma("1-2026-A", professor, true);
        curso.adicionarTurma(turma);
        turma.adicionarAluno(irlan);

        Livro senhorDosAneis = new Livro(1L, "Senhor dos Anéis", "J.R.R. Tolkien");
        Aluguel aluguel = new Aluguel();
        preencherAluguel(1L, aluguel, senhorDosAneis, irlan);

        Livro cronicasGeloFogo = new Livro(2L, "Cronicas de Gelo e Fogo", "George R. R. Martin");
        Aluguel aluguel2 = new Aluguel();
        preencherAluguel(2L, aluguel2, cronicasGeloFogo, irlan);


        AluguelService aluguelService = new AluguelService();
        aluguelService.alugar(aluguel);
        aluguelService.alugar(aluguel2);

        System.out.println(irlan);
        System.out.println(curso);
        System.out.println(turma);
        System.out.println(professor);
        System.out.println(senhorDosAneis);
        System.out.println(aluguelService.obterListaAlgueisAtivos());
    }

    private static void preencherAluguel(Long id, Aluguel aluguel2, Livro cronicasGeloFogo, Aluno irlan) {
        aluguel2.setId(id);
        aluguel2.setDataInicioAluguel(LocalDate.now());
        aluguel2.setDataFimAluguel(LocalDate.now().plusDays(8));
        aluguel2.setLivro(cronicasGeloFogo);
        aluguel2.setAluno(irlan);
    }

}
