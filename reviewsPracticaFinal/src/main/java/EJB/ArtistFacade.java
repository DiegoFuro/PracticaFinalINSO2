/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Artist;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
