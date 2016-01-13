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

import com.app.booksexchange.entity.Books;
import com.app.booksexchange.entity.ErrorMessage;
import com.app.booksexchange.services.BooksService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/webapi/books")
public class BooksController {

	@Resource(name = "booksService")
	BooksService booksService;

	/**
	 * get all Books
	 * 
	 * @param res
	 *            response
	 * @return list of Books
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getBooks(HttpServletResponse res) {
		try {
			res.addHeader("Content-Type", "application/json");
			List<Books> Books = booksService.getAllBooks();
			JSONArray mJSONArray = new JSONArray(Arrays.asList(Books));
			res.getWriter().write(mJSONArray.toString());

		} catch (IOException e) {

		}
	}

	/**
	 * Add book
	 * 
	 * @QueryParam book (json)
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @return the added book
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	public void addbook(HttpServletRequest req, HttpServletResponse rep)
			throws IOException {

		rep.addHeader("Content-Type", "application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);

			ObjectMapper mapper = new ObjectMapper();
			Books book = mapper.readValue(jb.toString(), Books.class);

			if (book != null && book.getBookId() == null) {
				booksService.save(book);
				rep.addHeader("status", "200");
				rep.getWriter().write(book.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * update book
	 * 
	 * @QueryParam book (json)
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @return book updated
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json")
	public void updatebook(HttpServletRequest req, HttpServletResponse rep)
			throws IOException {

		rep.addHeader("Content-Type", "application/json");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);

			ObjectMapper mapper = new ObjectMapper();
			Books book = mapper.readValue(jb.toString(), Books.class);

			if (book != null && book.getBookId() != null) {
				booksService.save(book);
				rep.addHeader("status", "200");
				rep.getWriter().write(book.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Delete book
	 * 
	 * @param bookId
	 *            book Id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE, consumes = "application/json")
	public void deletebook(@PathVariable String bookId, HttpServletRequest req,
			HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(bookId);
			Books book = booksService.getBookById(id);
			rep.addHeader("status", "200");
			booksService.delete(book);

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Get book by ID
	 * 
	 * @param bookId
	 *            book id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 * @return the book
	 */
	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET, consumes = "application/json")
	public void getbook(@PathVariable String bookId, HttpServletRequest req,
			HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(bookId);
			Books book = booksService.getBookById(id);
			rep.getWriter().write(book.toString());
			rep.addHeader("status", "200");

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

}
