/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.TvShow;
import java.util.Date;
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

    @Override
    public List<TvShow> findByDate(Date dateSince, Date dateTo) {

        String consulta = "FROM TvShow t WHERE t.releaseDate > :param1 AND t.releaseDate < :param2";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", dateSince);
        query.setParameter("param2", dateTo);

        List<TvShow> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<TvShow> orderBy(String order) {
        String consulta = "FROM TvShow t ORDER BY ";

        switch (order) {
            case "Alfabético (A-Z)":
                consulta += "t.title ASC";
                break;
            case "Alfabético (Z-A)":
                consulta += "t.title DESC";
                break;
            case "Valoración Ascendente":
                consulta += "t.rating ASC";
                break;
            case "Valoración Descendente":
                consulta += "t.rating DESC";
                break;
            case "Fecha - Antiguas Primero":
                consulta += "t.releaseDate ASC";
                break;
            case "Fecha - Nuevas Primero":
                consulta += "t.releaseDate DESC";
                break;
            default:
                consulta = "FROM TvShow t";
        }

        Query query = em.createQuery(consulta);
        List<TvShow> resultado = query.getResultList();
        return resultado;
    }

}
