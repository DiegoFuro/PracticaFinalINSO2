/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.ReviewFacadeLocal;
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
public class VideogameController implements Serializable {

    @Inject
    private ListVideogamesController listVideogamesController;

    private List<Review> reviews;

    @EJB
    private ReviewFacadeLocal reviewEJB;

    private Videogame videogame;
    private DonutChartModel donutModel;

    @PostConstruct
    public void init() {
        videogame = listVideogamesController.getVideogame();
        createDonutModel();
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(videogame.getRating());
        values.add(100 - videogame.getRating());
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

    public ListVideogamesController getListVideogamesController() {
        return listVideogamesController;
    }

    public void setListVideogamesController(ListVideogamesController listVideogamesController) {
        this.listVideogamesController = listVideogamesController;
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

    public Videogame getVideogame() {
        return videogame;
    }

    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }

}
