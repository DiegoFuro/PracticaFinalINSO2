/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.ReviewFacadeLocal;
import Modelo.Review;
import Modelo.TvShow;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@ViewScoped
public class TvShowController implements Serializable {

    @Inject
    private ListTvShowsController listTvShowsController;
    
    private List<Review> reviews;
    
    @EJB
    private ReviewFacadeLocal reviewEJB;

    private TvShow tvShow;

    @PostConstruct
    public void init() {
        tvShow = listTvShowsController.getTvShow();
        reviews = reviewEJB.findReviewsTvShow(tvShow);
    }

    public ListTvShowsController getListTvShowsController() {
        return listTvShowsController;
    }

    public void setListTvShowsController(ListTvShowsController listTvShowsController) {
        this.listTvShowsController = listTvShowsController;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    

}
