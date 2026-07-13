package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.inf011.model.playlist.PlaylistItem;
import br.edu.ifba.inf011.model.playlist.PlaylistVisitor;

public class Pacote implements PlaylistItem {

	protected String titulo;
	protected List<PlaylistItem> itens;

	public Pacote(String titulo) {
		this.titulo = titulo;
		this.itens = new ArrayList<PlaylistItem>();
	};

	public Pacote(String titulo, List<PlaylistItem> itens) {
		this.titulo = titulo;
		this.itens = itens;
	};

	public String getTitulo() {
		return this.titulo;
	}

	public void addItem(PlaylistItem item) {
		itens.add(item);
	}

	public List<PlaylistItem> getItens() {
		return itens;
	}

	public Double getPreco() {
		return itens.stream()
				.filter(item -> item instanceof ProdutoComercial)
				.map(item -> (ProdutoComercial) item)
				.mapToDouble(ProdutoComercial::getPreco)
				.sum() * 0.9;
	}

	public Double getDuracao() {
		return itens.stream()
				.filter(item -> item instanceof ProdutoComercial)
				.map(item -> (ProdutoComercial) item)
				.mapToDouble(ProdutoComercial::getDuracao)
				.sum();
	}

	@Override
	public void accept(PlaylistVisitor visitor) {
		visitor.visit(this);
	}

}