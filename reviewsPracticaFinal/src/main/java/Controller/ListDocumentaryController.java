/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.DocumentaryFacadeLocal;
import EJB.MovieFacadeLocal;
import EJB.ReviewFacadeLocal;
import Modelo.Documentary;
import Modelo.Movie;
import Modelo.Review;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private String order;
    private List<Documentary> filteredDocumentaries;
    private Date dateSince;
    private Date dateTo;
    private List<Review> reviews;

    @Inject
    private Documentary documentary;

    @EJB
    private DocumentaryFacadeLocal documentariesEJB;
    @EJB
    private ReviewFacadeLocal reviewsEJB;

    @PostConstruct
    public void init() {
        dateSince = new Date();
        dateTo = new Date();
        documentaries = documentariesEJB.findAll();
        filterDocumentaries();
        reviews = reviewsEJB.findReviewsMovie();
    }

    public void filterDocumentaries() {
        if (filter == null || filter.isEmpty()) {
            filteredDocumentaries = documentariesEJB.findAll();
            filter = "";
        } else {
            filteredDocumentaries = documentariesEJB.findByGenre(filter);
            filter = "";
        }
    }

    public void filterDates() {
        if (dateSince == null || dateTo == null) {
            filteredDocumentaries = documentariesEJB.findAll();
        } else {
            filteredDocumentaries = documentariesEJB.findByDate(dateSince, dateTo);
        }
    }

    public List<String> getOrderList() {
        Set<String> orders = new HashSet<>();
        orders.add("Alfabético (A-Z)");
        orders.add("Alfabético (Z-A)");
        orders.add("Valoración Ascendente");
        orders.add("Valoración Descendente");
        orders.add("Fecha - Nuevas Primero");
        orders.add("Fecha - Antiguas Primero");
        return new ArrayList<>(orders);
    }

    public List<String> getGenreList() {
        Set<String> genres = new HashSet<>();
        for (Documentary documentary : documentaries) {
            genres.add(documentary.getGenre());
        }
        return new ArrayList<>(genres);
    }

    public void order() {
        if (order == null || order.isEmpty()) {
            filteredDocumentaries = documentariesEJB.findAll();
        }
        System.out.println("ORDENAR: " + order);
        filteredDocumentaries = documentariesEJB.orderBy(order);
    }

    public void view(Documentary documentary) {
        this.documentary = documentary;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Date getDateSince() {
        return dateSince;
    }

    public void setDateSince(Date dateSince) {
        this.dateSince = dateSince;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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
