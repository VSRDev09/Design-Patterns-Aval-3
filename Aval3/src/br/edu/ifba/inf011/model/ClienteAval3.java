package br.edu.ifba.inf011.model;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.CinemaTimelineBuilder;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.TimelineBuilder;
import br.edu.ifba.inf011.avaliacao3.Questão1.PacoteBuilder;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Playlist;
import br.edu.ifba.inf011.model.playlist.Video;

public class ClienteAval3 {

    public void run() {

        TimelineBuilder builder = new CinemaTimelineBuilder();
        Timeline timeline = builder
                .reset()
                .addClassAdapterVideo("MainShot_4K.mov")
                .build();

        Filme matrix = new Filme("Matrix", 20.0, timeline);
        Filme reloaded = new Filme("Matrix Reloaded", 25.0, timeline);
        Filme revolutions = new Filme("Matrix Revolutions", 15.0, timeline);
        Filme bladeRunner = new Filme("Blade Runner", 18.0, timeline);

        Pacote trilogiaMatrix = new PacoteBuilder("Trilogia Matrix")
                .addItem(matrix)
                .addItem(reloaded)
                .addItem(revolutions)
                .build();

        Pacote colecaoSciFi = new PacoteBuilder("Coleção Sci-Fi")
                .addItem(trilogiaMatrix)
                .addItem(bladeRunner)
                .build();

        Playlist playlist = new Playlist();
        playlist.addItem(colecaoSciFi);
        playlist.addItem(new MP3("Son Of A Gun", 1000));
        playlist.addItem(new Video("Trailer Pessoal", 250, "youtube.com/trailer"));

        System.out.println("========== QUESTÃO 1 ==========");
        System.out.println("Preço total: " + colecaoSciFi.getPreco());
        System.out.println("Duração total: " + colecaoSciFi.getDuracao());

        System.out.println("\n========== VISITOR - LARGURA DE BANDA ==========");
        System.out.println(playlist.getBandaTotal());

        System.out.println("\n========== VISITOR - XML ==========");
        System.out.println(playlist.toXML());

        System.out.println("\n========== VISITOR - RELATÓRIO ==========");
        System.out.println(playlist.getPlaylistNameReport());
    }

    public static void main(String[] args) {
        new ClienteAval3().run();
    }

}