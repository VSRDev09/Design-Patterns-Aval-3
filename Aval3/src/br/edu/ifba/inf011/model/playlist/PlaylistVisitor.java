package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;

public interface PlaylistVisitor {

    void visit(Filme filme);

    void visit(Serie serie);

    void visit(Episodio episodio);

    void visit(MP3 mp3);

    void visit(Video video);

    void visit(Pacote pacote);

}
