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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;

/**
 *
 * @author imadbk
 */
@Entity
@Table(name = "commandes")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Commandes.findAll", query = "SELECT c FROM Commandes c"),
		@NamedQuery(name = "Commandes.findByCommandeId", query = "SELECT c FROM Commandes c WHERE c.commandeId = :commandeId"),
		@NamedQuery(name = "Commandes.findByStatus", query = "SELECT c FROM Commandes c WHERE c.status = :status"),
		@NamedQuery(name = "Commandes.findByNote", query = "SELECT c FROM Commandes c WHERE c.note = :note"),
		@NamedQuery(name = "Commandes.findByCode", query = "SELECT c FROM Commandes c WHERE c.code = :code") })
public class Commandes implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "commande_id")
	private Integer commandeId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "status")
	private String status;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "note")
	private Float note;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "code")
	private String code;
	@JoinColumn(name = "user_books", referencedColumnName = "user_books_id")
	@ManyToOne(optional = false)
	private UserBooks userBooks;
	@JoinColumn(name = "buyer_id", referencedColumnName = "user_id")
	@ManyToOne(optional = false)
	private Users buyerId;

	public Commandes() {
	}

	public Commandes(Integer commandeId) {
		this.commandeId = commandeId;
	}

	public Commandes(Integer commandeId, String status, String code) {
		this.commandeId = commandeId;
		this.status = status;
		this.code = code;
	}

	public Integer getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(Integer commandeId) {
		this.commandeId = commandeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getNote() {
		return note;
	}

	public void setNote(Float note) {
		this.note = note;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UserBooks getUserBooks() {
		return userBooks;
	}

	public void setUserBooks(UserBooks userBooks) {
		this.userBooks = userBooks;
	}

	public Users getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Users buyerId) {
		this.buyerId = buyerId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (commandeId != null ? commandeId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Commandes)) {
			return false;
		}
		Commandes other = (Commandes) object;
		if ((this.commandeId == null && other.commandeId != null)
				|| (this.commandeId != null && !this.commandeId
						.equals(other.commandeId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
		obj.put("commande_id", commandeId);
		obj.put("user_books", userBooks.toString());
		obj.put("buyer", buyerId.toString());
		obj.put("status", status);
		obj.put("note", note);
		obj.put("code", code);
		return obj.toString();
	}

}
