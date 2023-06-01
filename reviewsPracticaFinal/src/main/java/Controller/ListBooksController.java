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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private Book newBook;

    private String bookTitle;
    private String bookDescription;
    private int bookRating;
    private Date bookReleaseDate;
    private String bookImageUrl;
    private String bookGenre;
    private String bookImages;

    @PostConstruct
    public void init() {

        books = booksEJB.findAll();
        lastBooks = booksEJB.findLastBooks();
        bestBooks = booksEJB.findBestBooks();
        crimeBooks = booksEJB.findCrimeBooks();
        youthBooks = booksEJB.findYouthBooks();

    }

    public void addBook() {
        newBook = new Book();

        try {
            newBook.setTitle(bookTitle);
            newBook.setDescription(bookDescription);
            newBook.setRating(bookRating);
            newBook.setReleaseDate(bookReleaseDate);
            newBook.setImageURL(bookImageUrl);
            newBook.setGenre(bookGenre);
            newBook.setImages(bookImages);
            booksEJB.create(newBook);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se añadió correctamente", "Se añadio"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        bookTitle = "";
        bookDescription = "";
        bookRating = 0;
        bookImageUrl = "";
        bookGenre = "";
        bookImages = "";
    }

    public void view(Book book) throws IOException {
        this.book = book;
    }

    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public int getBookRating() {
        return bookRating;
    }

    public void setBookRating(int bookRating) {
        this.bookRating = bookRating;
    }

    public Date getBookReleaseDate() {
        return bookReleaseDate;
    }

    public void setBookReleaseDate(Date bookReleaseDate) {
        this.bookReleaseDate = bookReleaseDate;
    }

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookImages() {
        return bookImages;
    }

    public void setBookImages(String bookImages) {
        this.bookImages = bookImages;
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
