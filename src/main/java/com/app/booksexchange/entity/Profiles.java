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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author imad.bakli
 */
@Entity
@Table(name = "profiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profiles.findAll", query = "SELECT p FROM Profiles p"),
    @NamedQuery(name = "Profiles.findByProfileId", query = "SELECT p FROM Profiles p WHERE p.profileId = :profileId"),
    @NamedQuery(name = "Profiles.findByProfileName", query = "SELECT p FROM Profiles p WHERE p.profileName = :profileName"),
    @NamedQuery(name = "Profiles.findByProfileFunction", query = "SELECT p FROM Profiles p WHERE p.profileFunction = :profileFunction")})
public class Profiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "profile_name")
    private String profileName;
    @Column(name = "profile_function")
    private String profileFunction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private Collection<UsersRights> usersRightsCollection;

    public Profiles() {
    }

    public Profiles(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileFunction() {
        return profileFunction;
    }

    public void setProfileFunction(String profileFunction) {
        this.profileFunction = profileFunction;
    }

    @XmlTransient
    public Collection<UsersRights> getUsersRightsCollection() {
        return usersRightsCollection;
    }

    public void setUsersRightsCollection(Collection<UsersRights> usersRightsCollection) {
        this.usersRightsCollection = usersRightsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profileId != null ? profileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profiles)) {
            return false;
        }
        Profiles other = (Profiles) object;
        if ((this.profileId == null && other.profileId != null) || (this.profileId != null && !this.profileId.equals(other.profileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.booksexchange.Profiles[ profileId=" + profileId + " ]";
    }
    
}
