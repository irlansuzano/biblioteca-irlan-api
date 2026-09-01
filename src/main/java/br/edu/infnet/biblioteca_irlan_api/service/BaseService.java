package br.edu.infnet.biblioteca_irlan_api.service;

import br.edu.infnet.biblioteca_irlan_api.domain.Identificavel;

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
        return dados.get(id);
    }

    public Collection<T> obterLista() {
        return dados.values();
    }

    public T obterPorId(Long id) {
        return dados.get(id);
    }
}
