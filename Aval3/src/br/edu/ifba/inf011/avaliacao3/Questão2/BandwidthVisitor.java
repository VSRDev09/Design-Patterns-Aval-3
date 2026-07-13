package br.edu.ifba.inf011.avaliacao3.Questão2;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.PlaylistItem;
import br.edu.ifba.inf011.model.playlist.PlaylistVisitor;
import br.edu.ifba.inf011.model.playlist.Video;

public class BandwidthVisitor implements PlaylistVisitor {

    private static final Double BAND_PER_SECOND = 1.5;
    private Double bandwidth = 0.0;

    @Override
    public void visit(Filme filme) {
        bandwidth += filme.getDuracao() * BAND_PER_SECOND;
    }

    @Override
    public void visit(Episodio episodio) {
        bandwidth += episodio.getDuracao() * BAND_PER_SECOND;
    }

    @Override
    public void visit(Serie serie) {
        for (Episodio episodio : serie.getEpisodios()) {
            episodio.accept(this);
        }
    }

    @Override
    public void visit(Pacote pacote) {
        for (PlaylistItem item : pacote.getItens()) {
            item.accept(this);
        }
    }

    @Override
    public void visit(MP3 mp3) {
        bandwidth += mp3.getTamanhoMegaBytes();
    }

    @Override
    public void visit(Video video) {
        bandwidth += video.getTamanhoMegaBytes();
    }

    public Double getBandwidth() {
        return bandwidth;
    }
}