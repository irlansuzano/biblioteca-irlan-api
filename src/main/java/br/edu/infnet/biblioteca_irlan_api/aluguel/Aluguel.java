package br.edu.infnet.biblioteca_irlan_api.aluguel;

import br.edu.infnet.biblioteca_irlan_api.livro.Livro;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "aluguel")
public class Aluguel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inicio_aluguel")
    private LocalDate dataInicioAluguel;

    @Column(name = "data_fim_aluguel")
    private LocalDate dataFimAluguel;

    @Column(name = "is_ativo")
    private Boolean isAtivo;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @Column(name = "aluno_id")
    private Long alunoId;

    public Aluguel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return isAtivo;
    }

    public void setAtivo(Boolean ativo) {
        isAtivo = ativo;
    }

    public LocalDate getDataInicioAluguel() {
        return dataInicioAluguel;
    }

    public void setDataInicioAluguel(LocalDate dataInicioAluguel) {
        this.dataInicioAluguel = dataInicioAluguel;
    }

    public LocalDate getDataFimAluguel() {
        return dataFimAluguel;
    }

    public void setDataFimAluguel(LocalDate dataFimAluguel) {
        this.dataFimAluguel = dataFimAluguel;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aluguel aluguel = (Aluguel) o;
        return Objects.equals(id, aluguel.id)
                && Objects.equals(dataInicioAluguel, aluguel.dataInicioAluguel)
                && Objects.equals(dataFimAluguel, aluguel.dataFimAluguel)
                && Objects.equals(livro, aluguel.livro)
                && Objects.equals(alunoId, aluguel.alunoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataInicioAluguel, dataFimAluguel, livro, alunoId);
    }

    @Override
    public String toString() {
        return String.format("Aluguel do Livro %s feito pelo Aluno ID %s. Alugado de %s até %s", 
                livro != null ? livro.getTitulo() : "N/A", alunoId, dataInicioAluguel, dataFimAluguel);
    }
}
