/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

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
public class TvShowFacade extends AbstractFacade<TvShow> implements TvShowFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TvShowFacade() {
        super(TvShow.class);
    }

    @Override
    public List<TvShow> findByGenre(String filter) {
        String consulta = "FROM TvShow t WHERE t.genre=:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", filter);

        List<TvShow> resultado = query.getResultList();
        return resultado;
    }
    
}
