package br.edu.ifba.inf011.model.comercial;

import br.edu.ifba.inf011.model.playlist.PlaylistItem;

public interface ProdutoComercial extends PlaylistItem {

    Double getPreco();

    Integer getDuracao();

}
