package com.app.booksexchange.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.booksexchange.entity.ErrorMessage;
import com.app.booksexchange.entity.UserBooks;
import com.app.booksexchange.services.UserBooksService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/webapi/userbooks")
public class UserBooksController {

	@Resource(name = "userBooksService")
	UserBooksService userBooksService;

	/**
	 * get all UserBooks
	 * 
	 * @param res
	 *            response
	 * @return list of UserBooks
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getUserBooks(HttpServletResponse res) {
		try {
			res.addHeader("Content-Type", "application/json");
			List<UserBooks> userBooks = userBooksService
					.getUserBooks();
			JSONArray mJSONArray = new JSONArray(Arrays.asList(userBooks));
			res.getWriter().write(mJSONArray.toString());

		} catch (IOException e) {

		}
	}

	/**
	 * Add userBooks
	 * 
	 * @QueryParam userBooks (json)
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @return the added userBook
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
			UserBooks userBook = mapper.readValue(jb.toString(),
					UserBooks.class);

			if (userBook != null
					&& userBook.getUserBooksId() == null) {
				userBooksService.save(userBook);
				rep.addHeader("status", "200");
				rep.getWriter().write(userBook.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * update userBooks
	 * 
	 * @QueryParam userBooks (json)
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @return user updated
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json")
	public void updateUserBook(HttpServletRequest req, HttpServletResponse rep)
			throws IOException {

		rep.addHeader("Content-Type", "application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);

			ObjectMapper mapper = new ObjectMapper();
			UserBooks userBook = mapper.readValue(jb.toString(),
					UserBooks.class);

			if (userBook != null
					&& userBook.getUserBooksId() != null) {
				userBooksService.save(userBook);
				rep.addHeader("status", "200");
				rep.getWriter().write(userBook.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Delete UserBooks
	 * 
	 * @param userBook
	 *            userBook Id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{userBookId}", method = RequestMethod.DELETE, consumes = "application/json")
	public void deleteUser(@PathVariable String userBookId,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(userBookId);
			UserBooks userbook = userBooksService
					.getUserBooksById(id);
			rep.addHeader("status", "200");
			userBooksService.delete(userbook);

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
	@RequestMapping(value = "/{userbook}", method = RequestMethod.GET, consumes = "application/json")
	public void getUser(@PathVariable String userbook,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(userbook);
			UserBooks image = userBooksService
					.getUserBooksById(id);
			rep.getWriter().write(image.toString());
			rep.addHeader("status", "200");

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}
	
}
