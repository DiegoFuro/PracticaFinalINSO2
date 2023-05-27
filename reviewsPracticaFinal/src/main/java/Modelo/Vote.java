/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "votes")
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVote;

    @JoinColumn(name = "idReview")
    @ManyToOne
    private Review review;

    @JoinColumn(name = "idUser")
    @ManyToOne
    private Usuario user;

    @Column(name = "Vote")
    private char vote;

    public int getIdVote() {
        return idVote;
    }

    public void setIdVote(int idVote) {
        this.idVote = idVote;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public char getVote() {
        return vote;
    }

    public void setVote(char vote) {
        this.vote = vote;
    }

}
