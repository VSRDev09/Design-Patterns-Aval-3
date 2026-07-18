package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.model.playlist.PlaylistVisitor;

public class Pacote implements ProdutoComercial {

	protected String titulo;
	protected List<ProdutoComercial> itens;

	public Pacote(String titulo) {
		this.titulo = titulo;
		this.itens = new ArrayList<ProdutoComercial>();
	};

	public Pacote(String titulo, List<ProdutoComercial> itens) {
		this.titulo = titulo;
		this.itens = itens;
	};

	public String getTitulo() {
		return this.titulo;
	}

	public void addItem(ProdutoComercial item) {
		itens.add(item);
	}

	public List<ProdutoComercial> getItens() {
		return itens;
	}

	public Double getPreco() {
		return itens.stream()
				.mapToDouble(ProdutoComercial::getPreco)
				.sum() * 0.9;
	}

	public Integer getDuracao() {
		return itens.stream()
				.mapToInt(ProdutoComercial::getDuracao)
				.sum();
	}

	@Override
	public void accept(PlaylistVisitor visitor) {
		visitor.visit(this);
	}

}