package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.model.playlist.PlaylistVisitor;

public class Serie implements ProdutoComercial{

	protected String titulo;
	protected Integer temporada;
    protected List<Episodio> episodios;
    
    public Serie(String titulo, Integer temporada) {
    	this.titulo = titulo;
    	this.episodios = new ArrayList<Episodio>();
    };
    
    public String getTitulo() {
    	return this.titulo;
    }
        
    public Double getPreco() {
        double soma = this.episodios.stream().mapToDouble(Episodio::getPreco).sum();
        return soma * 0.9;
    }
        
    public Integer getDuracao() {
        return  this.episodios.stream().mapToInt(Episodio::getDuracao).sum();
    }    
    
    public Integer getTemporada() {
    	return this.temporada;
    }
    

	public List<Episodio> getEpisodios(){
        return this.episodios;
    } 

    @Override
    public void accept(PlaylistVisitor visitor){
        visitor.visit(this);
    }

}