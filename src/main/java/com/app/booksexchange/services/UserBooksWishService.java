package com.app.booksexchange.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.booksexchange.entity.UserBooksWish;

@Service("userBooksWishService")
public class UserBooksWishService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public List<UserBooksWish> getUserBooksWish() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("UserBooksWish.findAll");
		return query.list();
	}

	@Transactional
	public UserBooksWish getUserBooksWishById(Integer id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("UserBooksWish.findByUserBookWishId").setParameter(
				"userBookWishId", id);
		return (UserBooksWish) query.list().get(0);
	}


	@Transactional
	public void save(UserBooksWish userBookWishId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(userBookWishId)) {
			session.saveOrUpdate(userBookWishId);
		}
	}

	@Transactional
	public void delete(UserBooksWish userBookWishId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(userBookWishId))
			session.delete(userBookWishId);

	}
}
