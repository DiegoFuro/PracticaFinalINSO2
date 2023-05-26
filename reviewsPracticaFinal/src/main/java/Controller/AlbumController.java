/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.AlbumFacadeLocal;
import EJB.ReviewFacadeLocal;
import Modelo.Album;
import Modelo.Review;
import Modelo.Usuario;
import java.io.Serializable;
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
public class AlbumController implements Serializable {

    @Inject
    private ListAlbumsController listAlbumsController;

    private List<Review> reviews;
    private Review newReview;

    private List<Album> albums;

    @EJB
    private ReviewFacadeLocal reviewsEJB;

    @EJB
    private AlbumFacadeLocal albumsEJB;

    private Album album;
    private String reviewTitle;
    private String reviewBody;
    private int reviewRating;

    @PostConstruct
    public void init() {
        album = listAlbumsController.getAlbum();
        reviews = reviewsEJB.findReviewsAlbums(album);
    }

    public void writeReview() {
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        newReview = new Review();

        try {
            newReview.setIdUser(user);
            newReview.setAlbum(album);
            newReview.setTitle(reviewTitle);
            newReview.setBody(reviewBody);
            newReview.setRating(reviewRating);
            reviewsEJB.create(newReview);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se añadió correctamente", "Se añadio"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public Review getNewReview() {
        return newReview;
    }

    public void setNewReview(Review newReview) {
        this.newReview = newReview;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
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
