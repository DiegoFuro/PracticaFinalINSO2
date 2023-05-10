/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Documentary;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DocumentaryFacadeLocal {

    void create(Documentary documentary);

    void edit(Documentary documentary);

    void remove(Documentary documentary);

    Documentary find(Object id);

    List<Documentary> findAll();

    List<Documentary> findRange(int[] range);

    int count();
    
}
