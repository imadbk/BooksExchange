package com.app.booksexchange.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.booksexchange.entity.UserBooks;


@Service("userBooksService")
public class UserBooksService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public List<UserBooks> getUserBooks() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("UserBooks.findAll");
		return query.list();
	}

	@Transactional
	public UserBooks getUserBooksById(Integer id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("UserBooks.findByUserBookId").setParameter(
				"userBookId", id);
		return (UserBooks) query.list().get(0);
	}


	@Transactional
	public void save(UserBooks userBookId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(userBookId)) {
			session.saveOrUpdate(userBookId);
		}
	}

	@Transactional
	public void delete(UserBooks userBookId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(userBookId))
			session.delete(userBookId);

	}
}
