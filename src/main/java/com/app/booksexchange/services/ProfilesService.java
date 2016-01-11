package com.app.booksexchange.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.booksexchange.entity.Profiles;

@Service("profileService")
public class ProfilesService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Profiles> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Profiles.findAll");
		return query.list();
	}

	@Transactional
	public Profiles getProfileById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Profiles.findByProfileId")
				.setParameter("profileId", id);
		return (Profiles) query.list().get(0);
	}

	@Transactional
	public Profiles getProfileByRoleName(String role) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Profiles.findByProfileName")
				.setParameter("profileName", role);
		return (Profiles) query.list().get(0);
	}
}
