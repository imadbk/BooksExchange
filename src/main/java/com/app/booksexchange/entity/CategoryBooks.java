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
@Table(name = "category_books")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoryBooks.findAll", query = "SELECT c FROM CategoryBooks c"),
    @NamedQuery(name = "CategoryBooks.findByCategoryBooksId", query = "SELECT c FROM CategoryBooks c WHERE c.categoryBooksId = :categoryBooksId"),
    @NamedQuery(name = "CategoryBooks.findByName", query = "SELECT c FROM CategoryBooks c WHERE c.name = :name")})
public class CategoryBooks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_books_id")
    private Integer categoryBooksId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "book", referencedColumnName = "book_id")
    @ManyToOne(optional = false)
    private Books book;

    public CategoryBooks() {
    }

    public CategoryBooks(Integer categoryBooksId) {
        this.categoryBooksId = categoryBooksId;
    }

    public CategoryBooks(Integer categoryBooksId, String name) {
        this.categoryBooksId = categoryBooksId;
        this.name = name;
    }

    public Integer getCategoryBooksId() {
        return categoryBooksId;
    }

    public void setCategoryBooksId(Integer categoryBooksId) {
        this.categoryBooksId = categoryBooksId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryBooksId != null ? categoryBooksId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoryBooks)) {
            return false;
        }
        CategoryBooks other = (CategoryBooks) object;
        if ((this.categoryBooksId == null && other.categoryBooksId != null) || (this.categoryBooksId != null && !this.categoryBooksId.equals(other.categoryBooksId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	JSONObject obj = new JSONObject();
    	obj.put("category_books_id", categoryBooksId);
    	obj.put("name", name);
    	obj.put("book", new JSONObject(book));
        return obj.toString();
    }
    
}
