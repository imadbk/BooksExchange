package com.app.booksexchange.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.booksexchange.entity.Books;

@Service("booksService")
@Transactional
public class BooksService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public List<Books> getAllBooks() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("Books.findAll");
		return query.list();
	}

	@Transactional
	public Books getBookById(Integer id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("Books.findByBookId")
				.setParameter("bookId", id);
		return (Books) query.list().get(0);
	}

	@Transactional
	public Books getUserByMail(String isbn) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("Books.findByIsbn")
				.setParameter("isbn", isbn);
		return (query.list().size() > 0 ? (Books) query.list().get(0)
				: null);
	}

	@Transactional
	public void save(Books book) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(book)) {
			session.saveOrUpdate(book);
		}
	}

	@Transactional
	public void delete(Books book) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(book))
			session.delete(book);

	}

	@Transactional
	public void update(Books book) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(book))
			session.update(book);
	}



}
