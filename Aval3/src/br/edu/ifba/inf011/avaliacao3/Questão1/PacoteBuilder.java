package br.edu.ifba.inf011.avaliacao3.Questão1;

import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.ProdutoComercial;

public class PacoteBuilder {

    private Pacote pacote;

    public PacoteBuilder(String titulo) {
        pacote = new Pacote(titulo);
    }

    public PacoteBuilder addItem(ProdutoComercial item) {
        pacote.addItem(item);
        return this;
    }

    public Pacote build() {
        return pacote;
    }
}