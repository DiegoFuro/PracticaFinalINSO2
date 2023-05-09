/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.TvShow;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface TvShowFacadeLocal {

    void create(TvShow tvShow);

    void edit(TvShow tvShow);

    void remove(TvShow tvShow);

    TvShow find(Object id);

    List<TvShow> findAll();

    List<TvShow> findRange(int[] range);

    int count();
    
}
