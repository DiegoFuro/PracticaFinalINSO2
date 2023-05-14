/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Videogame;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface VideogameFacadeLocal {

    void create(Videogame videogame);

    void edit(Videogame videogame);

    void remove(Videogame videogame);

    Videogame find(Object id);

    List<Videogame> findAll();

    List<Videogame> findRange(int[] range);

    int count();
    
}
