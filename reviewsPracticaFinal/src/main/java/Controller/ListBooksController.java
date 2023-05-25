/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.BookFacadeLocal;
import Modelo.Book;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
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
public class ListBooksController implements Serializable {

    private List<Book> books;
    private String filter;
    private List<Book> lastBooks;
    private List<Book> bestBooks;
    private List<Book> crimeBooks;
    private List<Book> youthBooks;

    @Inject
    private Book book;

    @EJB
    private BookFacadeLocal booksEJB;

    @PostConstruct
    public void init() {

        books = booksEJB.findAll();
        lastBooks = booksEJB.findLastBooks();
        bestBooks = booksEJB.findBestBooks();
        crimeBooks = booksEJB.findCrimeBooks();
        youthBooks = booksEJB.findYouthBooks();

    }

    public void view(Book book) throws IOException {
        this.book = book;
    }

    public List<Book> getYouthBooks() {
        return youthBooks;
    }

    public void setYouthBooks(List<Book> youthBooks) {
        this.youthBooks = youthBooks;
    }
    

    public List<Book> getCrimeBooks() {
        return crimeBooks;
    }

    public void setCrimeBooks(List<Book> crimeBooks) {
        this.crimeBooks = crimeBooks;
    }
    

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<Book> getLastBooks() {
        return lastBooks;
    }

    public void setLastBooks(List<Book> lastBooks) {
        this.lastBooks = lastBooks;
    }

    public List<Book> getBestBooks() {
        return bestBooks;
    }

    public void setBestBooks(List<Book> bestBooks) {
        this.bestBooks = bestBooks;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
