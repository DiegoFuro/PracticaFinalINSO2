/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Album;
import Modelo.Artist;
import Modelo.Book;
import Modelo.Documentary;
import Modelo.Movie;
import Modelo.Review;
import Modelo.TvShow;
import Modelo.Videogame;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface ReviewFacadeLocal {

    void create(Review review);

    void edit(Review review);

    void remove(Review review);

    Review find(Object id);

    List<Review> findAll();

    List<Review> findRange(int[] range);

    int count();

    List<Review> findReviewsTvShow(TvShow tvShow);

    List<Review> findReviewsMovie();

    List<Review> findReviewsMovie(Movie movie);

    List<Review> findReviewsTvShow();

    List<Review> findReviewsDocumentary();

    List<Review> findReviewsArtist();

    List<Review> findReviewsAlbums();

    List<Review> findReviewsDocumentary(Documentary documentary);

    List<Review> findReviewsAlbums(Album album);

    List<Review> findReviewsArtist(Artist artist);

    List<Review> findReviewsBook(Book book);

    List<Review> findReviewsVideogame(Videogame videogame);

}
