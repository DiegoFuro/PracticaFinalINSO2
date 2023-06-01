/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.MovieFacadeLocal;
import EJB.ReviewFacadeLocal;
import EJB.VoteFacadeLocal;
import Modelo.Movie;
import Modelo.Review;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

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
    private Review newReview;
    private Usuario us;

    @EJB
    private MovieFacadeLocal moviesEJB;

    @EJB
    private VoteFacadeLocal votesEJB;

    @EJB
    private ReviewFacadeLocal reviewsEJB;

    private Movie movie;
    private DonutChartModel donutModel;

    private String reviewTitle;
    private String reviewBody;
    private int reviewRating;
    private boolean upVote;
    private boolean downvote;

    @PostConstruct
    public void init() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        movie = listMoviesController.getMovie();
        reviews = reviewsEJB.findReviewsMovie(movie);
        createDonutModel();
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(movie.getRating());
        values.add(100 - movie.getRating());
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(3, 111, 171)");
        bgColors.add("rgb(255, 255, 255, 0)");

        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);

        donutModel.setData(data);
    }

    public void writeReview() {
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        newReview = new Review();

        try {
            newReview.setIdUser(user);
            newReview.setMovie(movie);
            newReview.setTitle(reviewTitle);
            newReview.setBody(reviewBody);
            newReview.setRating(reviewRating);
            reviewsEJB.create(newReview);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se añadió correctamente", "Se añadio"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteMovie() {
        try {
            for (Review review : reviews) {
                reviewsEJB.remove(review);
            }
            moviesEJB.remove(movie);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó correctamente", "Se eliminó"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteReview(Review review) {
        try {
            reviewsEJB.remove(review);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó correctamente", "Se eliminó"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void incrementVotes(Review review) {
        review.setVotes(review.getVotes() + 1);
        reviewsEJB.edit(review);
        upVote = true;
    }

    public void decrementVotes(Review review) {
        review.setVotes(review.getVotes() - 1);
        reviewsEJB.edit(review);
        downvote = true;
    }

    public boolean isUpVote() {
        return upVote;
    }

    public void setUpVote(boolean upVote) {
        this.upVote = upVote;
    }

    public boolean isDownvote() {
        return downvote;
    }

    public void setDownvote(boolean downvote) {
        this.downvote = downvote;
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

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
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
