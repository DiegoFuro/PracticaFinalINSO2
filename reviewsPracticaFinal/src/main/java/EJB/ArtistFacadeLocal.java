/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Artist;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ainoa
 */
@Local
public interface ArtistFacadeLocal {

    void create(Artist artist);

    void edit(Artist artist);

    void remove(Artist artist);

    Artist find(Object id);

    List<Artist> findAll();

    List<Artist> findRange(int[] range);

    int count();
    
}
