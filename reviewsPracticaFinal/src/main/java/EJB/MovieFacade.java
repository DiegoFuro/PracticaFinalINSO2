/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Movie;
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

        System.out.println("Estoy aqui: " +dateSince.toString()+", " +dateTo.toString());
        String consulta = "FROM Movie m WHERE m.releaseDate > :param1 AND m.releaseDate < :param2";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", dateSince);
        query.setParameter("param2", dateTo);

        List<Movie> resultado = query.getResultList();
        System.out.println("Resultado: " + resultado.get(0).getTitle());
        return resultado;
    }

}
