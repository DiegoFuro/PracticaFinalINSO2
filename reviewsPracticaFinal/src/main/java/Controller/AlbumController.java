/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.AlbumFacadeLocal;
import Modelo.Album;
import Modelo.Review;
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
public class AlbumController implements Serializable {

    @Inject
    private ListAlbumsController listAlbumsController;

    private List<Review> reviews;
    private List<Album> albums;

    @EJB
    private AlbumFacadeLocal albumsEJB;

    private Album album;

    @PostConstruct
    public void init() {
        album = listAlbumsController.getAlbum();
        //albums = albumsEJB.findAlbumsByArtist(artist);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

   
    


}