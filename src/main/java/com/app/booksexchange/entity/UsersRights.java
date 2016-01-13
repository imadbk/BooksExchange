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

/**
 *
 * @author imad.bakli
 */
@Entity
@Table(name = "users_rights")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersRights.findAll", query = "SELECT u FROM UsersRights u"),
    @NamedQuery(name = "UsersRights.findByUsersRightsId", query = "SELECT u FROM UsersRights u WHERE u.usersRightsId = :usersRightsId")})
public class UsersRights implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "users_rights_id")
    private Integer usersRightsId;
    @JoinColumn(name = "profile", referencedColumnName = "profile_id")
    @ManyToOne(optional = false)
    private Profiles profile;
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users user;

    public UsersRights() {
    }

    public UsersRights(Integer usersRightsId) {
        this.usersRightsId = usersRightsId;
    }

    public Integer getUsersRightsId() {
        return usersRightsId;
    }

    public void setUsersRightsId(Integer usersRightsId) {
        this.usersRightsId = usersRightsId;
    }

    public Profiles getProfile() {
        return profile;
    }

    public void setProfile(Profiles profile) {
        this.profile = profile;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersRightsId != null ? usersRightsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersRights)) {
            return false;
        }
        UsersRights other = (UsersRights) object;
        if ((this.usersRightsId == null && other.usersRightsId != null) || (this.usersRightsId != null && !this.usersRightsId.equals(other.usersRightsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.booksexchange.UsersRights[ usersRightsId=" + usersRightsId + " ]";
    }
    
}
