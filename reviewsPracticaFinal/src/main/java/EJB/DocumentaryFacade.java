/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Documentary;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DocumentaryFacade extends AbstractFacade<Documentary> implements DocumentaryFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentaryFacade() {
        super(Documentary.class);
    }

    @Override
    public List<Documentary> findByGenre(String filter) {
        String consulta = "FROM Documentary d WHERE d.genre=:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", filter);

        List<Documentary> resultado = query.getResultList();
        return resultado;
    }

}
