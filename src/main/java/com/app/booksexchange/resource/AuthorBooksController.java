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
import com.app.booksexchange.entity.AuthorBooks;
import com.app.booksexchange.services.AuthorBooksService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/webapi/authors")
public class AuthorBooksController {

	@Resource(name = "authorBooksService")
	AuthorBooksService authorBooksService;

	/**
	 * get all AuthorBooks
	 * 
	 * @param res
	 *            response
	 * @return list of AuthorBooks
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getAuthorBooks(HttpServletResponse res) {
		try {
			res.addHeader("Content-Type", "application/json");
			List<AuthorBooks> authorBooks = authorBooksService.getAuthorBooks();
			JSONArray mJSONArray = new JSONArray(Arrays.asList(authorBooks));
			res.getWriter().write(mJSONArray.toString());

		} catch (IOException e) {

		}
	}

	/**
	 * Add authorBook
	 * 
	 * @QueryParam authorBook (json)
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @return the added autourBook
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
			AuthorBooks authorBook = mapper.readValue(jb.toString(),
					AuthorBooks.class);

			if (authorBook != null && authorBook.getAuthorBooksId() == null) {
				authorBooksService.save(authorBook);
				rep.addHeader("status", "200");
				rep.getWriter().write(authorBook.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * update authorBook
	 * 
	 * @QueryParam authorBook (json)
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
			AuthorBooks authorBook = mapper.readValue(jb.toString(),
					AuthorBooks.class);

			if (authorBook != null && authorBook.getAuthorBooksId() != null) {
				authorBooksService.save(authorBook);
				rep.addHeader("status", "200");
				rep.getWriter().write(authorBook.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Delete authorBook
	 * 
	 * @param authorBookId
	 *            authorBook Id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{authorBookId}", method = RequestMethod.DELETE, consumes = "application/json")
	public void deleteUser(@PathVariable String authorBookId,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(authorBookId);
			AuthorBooks authorBook = authorBooksService
					.getAuthorBookById(id);
			rep.addHeader("status", "200");
			authorBooksService.delete(authorBook);

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Get authorBook by ID
	 * 
	 * @param authorBookId
	 *            authorBook id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 * @return the user
	 */
	@RequestMapping(value = "/{authorBookId}", method = RequestMethod.GET, consumes = "application/json")
	public void getUser(@PathVariable String authorBookId,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(authorBookId);
			AuthorBooks authorBook = authorBooksService
					.getAuthorBookById(id);
			rep.getWriter().write(authorBook.toString());
			rep.addHeader("status", "200");

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}
}
