package br.edu.ifba.inf011.avaliacao3.Questão2;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.PlaylistItem;
import br.edu.ifba.inf011.model.playlist.PlaylistVisitor;
import br.edu.ifba.inf011.model.playlist.Video;

public class XMLVisitor implements PlaylistVisitor {

    private final StringBuilder xml = new StringBuilder();

    public XMLVisitor() {
        xml.append("<playlist>\n");
    }

    @Override
    public void visit(Filme filme) {
        xml.append("\t<filme titulo=\"")
                .append(filme.getTitulo())
                .append("\"/>\n");
    }

    @Override
    public void visit(Episodio episodio) {
        xml.append("\t<episodio titulo=\"")
                .append(episodio.getTitulo())
                .append("\" numero=\"")
                .append(episodio.getNumero())
                .append("\"/>\n");
    }

    @Override
    public void visit(MP3 mp3) {
        xml.append("\t<mp3 nome=\"")
                .append(mp3.getNome())
                .append("\"/>\n");
    }

    @Override
    public void visit(Video video) {
        xml.append("\t<video nome=\"")
                .append(video.getNome())
                .append("\"/>\n");
    }

    @Override
    public void visit(Pacote pacote) {

        xml.append("<pacote titulo=\"")
                .append(pacote.getTitulo())
                .append("\">\n");

        for (PlaylistItem item : pacote.getItens()) {
            item.accept(this);
        }

        xml.append("</pacote>\n");
    }

    @Override
    public void visit(Serie serie) {

        xml.append("\t<serie titulo=\"")
                .append(serie.getTitulo())
                .append("\" temporada=\"")
                .append(serie.getTemporada())
                .append("\">\n");

        for (Episodio episodio : serie.getEpisodios()) {
            episodio.accept(this);
        }

        xml.append("\t</serie>\n");
    }

    public String getXML() {
        return xml.toString() + "</playlist>";
    }

}