package br.edu.infnet.biblioteca_irlan_api;

import br.edu.infnet.biblioteca_irlan_api.domain.*;
import br.edu.infnet.biblioteca_irlan_api.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class ProjectRunner implements CommandLineRunner {

    private final AluguelService aluguelService;
    private final TurmaService turmaService;
    private final ProfessorService professorService;
    private final AlunoService alunoService;
    private final LivroService livroService;
    private final CursoService cursoService;

    public ProjectRunner(AluguelService aluguelService, TurmaService turmaService,
                         ProfessorService professorService, AlunoService alunoService,
                         LivroService livroService, CursoService cursoService) {
        this.aluguelService = aluguelService;
        this.turmaService = turmaService;
        this.professorService = professorService;
        this.alunoService = alunoService;
        this.livroService = livroService;
        this.cursoService = cursoService;
    }


    @Override
    public void run(String... args) throws Exception {
        instanciarMassasTeste();

        System.out.println(alunoService.obterAlunoPorMatricula("A2021001"));
        System.out.println(cursoService.buscarCursoPorNome("adm"));
        System.out.println(turmaService.obterTurmaPorIdentificador("1-2026-A"));
        System.out.println(professorService.obterProfessorPorRegistroProfissional("A123456"));
        System.out.println(livroService.buscarLivroPorTitulo("Senhor dos Anéis"));
        System.out.println(aluguelService.obterListaAlgueisAtivos());
    }

    private void instanciarMassasTeste() {
        Random random = new Random();
        LocalDate dataNascimento = LocalDate.of(1997, 5, 20);

        Aluno irlan = registrarAluno(null, "Irlan", dataNascimento);
        Aluno brenda = registrarAluno(null, "Brenda", dataNascimento);
        Aluno jose = registrarAluno(null, "Jose", dataNascimento);


        Professor professor = registrarProfessor(null, "professor luis", dataNascimento);
        Professor professor2 = registrarProfessor(null, "professor maria", dataNascimento);
        Professor professor3 = registrarProfessor(null, "professor joao", dataNascimento);

        Curso curso = registrarCurso(null, "adm",professor);
        Curso curso2 = registrarCurso(null, "engenharia", professor2);
        Curso curso3 = registrarCurso(null, "odonto", professor3);

        Turma turma = registrarTurma(null,"1-2026-A", professor);
        Turma turma2 = registrarTurma(null,"2-2026-A", professor2);
        Turma turma3 = registrarTurma(null,"3-2026-A", professor3);


        turma.adicionarAluno(irlan);
        turma2.adicionarAluno(brenda);
        turma3.adicionarAluno(jose);
        curso.adicionarTurma(turma);
        curso2.adicionarTurma(turma2);
        curso3.adicionarTurma(turma3);

        Livro senhorDosAneis = new Livro(null, "Senhor dos Anéis", "J.R.R. Tolkien");
        Livro cronicasGeloFogo = new Livro(null, "Cronicas de Gelo e Fogo", "George R. R. Martin");

        Aluguel aluguel = new Aluguel();
        preencherAluguel(null, aluguel, senhorDosAneis, irlan);


        Aluguel aluguel2 = new Aluguel();
        preencherAluguel(null, aluguel2, cronicasGeloFogo, irlan);

        alunoService.cadastrarAluno(irlan);
        alunoService.cadastrarAluno(brenda);
        alunoService.cadastrarAluno(jose);
        professorService.cadastrarProfessor(professor);
        professorService.cadastrarProfessor(professor2);
        professorService.cadastrarProfessor(professor3);
        cursoService.incluir(curso);
        cursoService.incluir(curso2);
        cursoService.incluir(curso3);
        turmaService.cadastrarTurma(turma);
        turmaService.cadastrarTurma(turma2);
        turmaService.cadastrarTurma(turma3);
        livroService.incluir(senhorDosAneis);
        livroService.incluir(cronicasGeloFogo);
        aluguelService.alugar(aluguel);
        aluguelService.alugar(aluguel2);

    }

    private static Turma registrarTurma(Long id, String identificador, Professor professor) {
        return new Turma(id, identificador, professor, true);
    }

    private static Curso registrarCurso(Long id, String nome, Professor professor) {
        return new Curso(id, nome, nome,
                professor);
    }

    private static Professor registrarProfessor(Long id, String nome, LocalDate dataNascimento) {
        return new Professor(id, nome, "987654321", "professor_luis123@gmail.com",
                dataNascimento, "A123456", null);
    }

    private static Aluno registrarAluno(Long id, String nome, LocalDate dataNascimento) {
        return new Aluno(id, nome, "123456789", "irlan123@gmail.com",
                dataNascimento, "A2021001", "A", null);
    }

    private static void preencherAluguel(Long id, Aluguel aluguel2, Livro cronicasGeloFogo, Aluno irlan) {
        aluguel2.setId(id);
        aluguel2.setDataInicioAluguel(LocalDate.now());
        aluguel2.setDataFimAluguel(LocalDate.now().plusDays(8));
        aluguel2.setLivro(cronicasGeloFogo);
        aluguel2.setAluno(irlan);
    }


}
