/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.UsuarioFacade;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import Modelo.Usuario;

/**
 *
 * @author Diego
 */
@Named
@ViewScoped
public class loginController implements Serializable {

    private String user;
    private String password;

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    public void init() {
    }

    public String verifyUser() {
        String navegacion = "privado/reviewer/home.xhtml?faces-redirect=true";
        String navegacionMal = "publico/permisosInsuficientes.xhtml?faces-redirect=true";
        Usuario us = new Usuario();
        try {
            us.setUser(user);
            us.setPassword(password);
            us = usuarioEJB.verificarUsuario(us);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return navegacionMal;
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
        System.out.println("Navegacion " + navegacion);
        return navegacion;
    }

    public String redirectRegister() {
        return "register.xhtml?faces-redirect=true";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
