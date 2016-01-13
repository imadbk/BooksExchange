package com.app.booksexchange.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.booksexchange.entity.CategoryBooks;


@Service("categoryBooksService")
public class CategoryBooksService {

	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public List<CategoryBooks> getCategoryBooks() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("CategoryBooks.findAll");
		return query.list();
	}

	@Transactional
	public CategoryBooks getAuthorBookById(Integer id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("CategoryBooks.findByCategoryBooksId").setParameter(
				"categoryBooksId", id);
		return (CategoryBooks) query.list().get(0);
	}


	@Transactional
	public void save(CategoryBooks categoryBooksId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(categoryBooksId)) {
			session.saveOrUpdate(categoryBooksId);
		}
	}

	@Transactional
	public void delete(CategoryBooks categoryBooksId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(categoryBooksId))
			session.delete(categoryBooksId);

	}

}
