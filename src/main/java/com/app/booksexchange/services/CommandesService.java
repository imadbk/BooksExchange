package com.app.booksexchange.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.booksexchange.entity.Commandes;

@Service("commandesService")
public class CommandesService {
	

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public List<Commandes> getCommandes() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("Commandes.findAll");
		return query.list();
	}

	@Transactional
	public Commandes getCommandeById(Integer id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("Commandes.findByCommandeId").setParameter(
				"CommandeId", id);
		return (Commandes) query.list().get(0);
	}


	@Transactional
	public void save(Commandes commandeId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(commandeId)) {
			session.saveOrUpdate(commandeId);
		}
	}

	@Transactional
	public void delete(Commandes CommandesId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(CommandesId))
			session.delete(CommandesId);

	}

}
