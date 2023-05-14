/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.Movie;
import Modelo.Review;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@ViewScoped
public class MovieController implements Serializable {

    @Inject
    private ListMoviesController listMoviesController;

    private List<Review> reviews;

    private Movie movie;

    @PostConstruct
    public void init() {
        movie = listMoviesController.getMovie();
        System.out.println("MOVIE 2: " + movie.getTitle());
    }

    public ListMoviesController getListMoviesController() {
        return listMoviesController;
    }

    public void setListMoviesController(ListMoviesController listMoviesController) {
        this.listMoviesController = listMoviesController;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
