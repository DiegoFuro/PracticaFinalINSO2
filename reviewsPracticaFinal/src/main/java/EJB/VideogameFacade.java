/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Videogame;
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
public class VideogameFacade extends AbstractFacade<Videogame> implements VideogameFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VideogameFacade() {
        super(Videogame.class);
    }

    public List<Videogame> findLastGames() {
        Date filterDate = Date.valueOf("2023-01-01");

        String consulta = "FROM Videogame v WHERE v.releaseDate > :param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", filterDate);

        List<Videogame> resultado = query.getResultList();
        return resultado;
    }

    public List<Videogame> findBestVideogames() {
        String consulta = "FROM Videogame v WHERE v.rating > :param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", 90);

        List<Videogame> resultado = query.getResultList();
        return resultado;
    }

    public List<Videogame> findHorrorVideogames() {
        String consulta = "FROM Videogame v WHERE v.genre =:param1 OR v.genre =:param2";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", "Terror");
        query.setParameter("param2", "Zombies");

        List<Videogame> resultado = query.getResultList();
        return resultado;
    }

    public List<Videogame> findAdventureGames() {
        String consulta = "FROM Videogame v WHERE v.genre =:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", "Aventura");

        List<Videogame> resultado = query.getResultList();
        return resultado;
    }

}
