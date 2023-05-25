/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Movie;
import Modelo.Review;
import Modelo.TvShow;
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
        String consulta = "FROM Review r WHERE r.tvShow=:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", tvShow);
        List<Review> resultado = query.getResultList();

        return resultado;
    }

    @Override
    public List<Review> findReviewsMovie() {
        String consulta = "FROM Review r WHERE r.movie IS NOT NULL";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Review> findReviewsMovie(Movie movie) {
        String consulta = "FROM Review r WHERE r.movie=:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", movie);
        List<Review> resultado = query.getResultList();

        return resultado;
    }

    @Override
    public List<Review> findReviewsTvShow() {
        String consulta = "FROM Review r WHERE r.tvShow IS NOT NULL";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Review> findReviewsDocumentary() {
        String consulta = "FROM Review r WHERE r.documentary IS NOT NULL";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }
    
    @Override
    public List<Review> findReviewsArtist() {
        String consulta = "FROM Review r WHERE r.artist IS NOT NULL";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }
    
    @Override
    public List<Review> findReviewsAlbums() {
        String consulta = "FROM Review r WHERE r.album IS NOT NULL";

        Query query = em.createQuery(consulta);
        query.setMaxResults(3);

        List<Review> resultado = query.getResultList();
        return resultado;
    }

}
