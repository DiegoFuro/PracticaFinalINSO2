/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Album;
import Modelo.Artist;
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
public class ArtistFacade extends AbstractFacade<Artist> implements ArtistFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtistFacade() {
        super(Artist.class);
    }

    @Override
    public List<Artist> findByGenre(String filter) {
        String consulta = "FROM Artist a WHERE a.genre=:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", filter);

        List<Artist> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Artist> findByDate(Date dateSince, Date dateTo) {
        String consulta = "FROM Artist a WHERE a.birthDate > :param1 AND a.birthDate < :param2";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", dateSince);
        query.setParameter("param2", dateTo);

        List<Artist> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Artist> orderBy(String order) {
        String consulta = "FROM Artist a ORDER BY ";

        switch (order) {
            case "Alfabético (A-Z)":
                consulta += "a.name ASC";
                break;
            case "Alfabético (Z-A)":
                consulta += "a.name DESC";
                break;
            case "Menos-Más Oyentes":
                consulta += "a.listeners ASC";
                break;
            case "Más-Menos Oyentes":
                consulta += "a.listeners DESC";
                break;
            default:
                consulta = "FROM Artist a";
        }

        Query query = em.createQuery(consulta);
        List<Artist> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Album> findAlbumsByArtist(Artist artist) {
        String consulta = "FROM Album a WHERE a.idArtist =:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", artist);

        List<Album> resultado = query.getResultList();
        return resultado;
    }

}
