package br.edu.ifba.inf011.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.CinemaTimelineBuilder;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.TimelineBuilder;
import br.edu.ifba.inf011.avaliacao3.Questão1.PacoteBuilder;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Playlist;

public class ClienteAval3 {

	public void run() {
		
		TimelineBuilder builder = new CinemaTimelineBuilder();
	    Timeline cinemaTimeline = builder.reset().addClassAdapterVideo("MainShot_4K.mov").build();
	    
	    List<Filme> filmes = new ArrayList<Filme>();
	    filmes.add(new Filme("Matrix", 20.0, cinemaTimeline));
	    filmes.add(new Filme("Matrix Reloaded", 25.0, cinemaTimeline));
	    filmes.add(new Filme("Matrix Revolutions", 15.0, cinemaTimeline));

		
	    Pacote pacote = new PacoteBuilder("Trilogia Matrix")
						.addItem(filmes.get(0))
						.addItem(filmes.get(1))
						.addItem(filmes.get(2))
						.build();

        System.out.println("Preço da Super Coleção: " + pacote.getPreco());
        System.out.println("Duração da Super Coleção: " + pacote.getDuracao());


    // Questão 2
        
        Playlist playlist = new Playlist();
        
        playlist.addItem(pacote);
        playlist.addItem(new MP3("Son Of A Gun", 1000));
        
        double largura =  playlist.getBandaTotal();
        System.out.println("Consumo de Largura de Banda: " + largura);
        
        String  xml = playlist.toXML();
        System.out.println(xml);
    }		

	public static void main(String[] args) {
		new ClienteAval3().run();
	}

	
}
