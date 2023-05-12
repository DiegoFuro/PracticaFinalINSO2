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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
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

    @Inject
    private Movie movie;

    @EJB
    private MovieFacadeLocal moviesEJB;

    @PostConstruct
    public void init() {
        movies = moviesEJB.findAll();
    }

    public void view(Movie movie) throws IOException {
        this.movie = movie;
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

}
