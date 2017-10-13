package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ClientDAO;
import model.pojo.Client;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// this method check if login form is correctly filled and then check if
	// client exist in the database
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (!ClientDAO.isValidEmailAddress(email)) {
			response.getWriter().append("Invalid email");
			return;
		}
		if (password.isEmpty()) {
			response.getWriter().append("Empty password");
			return;
		}

		Client u = new Client(email, password);
		try {
			if (ClientDAO.getInstance().clientExist(u)) {
				if (ClientDAO.getInstance().verifiedPassword(u)) {
					response.getWriter().append("Login complete");

					// TODO update session to remain logged in

				} else {
					response.getWriter().append("Wrong passwod");
				}
			} else {
				response.getWriter().append("client not exist");
			}
		} catch (SQLException e) {

		}

	}

}
