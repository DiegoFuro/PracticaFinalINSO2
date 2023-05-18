/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.MovieFacadeLocal;
import Modelo.Movie;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
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
public class ListMoviesController implements Serializable {

    private List<Movie> movies;
    private String filter;
    private String order;
    private List<Movie> filteredMovies;
    private Date dateSince;
    private Date dateTo;

    @Inject
    private Movie movie;

    @EJB
    private MovieFacadeLocal moviesEJB;

    @PostConstruct
    public void init() {
        dateSince = new Date();
        dateTo = new Date();
        movies = moviesEJB.findAll();
        filterMovies();
    }

    public void filterMovies() {
        if (filter == null || filter.isEmpty()) {
            filteredMovies = moviesEJB.findAll();
            filter = "";
        } else {
            filteredMovies = moviesEJB.findByGenre(filter);
            filter="";
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
        orders.add("Alfabético Ascendente");
        orders.add("Alfabético Descendente");
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
        if (order == null || order.isEmpty()) {
            filteredMovies = moviesEJB.findAll();
        }
        System.out.println("ORDENAR: " + order);
        filteredMovies = moviesEJB.orderBy(order);
    }

    public void view(Movie movie) throws IOException {
        this.movie = movie;
    }

    public void resetDates() {
        dateSince = new Date();
        dateTo = new Date();
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
