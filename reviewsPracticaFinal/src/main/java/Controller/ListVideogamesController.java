/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.MovieFacadeLocal;
import EJB.VideogameFacadeLocal;
import Modelo.Movie;
import Modelo.Videogame;
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
public class ListVideogamesController implements Serializable {

    private List<Videogame> videogames;
    private String filter;

    @Inject
    private Videogame videogame;

    @EJB
    private VideogameFacadeLocal videogamesEJB;

    @PostConstruct
    public void init() {
        videogames = videogamesEJB.findAll();
    }

    public void view(Videogame videogame) throws IOException {
        this.videogame = videogame;
    }

    public List<Videogame> getVideogames() {
        return videogames;
    }

    public void setVideogames(List<Videogame> videogames) {
        this.videogames = videogames;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Videogame getVideogame() {
        return videogame;
    }

    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }

    public VideogameFacadeLocal getVideogamesEJB() {
        return videogamesEJB;
    }

    public void setVideogamesEJB(VideogameFacadeLocal videogamesEJB) {
        this.videogamesEJB = videogamesEJB;
    }

}
