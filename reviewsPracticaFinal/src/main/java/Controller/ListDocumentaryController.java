/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.DocumentaryFacadeLocal;
import EJB.MovieFacadeLocal;
import Modelo.Documentary;
import Modelo.Movie;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@SessionScoped
public class ListDocumentaryController implements Serializable {

    private List<Documentary> documentaries;

    private Documentary documentary;

    @EJB
    private DocumentaryFacadeLocal documentaryEJB;

    @PostConstruct
    public void init() {
        documentaries = documentaryEJB.findAll();
    }

    public void view(Documentary documentary) throws IOException {
        System.out.println("documentary: " + documentary.getTitle());
        this.documentary = documentary;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/reviewsPracticaFinal/faces/privado/usuario/documentary.xhtml");
    }

    public List<Documentary> getDocumentaries() {
        return documentaries;
    }

    public void setDocumentaries(List<Documentary> documentaries) {
        this.documentaries = documentaries;
    }

    public Documentary getDocumentary() {
        return documentary;
    }

    public void setDocumentary(Documentary documentary) {
        this.documentary = documentary;
    }

    public DocumentaryFacadeLocal getDocumentaryEJB() {
        return documentaryEJB;
    }

    public void setDocumentaryEJB(DocumentaryFacadeLocal documentaryEJB) {
        this.documentaryEJB = documentaryEJB;
    }
    
    
    
}