package com.app.booksexchange.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.booksexchange.entity.Users;
import com.app.booksexchange.services.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Resource(name = "usersService")
	UsersService usersService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getUsers(HttpServletResponse res) {
		try {
			List<Users> users = usersService.getUsersAll();
			JSONArray mJSONArray = new JSONArray(Arrays.asList(users));
			res.getWriter().write(mJSONArray.toString());

		} catch (IOException e) {

		}
	}

}
