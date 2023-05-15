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
import java.util.ArrayList;
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
    private List<Movie> filteredMovies;

    @Inject
    private Movie movie;

    @EJB
    private MovieFacadeLocal moviesEJB;

    @PostConstruct
    public void init() {
        movies = moviesEJB.findAll();
        filterMovies();
    }

    public void filterMovies() {
        if (filter == null || filter.isEmpty()) {
            filteredMovies = moviesEJB.findAll();
        } else {
            filteredMovies = moviesEJB.findByGenre(filter);
        }
    }

    public List<String> getGenreList() {
        Set<String> genres = new HashSet<>();
        for (Movie movie : movies) {
            genres.add(movie.getGenre());
        }
        return new ArrayList<>(genres);
    }

    public void view(Movie movie) throws IOException {
        System.out.println("Movie: " + movie.getTitle());
        this.movie = movie;
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
