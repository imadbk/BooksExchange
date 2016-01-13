/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.booksexchange.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.json.JSONObject;

/**
 *
 * @author imad.bakli
 */
@Entity
@Table(name = "user_books")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserBooks.findAll", query = "SELECT u FROM UserBooks u"),
    @NamedQuery(name = "UserBooks.findByUserBooksId", query = "SELECT u FROM UserBooks u WHERE u.userBooksId = :userBooksId"),
    @NamedQuery(name = "UserBooks.findByBookState", query = "SELECT u FROM UserBooks u WHERE u.bookState = :bookState"),
    @NamedQuery(name = "UserBooks.findByPoints", query = "SELECT u FROM UserBooks u WHERE u.points = :points"),
    @NamedQuery(name = "UserBooks.findByStatus", query = "SELECT u FROM UserBooks u WHERE u.status = :status")})
public class UserBooks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_books_id")
    private Integer userBooksId;
    @Column(name = "book_state")
    private String bookState;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "points")
    private Float points;
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userBooks")
    private Collection<Commandes> commandesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userBooksId")
    private Collection<Images> imagesCollection;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @ManyToOne(optional = false)
    private Books bookId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public UserBooks() {
    }

    public UserBooks(Integer userBooksId) {
        this.userBooksId = userBooksId;
    }

    public Integer getUserBooksId() {
        return userBooksId;
    }

    public void setUserBooksId(Integer userBooksId) {
        this.userBooksId = userBooksId;
    }

    public String getBookState() {
        return bookState;
    }

    public void setBookState(String bookState) {
        this.bookState = bookState;
    }

    public Float getPoints() {
        return points;
    }

    public void setPoints(Float points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Commandes> getCommandesCollection() {
        return commandesCollection;
    }

    public void setCommandesCollection(Collection<Commandes> commandesCollection) {
        this.commandesCollection = commandesCollection;
    }

    @XmlTransient
    public Collection<Images> getImagesCollection() {
        return imagesCollection;
    }

    public void setImagesCollection(Collection<Images> imagesCollection) {
        this.imagesCollection = imagesCollection;
    }

    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userBooksId != null ? userBooksId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserBooks)) {
            return false;
        }
        UserBooks other = (UserBooks) object;
        if ((this.userBooksId == null && other.userBooksId != null) || (this.userBooksId != null && !this.userBooksId.equals(other.userBooksId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	JSONObject obj = new JSONObject();
		obj.put("user_books_id", userBooksId);
		obj.put("user", new JSONObject(userId));
		obj.put("book", new JSONObject(bookId));
		obj.put("book_status", bookState);
		obj.put("points", points);
		obj.put("status", status);
		return obj.toString();
    }
    
}
