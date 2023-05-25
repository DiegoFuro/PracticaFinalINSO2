/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.ReviewFacadeLocal;
import Modelo.Book;
import Modelo.Review;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

    @EJB
    private ReviewFacadeLocal reviewEJB;

    private Book book;
    private DonutChartModel donutModel;

    @PostConstruct
    public void init() {
        book = listBooksController.getBook();
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
        bgColors.add("rgb(244, 251, 16)");
        bgColors.add("rgb(255, 255, 255, 0)");

        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);

        donutModel.setData(data);
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
