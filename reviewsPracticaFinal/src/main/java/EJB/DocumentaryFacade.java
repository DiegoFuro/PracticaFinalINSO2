/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Documentary;
import java.util.Date;
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

    @Override
    public List<Documentary> findByDate(Date dateSince, Date dateTo) {

        String consulta = "FROM Documentary d WHERE d.releaseDate > :param1 AND d.releaseDate < :param2";

        Query query = em.createQuery(consulta);
        query.setParameter("param1", dateSince);
        query.setParameter("param2", dateTo);

        List<Documentary> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public List<Documentary> orderBy(String order) {
        String consulta = "FROM Documentary d ORDER BY ";

        switch (order) {
            case "Alfabético (A-Z)":
                consulta += "d.title ASC";
                break;
            case "Alfabético (Z-A)":
                System.out.println("Alfa Descen");
                consulta += "d.title DESC";
                break;
            case "Valoración Ascendente":
                System.out.println("Alfa Descen");
                consulta += "d.rating ASC";
                break;
            case "Valoración Descendente":
                System.out.println("Alfa Descen");
                consulta += "d.rating DESC";
                break;
            case "Fecha - Antiguas Primero":
                System.out.println("Alfa Descen");
                consulta += "d.releaseDate ASC";
                break;
            case "Fecha - Nuevas Primero":
                System.out.println("Alfa Descen");
                consulta += "d.releaseDate DESC";
                break;
            default:
                System.out.println("Todas");
                consulta = "FROM Documentary d";
        }

        Query query = em.createQuery(consulta);
        List<Documentary> resultado = query.getResultList();
        return resultado;
    }

}
