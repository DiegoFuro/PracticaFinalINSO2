/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.ReviewFacadeLocal;
import Modelo.Documentary;
import Modelo.Review;
import Modelo.TvShow;
import Modelo.Videogame;
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
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.line.LineChartOptions;

/**
 *
 * @author Diego
 */
@Named
@ViewScoped
public class DocumentaryController implements Serializable {

    @Inject
    private ListDocumentaryController listDocumentaryController;

    private List<Review> reviews;

    @EJB
    private ReviewFacadeLocal reviewEJB;

    private Documentary documentary;
    private DonutChartModel donutModel;

    @PostConstruct
    public void init() {
        documentary = listDocumentaryController.getDocumentary();
        createDonutModel();
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(documentary.getRating());
        values.add(100 - documentary.getRating());
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(244, 251, 16)");
        bgColors.add("rgb(255, 255, 255, 0)");

        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
      
        donutModel.setData(data);
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public ListDocumentaryController getListDocumentaryController() {
        return listDocumentaryController;
    }

    public void setListDocumentaryController(ListDocumentaryController listDocumentaryController) {
        this.listDocumentaryController = listDocumentaryController;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public ReviewFacadeLocal getReviewEJB() {
        return reviewEJB;
    }

    public void setReviewEJB(ReviewFacadeLocal reviewEJB) {
        this.reviewEJB = reviewEJB;
    }

    public Documentary getDocumentary() {
        return documentary;
    }

    public void setDocumentary(Documentary documentary) {
        this.documentary = documentary;
    }

   
}
