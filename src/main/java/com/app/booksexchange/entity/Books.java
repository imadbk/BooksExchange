/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.booksexchange.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.json.JSONObject;

/**
 *
 * @author imadbk
 */
@Entity
@Table(name = "books")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByBookId", query = "SELECT b FROM Books b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "Books.findByIsbn", query = "SELECT b FROM Books b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title"),
    @NamedQuery(name = "Books.findByPublishedDate", query = "SELECT b FROM Books b WHERE b.publishedDate = :publishedDate"),
    @NamedQuery(name = "Books.findByPrice", query = "SELECT b FROM Books b WHERE b.price = :price"),
    @NamedQuery(name = "Books.findByEan", query = "SELECT b FROM Books b WHERE b.ean = :ean"),
    @NamedQuery(name = "Books.findByPublisher", query = "SELECT b FROM Books b WHERE b.publisher = :publisher"),
    @NamedQuery(name = "Books.findByDescription", query = "SELECT b FROM Books b WHERE b.description = :description"),
    @NamedQuery(name = "Books.findByPageCount", query = "SELECT b FROM Books b WHERE b.pageCount = :pageCount"),
    @NamedQuery(name = "Books.findByLangage", query = "SELECT b FROM Books b WHERE b.langage = :langage"),
    @NamedQuery(name = "Books.findByImageUrl", query = "SELECT b FROM Books b WHERE b.imageUrl = :imageUrl")})
public class Books implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "book_id")
    private Integer bookId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Column(name = "published_date")
    @Temporal(TemporalType.DATE)
    private Date publishedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @Size(max = 100)
    @Column(name = "EAN")
    private String ean;
    @Size(max = 200)
    @Column(name = "publisher")
    private String publisher;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Column(name = "page_count")
    private Integer pageCount;
    @Size(max = 50)
    @Column(name = "langage")
    private String langage;
    @Size(max = 255)
    @Column(name = "image_url")
    private String imageUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Collection<CategoryBooks> categoryBooksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
    private Collection<UserBooks> userBooksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
    private Collection<UserBooksWish> userBooksWishCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Collection<AuthorBooks> authorBooksCollection;

    public Books() {
    }

    public Books(Integer bookId) {
        this.bookId = bookId;
    }

    public Books(Integer bookId, String isbn, String title, float price) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getLangage() {
        return langage;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @XmlTransient
    public Collection<CategoryBooks> getCategoryBooksCollection() {
        return categoryBooksCollection;
    }

    public void setCategoryBooksCollection(Collection<CategoryBooks> categoryBooksCollection) {
        this.categoryBooksCollection = categoryBooksCollection;
    }

    @XmlTransient
    public Collection<UserBooks> getUserBooksCollection() {
        return userBooksCollection;
    }

    public void setUserBooksCollection(Collection<UserBooks> userBooksCollection) {
        this.userBooksCollection = userBooksCollection;
    }

    @XmlTransient
    public Collection<UserBooksWish> getUserBooksWishCollection() {
        return userBooksWishCollection;
    }

    public void setUserBooksWishCollection(Collection<UserBooksWish> userBooksWishCollection) {
        this.userBooksWishCollection = userBooksWishCollection;
    }

    @XmlTransient
    public Collection<AuthorBooks> getAuthorBooksCollection() {
        return authorBooksCollection;
    }

    public void setAuthorBooksCollection(Collection<AuthorBooks> authorBooksCollection) {
        this.authorBooksCollection = authorBooksCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	
    	JSONObject obj = new JSONObject();
    	obj.put("book_id", bookId);
    	obj.put("ISBN", isbn);
    	obj.put("title", title);
    	obj.put("published_date", publishedDate);
    	obj.put("price", price);
    	obj.put("EAN", ean);
    	obj.put("publisher", publisher);
    	obj.put("description", description);
    	obj.put("page_count", pageCount);
    	obj.put("langage", langage);
    	obj.put("image_url", imageUrl);
    	
        return obj.toString();
    }
    
}
