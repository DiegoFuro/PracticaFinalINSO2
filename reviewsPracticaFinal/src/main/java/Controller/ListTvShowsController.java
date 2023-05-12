/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.TvShowFacadeLocal;
import Modelo.TvShow;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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

    @Inject
    private TvShow tvShow;

    @EJB
    private TvShowFacadeLocal tvShowsEJB;

    @PostConstruct
    public void init() {
        tvShows = tvShowsEJB.findAll();
    }

    public void view(TvShow tvShow) throws IOException {
        this.tvShow = tvShow;
        System.out.println("SERIE: " + this.tvShow.getTitle());
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
