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
public class ListAlbumsController implements Serializable {

    private List<Album> albums;
    private String filter;
    private String order;
    private List<Album> filteredAlbums;
    private Date dateSince;
    private Date dateTo;
    private List<Review> reviews;

    @Inject
    private Album album;

    @EJB
    private AlbumFacadeLocal albumsEJB;
    @EJB
    private ReviewFacadeLocal reviewsEJB;

    @PostConstruct
    public void init() {
        dateSince = new Date();
        dateTo = new Date();
        albums = albumsEJB.findAll();
        filterAlbums();
        reviews = reviewsEJB.findReviewsMovie();
    }

    public void filterAlbums() {
        if (filter == null || filter.isEmpty()) {
            filteredAlbums = albumsEJB.findAll();
            filter = "";
        } else {
            filteredAlbums = albumsEJB.findByGenre(filter);
            filter = "";
        }
    }

    public void filterDates() {
        if (dateSince == null || dateTo == null) {
            filteredAlbums = albumsEJB.findAll();
        } else {
            filteredAlbums = albumsEJB.findByDate(dateSince, dateTo);
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
        for (Album album : albums) {
            genres.add(album.getGenre());
        }
        return new ArrayList<>(genres);
    }

    public void order() {
        filteredAlbums = albumsEJB.orderBy(order);
    }

    public void view(Album album) {
        this.album = album;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<Album> getFilteredAlbums() {
        return filteredAlbums;
    }

    public void setFilteredAlbums(List<Album> filteredAlbums) {
        this.filteredAlbums = filteredAlbums;
    }

    public Date getDateSince() {
        return dateSince;
    }

    public void setDateSince(Date dateSince) {
        this.dateSince = dateSince;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
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
