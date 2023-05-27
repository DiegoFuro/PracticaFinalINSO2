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
import javax.ejb.Local;

/**
 *
 * @author Diego
 */
@Local
public interface VoteFacadeLocal {

    void create(Vote votes);

    void edit(Vote votes);

    void remove(Vote votes);

    Vote find(Object id);

    List<Vote> findAll();

    List<Vote> findRange(int[] range);

    int count();
    
    boolean consultVote(Usuario user, Review review);
    
}
