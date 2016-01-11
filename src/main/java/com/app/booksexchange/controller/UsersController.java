package com.app.booksexchange.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
			res.getWriter().write("[");
			List<Users> users = usersService.getUsersAll();

			for (Users us : users) {
				res.getWriter().write(us.toString());
			}
			
			res.getWriter().write("]");

		} catch (IOException e) {
			
		}
	}

}
