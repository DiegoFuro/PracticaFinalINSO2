/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Menu;
import Modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "reviews")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    
    public List<Menu> obtainMenusUser(Usuario us) {
        String jpql = "SELECT m FROM Menu m WHERE m.IdRol = :IdRol";
        TypedQuery<Menu> query = em.createQuery(jpql, Menu.class);
        query.setParameter("IdRol", us.getIdRol());
        List<Menu> menus = query.getResultList();
        return menus;
    }
    
}
