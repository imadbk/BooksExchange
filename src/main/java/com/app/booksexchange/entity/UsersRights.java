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

/**
 *
 * @author ibakli
 */
@Entity
@Table(name = "users_rights")
@NamedQueries({
    @NamedQuery(name = "UsersRights.findAll", query = "SELECT u FROM UsersRights u"),
    @NamedQuery(name = "UsersRights.findByRightId", query = "SELECT u FROM UsersRights u WHERE u.rightId = :rightId")})
public class UsersRights implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "right_id")
    private Integer rightId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    @ManyToOne(optional = false)
    private Profiles profileId;

    public UsersRights() {
    }

    public UsersRights(Integer rightId) {
        this.rightId = rightId;
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Profiles getProfileId() {
        return profileId;
    }

    public void setProfileId(Profiles profileId) {
        this.profileId = profileId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rightId != null ? rightId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsersRights)) {
            return false;
        }
        UsersRights other = (UsersRights) object;
        if ((this.rightId == null && other.rightId != null) || (this.rightId != null && !this.rightId.equals(other.rightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.imad.elseGroup.controller.UsersRights[ rightId=" + rightId + " ]";
    }
    
}
