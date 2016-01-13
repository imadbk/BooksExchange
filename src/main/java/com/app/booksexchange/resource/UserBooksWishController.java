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
import com.app.booksexchange.entity.UserBooksWish;
import com.app.booksexchange.services.UserBooksWishService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("webapi/userbookswish")
public class UserBooksWishController {

	@Resource(name = "userBooksWishService")
	UserBooksWishService userBooksWishService;

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
			List<UserBooksWish> userBooksWish = userBooksWishService
					.getUserBooksWish();
			JSONArray mJSONArray = new JSONArray(Arrays.asList(userBooksWish));
			res.getWriter().write(mJSONArray.toString());

		} catch (IOException e) {

		}
	}

	/**
	 * Add userBooksWish
	 * 
	 * @QueryParam userBooksWish (json)
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
			UserBooksWish userBookWish = mapper.readValue(jb.toString(),
					UserBooksWish.class);

			if (userBookWish != null
					&& userBookWish.getUserBooksWishId() == null) {
				userBooksWishService.save(userBookWish);
				rep.addHeader("status", "200");
				rep.getWriter().write(userBookWish.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * update userBooksWish
	 * 
	 * @QueryParam userBooksWish (json)
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
			UserBooksWish userBookWish = mapper.readValue(jb.toString(),
					UserBooksWish.class);

			if (userBookWish != null
					&& userBookWish.getUserBooksWishId() != null) {
				userBooksWishService.save(userBookWish);
				rep.addHeader("status", "200");
				rep.getWriter().write(userBookWish.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Delete UserBooksWish
	 * 
	 * @param userBookWish
	 *            userBookWish Id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{userBookWishId}", method = RequestMethod.DELETE, consumes = "application/json")
	public void deleteUser(@PathVariable String userBookWishId,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(userBookWishId);
			UserBooksWish userbookWish = userBooksWishService
					.getUserBooksWishById(id);
			rep.addHeader("status", "200");
			userBooksWishService.delete(userbookWish);

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Get UserBooksWish by ID
	 * 
	 * @param UserBooksWish
	 *            UserBooksWish id
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @throws IOException
	 * @return the user
	 */
	@RequestMapping(value = "/{userbookWish}", method = RequestMethod.GET, consumes = "application/json")
	public void getUser(@PathVariable String userbookWish,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(userbookWish);
			UserBooksWish image = userBooksWishService.getUserBooksWishById(id);
			rep.getWriter().write(image.toString());
			rep.addHeader("status", "200");

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}
}
