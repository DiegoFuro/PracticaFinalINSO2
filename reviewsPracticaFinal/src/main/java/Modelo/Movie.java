/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovie;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "ReleaseDate")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column(name = "ImageURL")
    private String imageURL;
    
    @Column(name = "Genre")
    private String genre;
     

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idMovie;
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + this.rating;
        hash = 97 * hash + Objects.hashCode(this.releaseDate);
        hash = 97 * hash + Objects.hashCode(this.imageURL);
        hash = 97 * hash + Objects.hashCode(this.genre);
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
        final Movie other = (Movie) obj;
        if (this.idMovie != other.idMovie) {
            return false;
        }
        if (this.rating != other.rating) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.imageURL, other.imageURL)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        return true;
    }
    
    

}
