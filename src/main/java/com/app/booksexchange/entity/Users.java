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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
		@NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
		@NamedQuery(name = "Users.findByMail", query = "SELECT u FROM Users u WHERE u.mail = :mail"),
		@NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
		@NamedQuery(name = "Users.findByFirstname", query = "SELECT u FROM Users u WHERE u.firstname = :firstname"),
		@NamedQuery(name = "Users.findByLastname", query = "SELECT u FROM Users u WHERE u.lastname = :lastname"),
		@NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
		@NamedQuery(name = "Users.findBySexe", query = "SELECT u FROM Users u WHERE u.sexe = :sexe"),
		@NamedQuery(name = "Users.findByBirthday", query = "SELECT u FROM Users u WHERE u.birthday = :birthday"),
		@NamedQuery(name = "Users.findByAderess", query = "SELECT u FROM Users u WHERE u.aderess = :aderess"),
		@NamedQuery(name = "Users.findByCity", query = "SELECT u FROM Users u WHERE u.city = :city"),
		@NamedQuery(name = "Users.findByCountry", query = "SELECT u FROM Users u WHERE u.country = :country"),
		@NamedQuery(name = "Users.findByPostalCode", query = "SELECT u FROM Users u WHERE u.postalCode = :postalCode"),
		@NamedQuery(name = "Users.findByPoints", query = "SELECT u FROM Users u WHERE u.points = :points"),
		@NamedQuery(name = "Users.findByImageUrl", query = "SELECT u FROM Users u WHERE u.imageUrl = :imageUrl") })
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "user_id")
	private Integer userId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "mail")
	private String mail;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "password")
	private String password;
	@Size(max = 100)
	@Column(name = "firstname")
	private String firstname;
	@Size(max = 100)
	@Column(name = "lastname")
	private String lastname;
	// @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
	// message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the
	// field contains phone or fax number consider using this annotation to
	// enforce field validation
	@Size(max = 15)
	@Column(name = "phone")
	private String phone;
	@Size(max = 10)
	@Column(name = "sexe")
	private String sexe;
	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	@Size(max = 100)
	@Column(name = "aderess")
	private String aderess;
	@Size(max = 100)
	@Column(name = "city")
	private String city;
	@Size(max = 100)
	@Column(name = "country")
	private String country;
	@Size(max = 5)
	@Column(name = "postal_code")
	private String postalCode;
	@Basic(optional = false)
	@NotNull
	@Column(name = "points")
	private int points;
	@Size(max = 200)
	@Column(name = "image_url")
	private String imageUrl;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "buyerId")
	private Collection<Commandes> commandesCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
	private Collection<UserBooks> userBooksCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
	private Collection<UserBooksWish> userBooksWishCollection;

	public Users() {
	}

	public Users(Integer userId) {
		this.userId = userId;
	}

	public Users(Integer userId, String mail, String password, int points) {
		this.userId = userId;
		this.mail = mail;
		this.password = password;
		this.points = points;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAderess() {
		return aderess;
	}

	public void setAderess(String aderess) {
		this.aderess = aderess;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@XmlTransient
	public Collection<Commandes> getCommandesCollection() {
		return commandesCollection;
	}

	public void setCommandesCollection(Collection<Commandes> commandesCollection) {
		this.commandesCollection = commandesCollection;
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

	public void setUserBooksWishCollection(
			Collection<UserBooksWish> userBooksWishCollection) {
		this.userBooksWishCollection = userBooksWishCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userId != null ? userId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Users)) {
			return false;
		}
		Users other = (Users) object;
		if ((this.userId == null && other.userId != null)
				|| (this.userId != null && !this.userId.equals(other.userId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
		obj.put("user_id", userId);
		obj.put("mail", mail);
		obj.put("firstname", firstname);
		obj.put("lastname", lastname);
		obj.put("phone", phone);
		obj.put("sexe", sexe);
		obj.put("birthday", birthday);
		obj.put("adress", aderess);
		obj.put("city", city);
		obj.put("country", country);
		obj.put("postal_code", postalCode);
		obj.put("points", points);
		obj.put("image_url", imageUrl);
		return obj.toString();
	}

}
