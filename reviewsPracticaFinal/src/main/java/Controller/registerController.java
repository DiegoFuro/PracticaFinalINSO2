/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.RolFacadeLocal;
import EJB.UsuarioFacadeLocal;
import Modelo.Rol;
import Modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@ViewScoped
public class registerController implements Serializable {

    private String user;
    private String name;
    private String lastName;
    private String password;
    private int idRol;

    private Rol rol;
    private String rolString;

    private List<Rol> rols;

    @EJB
    private UsuarioFacadeLocal userEJB;

    @EJB
    private RolFacadeLocal rolEJB;

    @PostConstruct
    public void init() {
        rols = rolEJB.findAll();
    }

    public String registerUser() {
        Usuario newUser = new Usuario();
        try {
            newUser.setName(name);
            newUser.setLastName(lastName);
            newUser.setUser(user);
            newUser.setPassword(password);
            newUser.setIdRol(rolEJB.findByName(rolString));
            if (!checkNameNumbers(newUser.getName())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error en el usuario", "El Nombre no puede contener números"));
                return "";
            } else if(!checkUser(newUser.getUser())){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error en el user", "El Nombre de usuario solo debe tener letra, números y _"));
                return "";
            }else {
                userEJB.create(newUser);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro correctamente", "Se registro"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "index.xhtml?faces-redirect=true";
    }
    
     public List<String> getRolList() {
        Set<String> genres = new HashSet<>();
        for (Rol rol : rols) {
            genres.add(rol.getDescription());
        }
        return new ArrayList<>(genres);
    }

    public boolean checkNameNumbers(String name) {
        return !name.matches(".*\\d.*");
    }
    
    public boolean checkUser(String name) {
        char[] letters = name.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            String letterStr = String.valueOf(letters[i]);
            if(!letterStr.matches("[a-zA-Z0-9_]")){
                return false;
            }
        }
        return true;    }

    public String getRolString() {
        return rolString;
    }

    public void setRolString(String rolString) {
        this.rolString = rolString;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public List<Rol> getRols() {
        return rols;
    }

    public void setRols(List<Rol> rols) {
        this.rols = rols;
    }

    public UsuarioFacadeLocal getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(UsuarioFacadeLocal userEJB) {
        this.userEJB = userEJB;
    }

    public RolFacadeLocal getRolEJB() {
        return rolEJB;
    }

    public void setRolEJB(RolFacadeLocal rolEJB) {
        this.rolEJB = rolEJB;
    }
}
