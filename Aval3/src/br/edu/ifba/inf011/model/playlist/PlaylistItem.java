package br.edu.ifba.inf011.model.playlist;

public interface PlaylistItem {
	void accept(PlaylistVisitor visitor);
}

