package com.app.booksexchange.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ibakli
 */
@Entity
@Table(name = "profiles")
@NamedQueries({
		@NamedQuery(name = "Profiles.findAll", query = "SELECT p FROM Profiles p"),
		@NamedQuery(name = "Profiles.findByProfileId", query = "SELECT p FROM Profiles p WHERE p.profileId = :profileId"),
		@NamedQuery(name = "Profiles.findByProfileName", query = "SELECT p FROM Profiles p WHERE p.profileName = :profileName"),
		@NamedQuery(name = "Profiles.findByFunction", query = "SELECT p FROM Profiles p WHERE p.function = :function") })
public class Profiles implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "profile_id")
	private Integer profileId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "profile_name")
	private String profileName;
	@Size(max = 45)
	@Column(name = "function")
	private String function;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "profileId")
	private Collection<UsersRights> usersRightsCollection;

	public Profiles() {
	}

	public Profiles(Integer profileId) {
		this.profileId = profileId;
	}

	public Profiles(Integer profileId, String profileName) {
		this.profileId = profileId;
		this.profileName = profileName;
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

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Collection<UsersRights> getUsersRightsCollection() {
		return usersRightsCollection;
	}

	public void setUsersRightsCollection(
			Collection<UsersRights> usersRightsCollection) {
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
		if (!(object instanceof Profiles)) {
			return false;
		}
		Profiles other = (Profiles) object;
		if ((this.profileId == null && other.profileId != null)
				|| (this.profileId != null && !this.profileId
						.equals(other.profileId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.imad.elseGroup.controller.Profiles[ profileId=" + profileId
				+ " ]";
	}

}
