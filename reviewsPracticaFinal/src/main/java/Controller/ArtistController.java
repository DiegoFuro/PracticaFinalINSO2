/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.ArtistFacadeLocal;
import Modelo.Album;
import Modelo.Artist;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */


@Named
@ViewScoped
public class ArtistController implements Serializable {

    @Inject
    private ListArtistsController listArtistsController;

    private List<Artist> reviews;
    private List<Album> albums;

    @EJB
    private ArtistFacadeLocal artistsEJB;

    private Artist artist;

    @PostConstruct
    public void init() {
        artist = listArtistsController.getArtist();
        albums = artistsEJB.findAlbumsByArtist(artist);
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
    
    public List<Artist> getReviews() {
        return reviews;
    }

    public void setReviews(List<Artist> reviews) {
        this.reviews = reviews;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
