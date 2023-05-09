package Modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Diego
 */
@Entity
@Table(name = "menus")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMenu;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Tipo")
    private char tipo;

    @Column(name = "Estado")
    private boolean estado;

    @JoinColumn(name = "IdRol")
    @ManyToOne
    private Rol IdRol;

    @JoinColumn(name = "IdMenu_Menu")
    @ManyToOne
    private Menu idMenu_Menu;

    @Column(name = "url")
    private String url;

    public Menu getIdMenu_Menu() {
        return idMenu_Menu;
    }

    public void setIdMenu_Menu(Menu idMenu_Menu) {
        this.idMenu_Menu = idMenu_Menu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idMenu;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + this.tipo;
        hash = 79 * hash + (this.estado ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.IdRol);
        hash = 79 * hash + Objects.hashCode(this.idMenu_Menu);
        hash = 79 * hash + Objects.hashCode(this.url);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (this.idMenu != other.idMenu) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.IdRol, other.IdRol)) {
            return false;
        }
        if (!Objects.equals(this.idMenu_Menu, other.idMenu_Menu)) {
            return false;
        }
        return true;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return IdRol;
    }

    public void setRol(Rol IdRol) {
        this.IdRol = IdRol;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
