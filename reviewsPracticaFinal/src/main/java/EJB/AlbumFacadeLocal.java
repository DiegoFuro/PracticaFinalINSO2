/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Modelo.Album;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ainoa
 */
@Local
public interface AlbumFacadeLocal {

    void create(Album album);

    void edit(Album album);

    void remove(Album album);

    Album find(Object id);

    List<Album> findAll();

    List<Album> findRange(int[] range);

    int count();

    List<Album> findByGenre(String filter);

    List<Album> findByDate(Date dateSince, Date dateTo);

    List<Album> orderBy(String order);

}
