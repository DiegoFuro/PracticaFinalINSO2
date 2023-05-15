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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private List<Videogame> lastVideogames;
    private List<Videogame> bestVideogames;
    private List<Videogame> horrorVideogames;
    private List<Videogame> adventureVideogames;

    @Inject
    private Videogame videogame;

    @EJB
    private VideogameFacadeLocal videogamesEJB;

    @PostConstruct
    public void init() {

        videogames = videogamesEJB.findAll();
        lastVideogames = videogamesEJB.findLastGames();
        bestVideogames = videogamesEJB.findBestVideogames();
        horrorVideogames = videogamesEJB.findHorrorVideogames();
        adventureVideogames = videogamesEJB.findAdventureGames();

    }

    public void view(Videogame videogame) throws IOException {
        this.videogame = videogame;
    }

    public List<Videogame> getHorrorVideogames() {
        return horrorVideogames;
    }

    public void setHorrorVideogames(List<Videogame> horrorVideogames) {
        this.horrorVideogames = horrorVideogames;
    }

    public List<Videogame> getAdventureVideogames() {
        return adventureVideogames;
    }

    public void setAdventureVideogames(List<Videogame> adventureVideogames) {
        this.adventureVideogames = adventureVideogames;
    }

    
    public List<Videogame> getBestVideogames() {
        return bestVideogames;
    }

    public void setBestVideogames(List<Videogame> bestVideogames) {
        this.bestVideogames = bestVideogames;
    }

    public List<Videogame> getLastVideogames() {
        return lastVideogames;
    }

    public void setLastVideogames(List<Videogame> lastVideogames) {
        this.lastVideogames = lastVideogames;
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
