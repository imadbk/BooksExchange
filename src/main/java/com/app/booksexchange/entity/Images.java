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
@Table(name = "images")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Images.findAll", query = "SELECT i FROM Images i"),
    @NamedQuery(name = "Images.findByImageId", query = "SELECT i FROM Images i WHERE i.imageId = :imageId"),
    @NamedQuery(name = "Images.findByImageUrl", query = "SELECT i FROM Images i WHERE i.imageUrl = :imageUrl"),
    @NamedQuery(name = "Images.findByTitle", query = "SELECT i FROM Images i WHERE i.title = :title")})
public class Images implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "image_id")
    private Integer imageId;
    @Basic(optional = false)
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "title")
    private String title;
    @JoinColumn(name = "user_books_id", referencedColumnName = "user_books_id")
    @ManyToOne(optional = false)
    private UserBooks userBooksId;

    public Images() {
    }

    public Images(Integer imageId) {
        this.imageId = imageId;
    }

    public Images(Integer imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserBooks getUserBooksId() {
        return userBooksId;
    }

    public void setUserBooksId(UserBooks userBooksId) {
        this.userBooksId = userBooksId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Images)) {
            return false;
        }
        Images other = (Images) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	JSONObject obj = new JSONObject();
		obj.put("image_id", imageId);
		obj.put("user_books", new JSONObject(userBooksId));
		obj.put("image_url", imageUrl);
		obj.put("title", title);
		return obj.toString();
    }
    
}
