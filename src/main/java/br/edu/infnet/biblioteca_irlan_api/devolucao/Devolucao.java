package br.edu.infnet.biblioteca_irlan_api.devolucao;

import br.edu.infnet.biblioteca_irlan_api.aluguel.Aluguel;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "devolucao")
public class Devolucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @OneToOne
    @JoinColumn(name = "id_aluguel")
    private Aluguel aluguel;

    @Column(name = "aluno_id")
    private Long alunoId;

    public Devolucao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
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
        Devolucao devolucao = (Devolucao) o;
        return Objects.equals(id, devolucao.id) 
                && Objects.equals(dataDevolucao, devolucao.dataDevolucao) 
                && Objects.equals(aluguel, devolucao.aluguel)
                && Objects.equals(alunoId, devolucao.alunoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataDevolucao, aluguel, alunoId);
    }
}
