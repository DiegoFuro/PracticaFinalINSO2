/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.ArtistFacadeLocal;
import EJB.ReviewFacadeLocal;
import Modelo.Artist;
import Modelo.Review;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@SessionScoped
public class ListArtistsController implements Serializable {

    private List<Artist> artists;
    private String filter;
    private String order;
    private List<Artist> filteredArtists;
    private Date dateSince;
    private Date dateTo;
    private List<Review> reviews;

    @Inject
    private Artist artist;

    @EJB
    private ArtistFacadeLocal artistsEJB;
    @EJB
    private ReviewFacadeLocal reviewsEJB;

    @PostConstruct
    public void init() {
        dateSince = new Date();
        dateTo = new Date();
        artists = artistsEJB.findAll();
        filterMovies();
        reviews = reviewsEJB.findReviewsMovie();
    }

    public void filterMovies() {
        if (filter == null || filter.isEmpty()) {
            filteredArtists = artistsEJB.findAll();
            filter = "";
        } else {
            filteredArtists = artistsEJB.findByGenre(filter);
            filter = "";
        }
    }

    public void filterDates() {
        if (dateSince == null || dateTo == null) {
            filteredArtists = artistsEJB.findAll();
        } else {
            filteredArtists = artistsEJB.findByDate(dateSince, dateTo);
        }
    }

    public List<String> getOrderList() {
        Set<String> orders = new HashSet<>();
        orders.add("Alfabético (A-Z)");
        orders.add("Alfabético (Z-A)");
        orders.add("Valoración Ascendente");
        orders.add("Valoración Descendente");
        orders.add("Fecha - Nuevas Primero");
        orders.add("Fecha - Antiguas Primero");
        return new ArrayList<>(orders);
    }

    public List<String> getGenreList() {
        Set<String> genres = new HashSet<>();
        for (Artist artist : artists) {
        }
        return new ArrayList<>(genres);
    }

    public void order() {
        filteredArtists = artistsEJB.orderBy(order);
    }

    public void view(Artist artist) {
        this.artist = artist;
    }

    public void resetDates() {
        dateSince = new Date();
        dateTo = new Date();
    }

}
