/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.AlbumFacadeLocal;
import EJB.ArtistFacadeLocal;
import EJB.ReviewFacadeLocal;
import Modelo.Album;
import Modelo.Artist;
import Modelo.Review;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private List<Review> reviews;
    private List<Album> albums;
    private Review newReview;

    @EJB
    private ArtistFacadeLocal artistsEJB;

    @EJB
    private ReviewFacadeLocal reviewsEJB;

    @EJB
    private AlbumFacadeLocal albumsEJB;

    private Artist artist;
    private String reviewTitle;
    private String reviewBody;
    private int reviewRating;

    private Album newAlbum;

    private String albumTitle;
    private String albumDescription;
    private int albumNumberOfSongs;
    private Date albumReleaseDate;
    private int albumRating;
    private String albumImageURL;
    private String albumGenre;

    @PostConstruct
    public void init() {
        artist = listArtistsController.getArtist();
        albums = artistsEJB.findAlbumsByArtist(artist);
        reviews = reviewsEJB.findReviewsArtist(artist);
    }

    public void writeReview() {
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        newReview = new Review();

        try {
            newReview.setIdUser(user);
            newReview.setArtist(artist);
            newReview.setTitle(reviewTitle);
            newReview.setBody(reviewBody);
            newReview.setRating(reviewRating);
            reviewsEJB.create(newReview);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se añadió correctamente", "Se añadio"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void addAlbum() {
        newAlbum = new Album();

        try {
            newAlbum.setTitle(albumTitle);
            newAlbum.setDescription(albumDescription);
            newAlbum.setRating(albumRating);
            newAlbum.setReleaseDate(albumReleaseDate);
            newAlbum.setImageURL(albumImageURL);
            newAlbum.setGenre(albumGenre);
            newAlbum.setIdArtist(artist);
            newAlbum.setNumberSongs(albumNumberOfSongs);
            albumsEJB.create(newAlbum);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se añadió correctamente", "Se añadio"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        albumTitle = "";
        albumDescription = "";
        albumRating = 0;
        albumImageURL = "";
        albumGenre = "";
        albumNumberOfSongs = 0;
    }

    public void deleteReview(Review review) {
        try {
            reviewsEJB.remove(review);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó correctamente", "Se eliminó"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteArtist() {
        try {
            for (Review review : reviews) {
                reviewsEJB.remove(review);
            }
            artistsEJB.remove(artist);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó correctamente", "Se eliminó"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteAlbum(Album album) {
        try {
            albumsEJB.remove(album);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó correctamente", "Se eliminó"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public ListArtistsController getListArtistsController() {
        return listArtistsController;
    }

    public void setListArtistsController(ListArtistsController listArtistsController) {
        this.listArtistsController = listArtistsController;
    }

    public Album getNewAlbum() {
        return newAlbum;
    }

    public void setNewAlbum(Album newAlbum) {
        this.newAlbum = newAlbum;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public int getAlbumNumberOfSongs() {
        return albumNumberOfSongs;
    }

    public void setAlbumNumberOfSongs(int albumNumberOfSongs) {
        this.albumNumberOfSongs = albumNumberOfSongs;
    }

    public Date getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public void setAlbumReleaseDate(Date albumReleaseDate) {
        this.albumReleaseDate = albumReleaseDate;
    }

    public int getAlbumRating() {
        return albumRating;
    }

    public void setAlbumRating(int albumRating) {
        this.albumRating = albumRating;
    }

    public String getAlbumImageURL() {
        return albumImageURL;
    }

    public void setAlbumImageURL(String albumImageURL) {
        this.albumImageURL = albumImageURL;
    }

    public String getAlbumGenre() {
        return albumGenre;
    }

    public void setAlbumGenre(String albumGenre) {
        this.albumGenre = albumGenre;
    }

    public Review getNewReview() {
        return newReview;
    }

    public void setNewReview(Review newReview) {
        this.newReview = newReview;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
