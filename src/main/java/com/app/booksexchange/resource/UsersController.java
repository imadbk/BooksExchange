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
import com.app.booksexchange.entity.Users;
import com.app.booksexchange.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/webapi/users")
public class UsersController {

	@Resource(name = "usersService")
	UsersService usersService;

	/**
	 * get all users
	 * @param res response
	 * @return list of users
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getUsers(HttpServletResponse res) {
		try {
			res.addHeader("Content-Type", "application/json");
			List<Users> users = usersService.getUsersAll();
			JSONArray mJSONArray = new JSONArray(Arrays.asList(users));
			res.getWriter().write(mJSONArray.toString());

		} catch (IOException e) {

		}
	}

	/**
	 * Add user
	 * @QueryParam user (json)
	 * @param req request
	 * @param rep response
	 * @return the added user 
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
			Users user = mapper.readValue(jb.toString(), Users.class);

			if (user != null && user.getUserId() == null) {
				usersService.save(user);
				rep.addHeader("status", "200");
				rep.getWriter().write(user.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * update user
	 * @QueryParam user (json)
	 * @param req  request
	 * @param rep response
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
			Users user = mapper.readValue(jb.toString(), Users.class);

			if (user != null && user.getUserId() != null) {
				usersService.save(user);
				rep.addHeader("status", "200");
				rep.getWriter().write(user.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Delete user
	 * @param userId  user Id
	 * @param req request 
	 * @param rep response
	 * @throws IOException
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, consumes = "application/json")
	public void deleteUser(@PathVariable String userId, HttpServletRequest req,
			HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {
			
			int id = Integer.parseInt(userId);
			Users user= usersService.getUserById(id);
			rep.addHeader("status", "200");
			usersService.delete(user);
			
		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}
	
	
	/**
	 * Get user by ID
	 * @param userId user id
	 * @param req request
	 * @param rep response
	 * @throws IOException
	 * @return the user 
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, consumes = "application/json")
	public void getUser(@PathVariable String userId, HttpServletRequest req,
			HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {
			
			int id = Integer.parseInt(userId);
			Users user= usersService.getUserById(id);
			rep.getWriter().write(user.toString());
			rep.addHeader("status", "200");
			
		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

}
