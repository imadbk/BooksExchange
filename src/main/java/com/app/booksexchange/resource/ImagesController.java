package com.app.booksexchange.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.booksexchange.entity.ErrorMessage;
import com.app.booksexchange.entity.Images;
import com.app.booksexchange.services.ImagesService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImagesController {

	@Resource(name = "ImagesService")
	ImagesService imagesService;

	/**
	 * get all Images
	 * 
	 * @param res
	 *            response
	 * @return list of Images
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getCommandes(HttpServletResponse res) {
		try {
			res.addHeader("Content-Type", "application/json");
			List<Images> Images = imagesService
					.getImages();
			JSONArray mJSONArray = new JSONArray(Arrays.asList(Images));
			res.getWriter().write(mJSONArray.toString());

		} catch (IOException e) {

		}
	}

	/**
	 * Add Images
	 * 
	 * @QueryParam Images (json)
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @return the added Image
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	public void addUser(HttpServletRequest req, HttpServletResponse rep)
			throws IOException {

		rep.addHeader("Content-Type", "application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);

			ObjectMapper mapper = new ObjectMapper();
			Images image = mapper.readValue(jb.toString(),
					Images.class);

			if (image != null
					&& image.getImageId() == null) {
				imagesService.save(image);
				rep.addHeader("status", "200");
				rep.getWriter().write(image.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * update Images
	 * 
	 * @QueryParam Images (json)
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @return user updated
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json")
	public void updateUser(HttpServletRequest req, HttpServletResponse rep)
			throws IOException {

		rep.addHeader("Content-Type", "application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);

			ObjectMapper mapper = new ObjectMapper();
			Images image = mapper.readValue(jb.toString(),
					Images.class);

			if (image != null
					&& image.getImageId() != null) {
				imagesService.save(image);
				rep.addHeader("status", "200");
				rep.getWriter().write(image.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Delete Images
	 * 
	 * @param image
	 *            image Id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{Commandes}", method = RequestMethod.DELETE, consumes = "application/json")
	public void deleteUser(@PathVariable String categoryBookId,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(categoryBookId);
			Images image = imagesService
					.getImageById(id);
			rep.addHeader("status", "200");
			imagesService.delete(image);

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Get Commandes by ID
	 * 
	 * @param commande
	 *            commande id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 * @return the user
	 */
	@RequestMapping(value = "/{imageId}", method = RequestMethod.GET, consumes = "application/json")
	public void getUser(@PathVariable String imageId,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(imageId);
			Images image = imagesService
					.getImageById(id);
			rep.getWriter().write(image.toString());
			rep.addHeader("status", "200");

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}
}
