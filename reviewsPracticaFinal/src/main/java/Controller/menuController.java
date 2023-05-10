/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.MenuFacadeLocal;
import Modelo.Menu;
import Modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Diego
 */
@Named
@SessionScoped
public class menuController implements Serializable {

    @EJB
    private MenuFacadeLocal menuEJB;

    private MenuModel modelo;

    @PostConstruct
    public void init() {
        modelo = obtenerMenu();
    }

    public MenuModel obtenerMenu() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        modelo = new DefaultMenuModel();
        List<Menu> menus = menuEJB.obtainMenusUser(us);

        for (Menu menu : menus) {
            if (menu.getTipo() == 'S') {
                DefaultSubMenu subMenu = DefaultSubMenu.builder()
                        .label(menu.getNombre())
                        .build();

                // Agrega los elementos de menú al submenú
                for (Menu submenuItem : menus) {
                    if (submenuItem.getTipo() == 'I' && submenuItem.getIdMenu_Menu() != null && submenuItem.getIdMenu_Menu().equals(menu)) {
                        DefaultMenuItem item = DefaultMenuItem.builder()
                                .value(submenuItem.getNombre())
                                .url(submenuItem.getUrl())
                                .build();
                        subMenu.getElements().add(item);
                    }
                }

                modelo.getElements().add(subMenu);
            } else if (menu.getTipo() == 'I' && menu.getIdMenu_Menu() == null) {
                DefaultMenuItem item = DefaultMenuItem.builder()
                        .value(menu.getNombre())
                        .url(menu.getUrl())
                        .build();
                modelo.getElements().add(item);
            }
        }

        DefaultMenuItem cerrarSesion = DefaultMenuItem.builder()
                .value("Cerrar Sesión")
                .icon("pi pi-fw pi-sign-out")
                .command("#{menuController.destruirSesion()}")
                .build();
        modelo.getElements().add(cerrarSesion);
        return modelo;
    }

    public String destruirSesion() {
        System.out.println("Navegacion index.xhtml?faces-redirect=true");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }

}
