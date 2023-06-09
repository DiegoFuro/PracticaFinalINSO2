/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Movie;
import Modelo.Review;
import Modelo.Usuario;
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
public class MovieFacade extends AbstractFacade<Movie> implements MovieFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovieFacade() {
        super(Movie.class);
    }

    public List<Movie> findByGenre(String filter) {
        String consulta = "FROM Movie m WHERE m.genre=:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", filter);

        List<Movie> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Movie> findByDate(Date dateSince, Date dateTo) {
        String consulta = "FROM Movie m WHERE m.releaseDate > :param1 AND m.releaseDate < :param2";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", dateSince);
        query.setParameter("param2", dateTo);

        List<Movie> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Movie> orderBy(String order) {
        String consulta = "FROM Movie m ORDER BY ";

        switch (order) {
            case "Alfabético (A-Z)":
                consulta += "m.title ASC";
                break;
            case "Alfabético (Z-A)":
                consulta += "m.title DESC";
                break;
            case "Valoración Ascendente":
                consulta += "m.rating ASC";
                break;
            case "Valoración Descendente":
                consulta += "m.rating DESC";
                break;
            case "Fecha - Antiguas Primero":
                consulta += "m.releaseDate ASC";
                break;
            case "Fecha - Nuevas Primero":
                consulta += "m.releaseDate DESC";
                break;
            default:
                consulta = "FROM Movie m";
        }

        Query query = em.createQuery(consulta);
        List<Movie> resultado = query.getResultList();
        return resultado;
    }

}
