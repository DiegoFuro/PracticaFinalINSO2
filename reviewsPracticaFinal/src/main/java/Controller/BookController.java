/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.BookFacadeLocal;
import EJB.ReviewFacadeLocal;
import Modelo.Book;
import Modelo.Review;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
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
public class BookController implements Serializable {

    @Inject
    private ListBooksController listBooksController;

    private List<Review> reviews;
    private Review newReview;

    @EJB
    private ReviewFacadeLocal reviewsEJB;

    @EJB
    private BookFacadeLocal booksEJB;

    private Book book;
    private DonutChartModel donutModel;
    private String reviewTitle;
    private String reviewBody;
    private int reviewRating;

    @PostConstruct
    public void init() {
        book = listBooksController.getBook();
        reviews = reviewsEJB.findReviewsBook(book);
        createDonutModel();
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(book.getRating());
        values.add(100 - book.getRating());
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
            newReview.setBook(book);
            newReview.setTitle(reviewTitle);
            newReview.setBody(reviewBody);
            newReview.setRating(reviewRating);
            reviewsEJB.create(newReview);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se añadió correctamente", "Se añadio"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteReview(Review review) {
        try {
            reviewsEJB.remove(review);
            FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó correctamente", "Se eliminó"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void deleteBook() {
        try {
            for (Review review : reviews) {
                reviewsEJB.remove(review);
            }
            booksEJB.remove(book);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se eliminó correctamente", "Se eliminó"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public Review getNewReview() {
        return newReview;
    }

    public void setNewReview(Review newReview) {
        this.newReview = newReview;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

}
