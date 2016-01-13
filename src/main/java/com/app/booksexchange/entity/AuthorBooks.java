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
@Table(name = "author_books")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthorBooks.findAll", query = "SELECT a FROM AuthorBooks a"),
    @NamedQuery(name = "AuthorBooks.findByAuthorBooksId", query = "SELECT a FROM AuthorBooks a WHERE a.authorBooksId = :authorBooksId"),
    @NamedQuery(name = "AuthorBooks.findByName", query = "SELECT a FROM AuthorBooks a WHERE a.name = :name")})
public class AuthorBooks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "author_books_id")
    private Integer authorBooksId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "book", referencedColumnName = "book_id")
    @ManyToOne(optional = false)
    private Books book;

    public AuthorBooks() {
    }

    public AuthorBooks(Integer authorBooksId) {
        this.authorBooksId = authorBooksId;
    }

    public AuthorBooks(Integer authorBooksId, String name) {
        this.authorBooksId = authorBooksId;
        this.name = name;
    }

    public Integer getAuthorBooksId() {
        return authorBooksId;
    }

    public void setAuthorBooksId(Integer authorBooksId) {
        this.authorBooksId = authorBooksId;
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
        hash += (authorBooksId != null ? authorBooksId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AuthorBooks)) {
            return false;
        }
        AuthorBooks other = (AuthorBooks) object;
        if ((this.authorBooksId == null && other.authorBooksId != null) || (this.authorBooksId != null && !this.authorBooksId.equals(other.authorBooksId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	JSONObject obj = new JSONObject();
    	obj.put("name", name);
    	obj.put("book", new JSONObject(book));
    	
        return obj.toString();
    }
    
}
