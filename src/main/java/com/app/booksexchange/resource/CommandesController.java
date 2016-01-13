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

import com.app.booksexchange.entity.Commandes;
import com.app.booksexchange.entity.ErrorMessage;
import com.app.booksexchange.services.CommandesService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/webapi/commandes")
public class CommandesController {

	
	@Resource(name = "commandesService")
	CommandesService commandesService;

	/**
	 * get all Commandes
	 * 
	 * @param res
	 *            response
	 * @return list of Commandes
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void getCommandes(HttpServletResponse res) {
		try {
			res.addHeader("Content-Type", "application/json");
			List<Commandes> Commandes = commandesService
					.getCommandes();
			JSONArray mJSONArray = new JSONArray(Arrays.asList(Commandes));
			res.getWriter().write(mJSONArray.toString());

		} catch (IOException e) {

		}
	}

	/**
	 * Add Commandes
	 * 
	 * @QueryParam Commandes (json)
	 * @param req
	 *            request
	 * @param rep
	 *            response
	 * @return the added Commandes
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
			Commandes commande = mapper.readValue(jb.toString(),
					Commandes.class);

			if (commande != null
					&& commande.getCommandeId() == null) {
				commandesService.save(commande);
				rep.addHeader("status", "200");
				rep.getWriter().write(commande.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * update Commandes
	 * 
	 * @QueryParam Commandes (json)
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
			Commandes commande = mapper.readValue(jb.toString(),
					Commandes.class);

			if (commande != null
					&& commande.getCommandeId() != null) {
				commandesService.save(commande);
				rep.addHeader("status", "200");
				rep.getWriter().write(commande.toString());
			}

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

	/**
	 * Delete Commandes
	 * 
	 * @param Commandes
	 *            Commandes Id
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
			Commandes commande = commandesService
					.getCommandeById(id);
			rep.addHeader("status", "200");
			commandesService.delete(commande);

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
	@RequestMapping(value = "/{CommandesId}", method = RequestMethod.GET, consumes = "application/json")
	public void getUser(@PathVariable String CommandesId,
			HttpServletRequest req, HttpServletResponse rep) throws IOException {

		rep.addHeader("Content-Type", "application/json");

		try {

			int id = Integer.parseInt(CommandesId);
			Commandes Commandes = commandesService
					.getCommandeById(id);
			rep.getWriter().write(Commandes.toString());
			rep.addHeader("status", "200");

		} catch (Exception e) {
			rep.addHeader("status", "500");
			rep.getWriter().write(
					new ErrorMessage("Server Error", 500, "").toString());
		}
	}

}
