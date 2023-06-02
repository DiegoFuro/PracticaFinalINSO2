/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.MovieFacadeLocal;
import EJB.ReviewFacadeLocal;
import Modelo.Movie;
import Modelo.Review;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
@SessionScoped
public class ListMoviesController implements Serializable {

    private List<Movie> movies;
    private String filter;
    private String order;
    private List<Movie> filteredMovies;
    private Date dateSince;
    private Date dateTo;
    private List<Review> reviews;

    @Inject
    private Movie movie;

    @EJB
    private MovieFacadeLocal moviesEJB;
    @EJB
    private ReviewFacadeLocal reviewsEJB;

    private Movie newMovie;

    private String movieTitle;
    private String movieDescription;
    private int movieRating;
    private Date movieReleaseDate;
    private String movieImageUrl;
    private String movieGenre;
    private String movieImages;

    @PostConstruct
    public void init() {
        System.out.println("MOVIEEEE");
        reviews = reviewsEJB.findReviewsMovie();
        dateSince = new Date();
        dateTo = new Date();
        movies = moviesEJB.findAll();
        filterMovies();
    }

    public void addMovie() {
        newMovie = new Movie();

        try {
            newMovie.setTitle(movieTitle);
            newMovie.setDescription(movieDescription);
            newMovie.setRating(movieRating);
            newMovie.setReleaseDate(movieReleaseDate);
            newMovie.setImageURL(movieImageUrl);
            newMovie.setGenre(movieGenre);
            newMovie.setImages(movieImages);
            moviesEJB.create(newMovie);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se añadió correctamente", "Se añadio"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        movieTitle = "";
        movieDescription = "";
        movieRating = 0;
        movieImageUrl = "";
        movieGenre = "";
        movieImages = "";
    }

    public void filterMovies() {
        if (filter == null || filter.isEmpty()) {
            filteredMovies = moviesEJB.findAll();
            filter = "";
        } else {
            filteredMovies = moviesEJB.findByGenre(filter);
            filter = "";
        }
    }

    public void filterDates() {
        if (dateSince == null || dateTo == null) {
            filteredMovies = moviesEJB.findAll();
        } else {
            filteredMovies = moviesEJB.findByDate(dateSince, dateTo);
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
        for (Movie movie : movies) {
            genres.add(movie.getGenre());
        }
        return new ArrayList<>(genres);
    }

    public void order() {
        filteredMovies = moviesEJB.orderBy(order);
    }

    public void view(Movie movie) throws IOException {
        this.movie = movie;
    }

    public void resetDates() {
        dateSince = new Date();
        dateTo = new Date();
    }

    public Movie getNewMovie() {
        return newMovie;
    }

    public void setNewMovie(Movie newMovie) {
        this.newMovie = newMovie;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    public Date getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(Date movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieImageUrl() {
        return movieImageUrl;
    }

    public void setMovieImageUrl(String movieImageUrl) {
        this.movieImageUrl = movieImageUrl;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieImages() {
        return movieImages;
    }

    public void setMovieImages(String movieImages) {
        this.movieImages = movieImages;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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

    public List<Movie> getFilteredMovies() {
        return filteredMovies;
    }

    public void setFilteredMovies(List<Movie> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieFacadeLocal getMoviesEJB() {
        return moviesEJB;
    }

    public void setMoviesEJB(MovieFacadeLocal moviesEJB) {
        this.moviesEJB = moviesEJB;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
