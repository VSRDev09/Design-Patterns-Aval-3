package br.edu.ifba.inf011.model.playlist;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.Questão2.BandwidthVisitor;
import br.edu.ifba.inf011.avaliacao3.Questão2.PlaylistNameReportVisitor;
import br.edu.ifba.inf011.avaliacao3.Questão2.XMLVisitor;

public class Playlist {

    private List<PlaylistItem> items;

    public Playlist() {
        this.items = new ArrayList<>();
    }

    public void addItem(PlaylistItem item) {
        this.items.add(item);
    }

    public String toXML() {
        XMLVisitor visitor = new XMLVisitor();

        for (PlaylistItem item : items) {
            item.accept(visitor);
        }

        return visitor.getXML();
    }

    public Double getBandaTotal() {
        BandwidthVisitor visitor = new BandwidthVisitor();

        for (PlaylistItem item : items) {
            item.accept(visitor);
        }

        return visitor.getBandwidth();
    }

    public String getPlaylistNameReport() {
    
    PlaylistNameReportVisitor visitor = new PlaylistNameReportVisitor();
    
    for (PlaylistItem item : this.items) { 
        item.accept(visitor);
    }
    
    return visitor.getReport();
}


}