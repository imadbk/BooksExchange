/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.booksexchange.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

/**
 *
 * @author imad.bakli
 */
@Entity
@Table(name = "user_books_wish")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserBooksWish.findAll", query = "SELECT u FROM UserBooksWish u"),
    @NamedQuery(name = "UserBooksWish.findByUserBooksWishId", query = "SELECT u FROM UserBooksWish u WHERE u.userBooksWishId = :userBooksWishId")})
public class UserBooksWish implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_books_wish_id")
    private Integer userBooksWishId;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @ManyToOne(optional = false)
    private Books bookId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public UserBooksWish() {
    }

    public UserBooksWish(Integer userBooksWishId) {
        this.userBooksWishId = userBooksWishId;
    }

    public Integer getUserBooksWishId() {
        return userBooksWishId;
    }

    public void setUserBooksWishId(Integer userBooksWishId) {
        this.userBooksWishId = userBooksWishId;
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
        hash += (userBooksWishId != null ? userBooksWishId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserBooksWish)) {
            return false;
        }
        UserBooksWish other = (UserBooksWish) object;
        if ((this.userBooksWishId == null && other.userBooksWishId != null) || (this.userBooksWishId != null && !this.userBooksWishId.equals(other.userBooksWishId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	JSONObject obj = new JSONObject();
		obj.put("user_books_wish_id", userBooksWishId);
		obj.put("user", new JSONObject(userId));
		obj.put("book", new JSONObject(bookId));
		return obj.toString();
    }
    
}
