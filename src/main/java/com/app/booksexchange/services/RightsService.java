package com.app.booksexchange.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.booksexchange.entity.UsersRights;



@Service("rightsService")
@Transactional
public class RightsService {

	@Resource(name = "usersService")
	UsersService userService;
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<UsersRights> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UsersRights.findAll");
		return query.list();
	}

	public UsersRights getRightById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("UsersRights.findByRightId")
				.setParameter("rightId", id);
		return (UsersRights) query.list().get(0);
	}

	@SuppressWarnings("unchecked")
	public List<UsersRights> getUserRights(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT u FROM UsersRights u WHERE u.userId = " + userId );
		return query.list();
	}

	public boolean isAlreadyUserRight(UsersRights right){
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT u FROM UsersRights u WHERE u.userId = " + right.getUser().getUserId() +" and u.profileId = " + right.getProfile().getProfileId() );
		return query.list().size() > 0;
	}
	public void add(UsersRights right) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(right) && !isAlreadyUserRight(right))
			session.save(right);
	}

	public void delete(UsersRights right) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(right))
			session.delete(right);
	}

	public void update(UsersRights right) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(right))
			session.update(right);
	}
}
