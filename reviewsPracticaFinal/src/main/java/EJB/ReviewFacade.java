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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diego
 */
@Stateless
public class ReviewFacade extends AbstractFacade<Review> implements ReviewFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReviewFacade() {
        super(Review.class);
    }

    public List<Review> findReviewsTvShow(TvShow tvShow) {
        String consulta = "FROM Review r WHERE r.tvShow=:param1 ORDER BY r.votes DESC";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", tvShow);
        List<Review> resultado = query.getResultList();

        return resultado;
    }

    @Override
    public List<Review> findReviewsMovie() {
        String consulta = "FROM Review r WHERE r.movie IS NOT NULL ORDER BY r.movie DESC";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Review> findReviewsMovie(Movie movie) {
        String consulta = "FROM Review r WHERE r.movie=:param1 ORDER BY r.votes DESC";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", movie);
        List<Review> resultado = query.getResultList();

        return resultado;
    }

    @Override
    public List<Review> findReviewsTvShow() {
        String consulta = "FROM Review r WHERE r.tvShow IS NOT NULL ORDER BY r.tvShow DESC";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Review> findReviewsDocumentary() {
        String consulta = "FROM Review r WHERE r.documentary IS NOT NULL ORDER BY r.documentary DESC";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Review> findReviewsArtist() {
        String consulta = "FROM Review r WHERE r.artist IS NOT NULL ORDER BY r.artist DESC";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Review> findReviewsAlbums() {
        String consulta = "FROM Review r WHERE r.album IS NOT NULL ORDER BY r.album DESC";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Review> findReviewsDocumentary(Documentary documentary) {
        String consulta = "FROM Review r WHERE r.documentary=:param1 ORDER BY r.votes DESC";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", documentary);
        List<Review> resultado = query.getResultList();

        return resultado;
    }

    @Override
    public List<Review> findReviewsAlbums(Album album) {
        String consulta = "FROM Review r WHERE r.album=:param1 ORDER BY r.votes DESC";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", album);
        List<Review> resultado = query.getResultList();

        return resultado;
    }

    @Override
    public List<Review> findReviewsArtist(Artist artist) {
        String consulta = "FROM Review r WHERE r.artist=:param1 ORDER BY r.votes DESC";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", artist);
        List<Review> resultado = query.getResultList();

        return resultado;
    }

    @Override
    public List<Review> findReviewsBook(Book book) {
        String consulta = "FROM Review r WHERE r.book=:param1 ORDER BY r.votes DESC";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", book);
        List<Review> resultado = query.getResultList();

        return resultado;
    }

    @Override
    public List<Review> findReviewsVideogame(Videogame videogame) {
        String consulta = "FROM Review r WHERE r.videogame=:param1 ORDER BY r.votes DESC";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", videogame);
        List<Review> resultado = query.getResultList();

        return resultado;
    }

}
