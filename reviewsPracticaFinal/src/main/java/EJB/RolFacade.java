/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Rol;
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
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    @Override
    public Rol findByName(String rolString) {
        String consulta = "FROM Rol r WHERE r.description=:param1";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", rolString);

        List<Rol> resultado = query.getResultList();
        Rol rol = resultado.get(0);

        return rol;
    }

}
