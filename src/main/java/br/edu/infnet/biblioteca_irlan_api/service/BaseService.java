package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Identificavel;
import br.edu.infnet.biblioteca_irlan_api.exception.RecursoNaoEncontradoException;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseService<T extends Identificavel> {

    private final Map<Long, T> dados = new LinkedHashMap<>();

    public void incluir(T objeto) {
        dados.put(objeto.getId(), objeto);
    }

    public void alterar(T objeto) {
        incluir(objeto);
    }

    public void excluir(Long id) {
        dados.remove(id);
    }

    public T buscarPorId(Long id) {
        validarExistencia(id);
        return dados.get(id);
    }

    public Collection<T> obterLista() {
        return dados.values();
    }

    public T obterPorId(Long id) {
        validarExistencia(id);
        return dados.get(id);
    }

    private void validarExistencia(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        if (!dados.containsKey(id)) {
            throw new RecursoNaoEncontradoException("Recurso não encontrado com o ID " + id);
        }
    }
}
