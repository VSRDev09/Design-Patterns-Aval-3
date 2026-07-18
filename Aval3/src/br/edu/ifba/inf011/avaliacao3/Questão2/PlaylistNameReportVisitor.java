package br.edu.ifba.inf011.avaliacao3.Questão2;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.PlaylistItem;
import br.edu.ifba.inf011.model.playlist.PlaylistVisitor;
import br.edu.ifba.inf011.model.playlist.Video;

public class PlaylistNameReportVisitor implements PlaylistVisitor {

    private final StringBuilder report = new StringBuilder();

    @Override
    public void visit(Filme filme) {
        report.append("Filme: ")
              .append(filme.getTitulo())
              .append("\n");
    }

    @Override
    public void visit(Episodio episodio) {
        report.append("Episodio: ")
              .append(episodio.getTitulo())
              .append("\n");
    }

    @Override
    public void visit(Serie serie) {
        report.append("Serie: ")
              .append(serie.getTitulo())
              .append("\n");

        for (Episodio episodio : serie.getEpisodios()) {
            episodio.accept(this);
        }
    }

    @Override
    public void visit(Pacote pacote) {
        report.append("Pacote: ")
              .append(pacote.getTitulo())
              .append("\n");

        for (PlaylistItem item : pacote.getItens()) {
            item.accept(this);
        }
    }

    @Override
    public void visit(MP3 mp3) {
        report.append("MP3: ")
              .append(mp3.getNome())
              .append("\n");
    }

    @Override
    public void visit(Video video) {
        report.append("Video: ")
              .append(video.getNome())
              .append("\n");
    }

    public String getReport() {
        return report.toString();
    }
}