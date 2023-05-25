/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Book;
import java.sql.Date;
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
public class BookFacade extends AbstractFacade<Book> implements BookFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }

    @Override
    public List<Book> findLastBooks() {
        Date filterDate = Date.valueOf("2023-01-01");

        String consulta = "FROM Book b WHERE b.releaseDate > :param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", filterDate);

        List<Book> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Book> findBestBooks() {
        String consulta = "FROM Book b WHERE b.rating > :param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", 90);

        List<Book> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Book> findCrimeBooks() {
        String consulta = "FROM Book b WHERE b.genre =:param1 OR b.genre =:param2 OR b.genre =:param3";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", "Crimen");
        query.setParameter("param2", "Terror");
        query.setParameter("param3", "Policiaco");

        List<Book> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Book> findYouthBooks() {
        String consulta = "FROM Book b WHERE b.genre =:param1 OR b.genre =:param2";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", "Juvenil");
        query.setParameter("param2", "Infantil");

        List<Book> resultado = query.getResultList();
        return resultado;
    }

}
