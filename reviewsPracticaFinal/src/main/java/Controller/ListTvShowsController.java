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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private String filter;
    private List<TvShow> filteredTvShows;

    @Inject
    private TvShow tvShow;

    @EJB
    private TvShowFacadeLocal tvShowsEJB;

    @PostConstruct
    public void init() {
        tvShows = tvShowsEJB.findAll();
        filterTvShows();
    }

    public void view(TvShow tvShow) throws IOException {
        this.tvShow = tvShow;
        
    }

    public void filterTvShows() {
        if (filter == null || filter.isEmpty()) {
            filteredTvShows = tvShowsEJB.findAll();
        } else {
            filteredTvShows = tvShowsEJB.findByGenre(filter);
        }
    }

    public List<String> getGenreList() {
        Set<String> genres = new HashSet<>();
        for (TvShow tvShow : tvShows) {
            genres.add(tvShow.getGenre());
        }
        return new ArrayList<>(genres);
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
