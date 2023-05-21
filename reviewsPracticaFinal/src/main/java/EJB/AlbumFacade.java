/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Album;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ainoa
 */
@Stateless
public class AlbumFacade extends AbstractFacade<Album> implements AlbumFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlbumFacade() {
        super(Album.class);
    }

    @Override
    public List<Album> findByGenre(String filter) {
        String consulta = "FROM Album a WHERE a.genre=:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", filter);

        List<Album> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Album> findByDate(Date dateSince, Date dateTo) {
        String consulta = "FROM Album a WHERE a.releaseDate > :param1 AND a.releaseDate < :param2";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", dateSince);
        query.setParameter("param2", dateTo);

        List<Album> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Album> orderBy(String order) {
        String consulta = "FROM Album a ORDER BY ";

        switch (order) {
            case "Alfabético (A-Z)":
                consulta += "a.title ASC";
                break;
            case "Alfabético (Z-A)":
                consulta += "a.title DESC";
                break;
            case "Valoración Ascendente":
                consulta += "a.rating ASC";
                break;
            case "Valoración Descendente":
                consulta += "a.rating DESC";
                break;
            case "Fecha - Antiguas Primero":
                consulta += "a.releaseDate ASC";
                break;
            case "Fecha - Nuevas Primero":
                consulta += "a.releaseDate DESC";
                break;
            default:
                consulta = "FROM Album a";
        }

        Query query = em.createQuery(consulta);
        List<Album> resultado = query.getResultList();
        return resultado;
    }

}
