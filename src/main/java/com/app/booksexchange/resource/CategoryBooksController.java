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

import com.app.booksexchange.entity.CategoryBooks;
import com.app.booksexchange.entity.ErrorMessage;
import com.app.booksexchange.services.CategoryBooksService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/webapi/categories")
public class CategoryBooksController {

	@Resource(name = "categoryBooksService")
	CategoryBooksService categoryBooksService;

	/**
	 * get all CategoryBooks
	 * 
	 * @param res
	 *            response
	 * @return list of CategoryBooks
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getCategoryBooks(HttpServletResponse res) {
		try {
			res.addHeader("Content-Type", "application/json");
			List<CategoryBooks> CategoryBooks = categoryBooksService
					.getCategoryBooks();
			JSONArray mJSONArray = new JSONArray(Arrays.asList(CategoryBooks));
			res.getWriter().write(mJSONArray.toString());

		} catch (IOException e) {

		}
	}

	/**
	 * Add categoryBooks
	 * 
	 * @QueryParam categoryBooks (json)
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @return the added categoryBooks
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
			CategoryBooks categoryBook = mapper.readValue(jb.toString(),
					CategoryBooks.class);

			if (categoryBook != null
					&& categoryBook.getCategoryBooksId() == null) {
				categoryBooksService.save(categoryBook);
				rep.addHeader("status", "200");
				rep.getWriter().write(categoryBook.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * update categoryBooks
	 * 
	 * @QueryParam categoryBooks (json)
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
			CategoryBooks categoryBook = mapper.readValue(jb.toString(),
					CategoryBooks.class);

			if (categoryBook != null
					&& categoryBook.getCategoryBooksId() != null) {
				categoryBooksService.save(categoryBook);
				rep.addHeader("status", "200");
				rep.getWriter().write(categoryBook.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Delete categoryBooks
	 * 
	 * @param categoryBooks
	 *            categoryBooks Id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{categoryBooks}", method = RequestMethod.DELETE, consumes = "application/json")
	public void deleteUser(@PathVariable String categoryBookId,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(categoryBookId);
			CategoryBooks authorBook = categoryBooksService
					.getAuthorBookById(id);
			rep.addHeader("status", "200");
			categoryBooksService.delete(authorBook);

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Get categoryBooks by ID
	 * 
	 * @param categoryBooks
	 *            authorBook id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 * @return the user
	 */
	@RequestMapping(value = "/{categoryBooksId}", method = RequestMethod.GET, consumes = "application/json")
	public void getUser(@PathVariable String categoryBooksId,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(categoryBooksId);
			CategoryBooks categoryBooks = categoryBooksService
					.getAuthorBookById(id);
			rep.getWriter().write(categoryBooks.toString());
			rep.addHeader("status", "200");

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

}
