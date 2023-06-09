/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.ReviewFacadeLocal;
import EJB.TvShowFacadeLocal;
import Modelo.Review;
import Modelo.TvShow;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@RequestScoped
public class ListTvShowsController implements Serializable {

    private List<TvShow> tvShows;
    private String filter;
    private String order;
    private List<TvShow> filteredTvShows;
    private Date dateSince;
    private Date dateTo;
    private List<Review> reviews;

    @Inject
    private TvShow tvShow;

    @EJB
    private TvShowFacadeLocal tvShowsEJB;
    @EJB
    private ReviewFacadeLocal reviewsEJB;

    private TvShow newTvShow;

    private String tvShowTitle;
    private String tvShowDescription;
    private int tvShowRating;
    private Date tvShowReleaseDate;
    private String tvShowImageUrl;
    private String tvShowGenre;
    private String tvShowImages;

    @PostConstruct
    public void init() {
        dateSince = new Date();
        dateTo = new Date();
        tvShows = tvShowsEJB.findAll();
        filterTvShows();
        reviews = reviewsEJB.findReviewsTvShow();
    }

    public void view(TvShow tvShow) throws IOException {
        this.tvShow = tvShow;
    }

    public void filterTvShows() {
        if (filter == null || filter.isEmpty()) {
            filteredTvShows = tvShowsEJB.findAll();
            filter = "";
        } else {
            filteredTvShows = tvShowsEJB.findByGenre(filter);
            filter = "";
        }
    }

    public List<String> getGenreList() {
        Set<String> genres = new HashSet<>();
        for (TvShow tvShow : tvShows) {
            genres.add(tvShow.getGenre());
        }
        return new ArrayList<>(genres);
    }

    public void filterDates() {
        if (dateSince == null || dateTo == null) {
            filteredTvShows = tvShowsEJB.findAll();
        } else {
            filteredTvShows = tvShowsEJB.findByDate(dateSince, dateTo);
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

    public void order() {
        filteredTvShows = tvShowsEJB.orderBy(order);
    }

    public void addtvShow() {
        newTvShow = new TvShow();

        try {
            newTvShow.setTitle(tvShowTitle);
            newTvShow.setDescription(tvShowDescription);
            newTvShow.setRating(tvShowRating);
            newTvShow.setReleaseDate(tvShowReleaseDate);
            newTvShow.setImageURL(tvShowImageUrl);
            newTvShow.setGenre(tvShowGenre);
            newTvShow.setImages(tvShowImages);
            tvShowsEJB.create(newTvShow);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se añadió correctamente", "Se añadio"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        tvShowTitle = "";
        tvShowDescription = "";
        tvShowRating = 0;
        tvShowImageUrl = "";
        tvShowGenre = "";
        tvShowImages = "";
    }

    public TvShow getNewTvShow() {
        return newTvShow;
    }

    public void setNewTvShow(TvShow newTvShow) {
        this.newTvShow = newTvShow;
    }

    public String getTvShowTitle() {
        return tvShowTitle;
    }

    public void setTvShowTitle(String tvShowTitle) {
        this.tvShowTitle = tvShowTitle;
    }

    public String getTvShowDescription() {
        return tvShowDescription;
    }

    public void setTvShowDescription(String tvShowDescription) {
        this.tvShowDescription = tvShowDescription;
    }

    public int getTvShowRating() {
        return tvShowRating;
    }

    public void setTvShowRating(int tvShowRating) {
        this.tvShowRating = tvShowRating;
    }

    public Date getTvShowReleaseDate() {
        return tvShowReleaseDate;
    }

    public void setTvShowReleaseDate(Date tvShowReleaseDate) {
        this.tvShowReleaseDate = tvShowReleaseDate;
    }

    public String getTvShowImageUrl() {
        return tvShowImageUrl;
    }

    public void setTvShowImageUrl(String tvShowImageUrl) {
        this.tvShowImageUrl = tvShowImageUrl;
    }

    public String getTvShowGenre() {
        return tvShowGenre;
    }

    public void setTvShowGenre(String tvShowGenre) {
        this.tvShowGenre = tvShowGenre;
    }

    public String getTvShowImages() {
        return tvShowImages;
    }

    public void setTvShowImages(String tvShowImages) {
        this.tvShowImages = tvShowImages;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<TvShow> getFilteredTvShows() {
        return filteredTvShows;
    }

    public void setFilteredTvShows(List<TvShow> filteredTvShows) {
        this.filteredTvShows = filteredTvShows;
    }

    public List<TvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TvShow> tvShows) {
        this.tvShows = tvShows;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    public TvShowFacadeLocal getTvShowsEJB() {
        return tvShowsEJB;
    }

    public void setTvShowsEJB(TvShowFacadeLocal tvShowsEJB) {
        this.tvShowsEJB = tvShowsEJB;
    }

}
