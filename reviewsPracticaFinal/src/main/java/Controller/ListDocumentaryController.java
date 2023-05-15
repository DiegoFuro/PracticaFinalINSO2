/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.DocumentaryFacadeLocal;
import EJB.MovieFacadeLocal;
import Modelo.Documentary;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@SessionScoped
public class ListDocumentaryController implements Serializable {

    private List<Documentary> documentaries;
    private String filter;
    private List<Documentary> filteredDocumentaries;

    @Inject
    private Documentary documentary;

    @EJB
    private DocumentaryFacadeLocal documentariesEJB;

    @PostConstruct
    public void init() {
        documentaries = documentariesEJB.findAll();
        filterDocumentaries();
    }

    public void filterDocumentaries() {
        if (filter == null || filter.isEmpty()) {
            filteredDocumentaries = documentariesEJB.findAll();
        } else {
            filteredDocumentaries = documentariesEJB.findByGenre(filter);
        }
    }

    public List<String> getGenreList() {
        Set<String> genres = new HashSet<>();
        for (Documentary documentary : documentaries) {
            genres.add(documentary.getGenre());
        }
        return new ArrayList<>(genres);
    }

    public void view(Documentary documentary) throws IOException {
        this.documentary = documentary;
    }

    public List<Documentary> getDocumentaries() {
        return documentaries;
    }

    public void setDocumentaries(List<Documentary> documentaries) {
        this.documentaries = documentaries;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<Documentary> getFilteredDocumentaries() {
        return filteredDocumentaries;
    }

    public void setFilteredDocumentaries(List<Documentary> filteredDocumentaries) {
        this.filteredDocumentaries = filteredDocumentaries;
    }

    public Documentary getDocumentary() {
        return documentary;
    }

    public void setDocumentary(Documentary documentary) {
        this.documentary = documentary;
    }

    public DocumentaryFacadeLocal getDocumentariesEJB() {
        return documentariesEJB;
    }

    public void setDocumentariesEJB(DocumentaryFacadeLocal documentariesEJB) {
        this.documentariesEJB = documentariesEJB;
    }

    
}
