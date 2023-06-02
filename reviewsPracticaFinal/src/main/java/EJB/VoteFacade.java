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
        String consulta = "SELECT COUNT(v) FROM Vote v WHERE v.user = :usuario AND v.review = :review AND v.vote = 'U'";
        Query query = em.createQuery(consulta);

        query.setParameter("usuario", user);
        query.setParameter("review", review);
        Long count = (Long) query.getSingleResult();
        System.out.println("COUNT: " + count);
        return count > 0;
    }

    @Override
    public boolean consultVote2(Usuario user, Review review) {
        String consulta = "SELECT COUNT(v) FROM Vote v WHERE v.user = :usuario AND v.review = :review AND v.vote = 'D'";
        Query query = em.createQuery(consulta);

        query.setParameter("usuario", user);
        query.setParameter("review", review);
        Long count = (Long) query.getSingleResult();
        System.out.println("COUNT: " + count);
        return count > 0;
    }

    @Override
    public boolean deleteUserVoteUp(Review review, Usuario us) {
        String consulta = "DELETE FROM Vote v WHERE v.user = :usuario AND v.review = :review AND v.vote = 'U'";

        Query query = em.createQuery(consulta);

        query.setParameter("usuario", us);
        query.setParameter("review", review);

        int rowsAffected = query.executeUpdate();

        return rowsAffected > 0;
    }

    @Override
    public boolean deleteUserVoteDown(Review review, Usuario us) {
        String consulta = "DELETE FROM Vote v WHERE v.user = :usuario AND v.review = :review AND v.vote = 'D'";

        Query query = em.createQuery(consulta);

        query.setParameter("usuario", us);
        query.setParameter("review", review);

        int rowsAffected = query.executeUpdate();

        return rowsAffected > 0;
    }

    @Override
    public void removeVotes(Review review) {
        System.out.println("BORRAR VOTOS " + review.getTitle());
        String consulta = "DELETE FROM Vote v WHERE  v.review = :review";

        Query query = em.createQuery(consulta);
        query.setParameter("review", review);
        
        query.executeUpdate();

        return;
    }

}
