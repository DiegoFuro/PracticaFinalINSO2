/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.MovieFacadeLocal;
import EJB.TvShowFacadeLocal;
import Modelo.Movie;
import Modelo.TvShow;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@RequestScoped
public class listTvShowsController implements Serializable {

    private List<TvShow> tvShows;

    @Inject
    private TvShow tvShow;

    @EJB
    private TvShowFacadeLocal tvShowsEJB;

    @PostConstruct
    public void init() {
        tvShows = tvShowsEJB.findAll();
    }

    public void establecerPublicacion(TvShow tvShow) throws IOException {
        System.out.println("establecerPublicacion(" + tvShow + ")");
        this.tvShow = tvShow;
    }

    public void valorarPublicacion() {
        /*
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        try {
            externalContext.redirect("privado/profesor/editarPublicacion.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            ex.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al redireccionar a la página de edición", ex.getMessage()));
        }*/
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
