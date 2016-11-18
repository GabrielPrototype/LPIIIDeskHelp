package br.unoeste.fipp.lp3.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Erro
        implements Serializable {

    private final List<String> mensagens;

    public Erro() {
        mensagens = new ArrayList<>();
    }

    public void add(String mensagem) {
        mensagens.add(mensagem);
    }

    public List<String> getMensagens() {
        return mensagens;
    }

    public boolean isEmpty() {
        return mensagens.isEmpty();
    }
}
