/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Review;
import Modelo.Usuario;
import Modelo.Vote;
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
public class VoteFacade extends AbstractFacade<Vote> implements VoteFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoteFacade() {
        super(Vote.class);
    }

    @Override
    public boolean consultVote(Usuario user, Review review) {
        String consulta = "SELECT v.up FROM Vote v WHERE v.user=:param1 and u.review=:param2";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", user);
        query.setParameter("param2", review);

        Integer upValue = (Integer) query.getSingleResult();
        System.out.println("DEVUELVO: " + upValue);
        return upValue == 1;
    }

}
