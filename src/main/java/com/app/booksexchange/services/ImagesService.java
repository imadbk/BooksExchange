package com.app.booksexchange.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.booksexchange.entity.Images;

@Service("imagesService")
public class ImagesService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public List<Images> getImages() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("Images.findAll");
		return query.list();
	}

	@Transactional
	public Images getImageById(Integer id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("Images.findByImageId").setParameter(
				"imageId", id);
		return (Images) query.list().get(0);
	}


	@Transactional
	public void save(Images imageId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(imageId)) {
			session.saveOrUpdate(imageId);
		}
	}

	@Transactional
	public void delete(Images ImagesId) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(ImagesId))
			session.delete(ImagesId);

	}

	
}
