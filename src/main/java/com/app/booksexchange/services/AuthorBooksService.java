package com.app.booksexchange.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.booksexchange.entity.AuthorBooks;
import com.app.booksexchange.entity.Books;

@Service("authorBooksService")
public class AuthorBooksService {

	@Resource(name = "booksService")
	BooksService booksService;
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public List<AuthorBooks> getAuthorBooks() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("AuthorBooks.findAll");
		return query.list();
	}

	@Transactional
	public AuthorBooks getAuthorBookById(Integer id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("AuthorBooks.findByAuthorBooksId").setParameter(
				"authorBooksId", id);
		return (AuthorBooks) query.list().get(0);
	}


	@Transactional
	public void save(AuthorBooks authorBooksId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(authorBooksId)) {
			session.saveOrUpdate(authorBooksId);
		}
	}

	@Transactional
	public void delete(AuthorBooks authorBooksId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(authorBooksId))
			session.delete(authorBooksId);

	}

	@Transactional
	public void update(AuthorBooks authorBooks) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(authorBooks) && !StringUtils.isEmpty(authorBooks.getAuthorBooksId())){
			if(getAuthorBookById(authorBooks.getAuthorBooksId()).getBook().getBookId() != authorBooks.getBook().getBookId()){
				Books book = booksService.getBookById(authorBooks.getBook().getBookId());
				if(book != null){
					authorBooks.setBook(book);
				}
			}else {
				Books book = getAuthorBookById(authorBooks.getAuthorBooksId()).getBook();
				authorBooks.setBook(book);
			}
			session.update(authorBooks);
		}
	}

}
