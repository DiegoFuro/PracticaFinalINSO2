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

    private String usuario;
    private String contraseña;

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    public void init() {
        // implementar la lógica de inicialización si es necesario
    }

    public String verificarUsuario() {
        String navegacion = "home.xhtml?faces-redirect=true";
        String navegacionMal = "publico/permisosInsuficientes.xhtml?faces-redirect=true";
        Usuario us = new Usuario();
        try {
            us.setUser(usuario);
            us.setPassword(contraseña);
            us = usuarioEJB.verificarUsuario(us);
        } catch (Exception e) {
            System.out.println("Error");
            return navegacionMal;
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
        System.out.println("Navegacion " + navegacion);
        return navegacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
