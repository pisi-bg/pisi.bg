package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import model.db.DBManager;
import model.pojo.Client;

public class ClientDAO {
	private static ClientDAO instance;

	private ClientDAO() {
	}

	public static synchronized ClientDAO getInstance() {
		if (instance == null) {
			instance = new ClientDAO();
		}
		return instance;
	}

	// this method insert client info to database
	public void insertClient(Client c) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO pisi.client (first_name,last_name,email,password,gender, isAdmin) VALUES (?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, c.getFirstName());
		ps.setString(2, c.getLastName());
		ps.setString(3, c.getEmail());
		ps.setString(4, c.getPassword());
		if (c.isMale()) {
			ps.setInt(5, 1);
		} else {
			ps.setInt(5, 0);
		}
		if (c.isAdmin()) {
			ps.setInt(6, 1);
		} else {
			ps.setInt(6, 0);
		}
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		c.setId(rs.getLong(1));

	}

	// this method check if client exists in the database
	public boolean clientExist(Client u) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT first_name as name FROM pisi.client WHERE email = ?");
		stmt.setString(1, u.getEmail());
		ResultSet rs = stmt.executeQuery();
		return rs.next();
	}

	// this method checks if this i a valid email
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	// this method checks if this password is right for this email
	public boolean verifiedPassword(Client u) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement stmt = con
				.prepareStatement("SELECT first_name as name FROM pisi.client WHERE email = ? AND password = ?");
		stmt.setString(1, u.getEmail());
		stmt.setString(2, u.getPassword());
		ResultSet rs = stmt.executeQuery();
		return rs.next();
	}

}
