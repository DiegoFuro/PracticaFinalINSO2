/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

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
        System.out.println("ESTOY AQUI: " + tvShow.getTitle());
        String consulta = "FROM Review r WHERE r.tvShow=:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", tvShow);
        System.out.println("CONSULTA: " + query.toString());
        List<Review> resultado = query.getResultList();

        return resultado;
    }

}
