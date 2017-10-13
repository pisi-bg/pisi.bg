package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import model.DBManager;
import model.pojo.User;

public class UserDAO {
	private static UserDAO instance;
	private UserDAO(){}
	public static synchronized UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	//TODO insert into favorite collection
	public void insertFavorite(long product_ID){
		
	}
	
	//TODO remove from favorite collection and from DB
	public void removeFavorite(long product_ID){
		
	}
	
	
	//TODO return favorites products for this user
	public List getFavorites(long user_id){
		return null;
	}
	
	// this method insert user info to database
	public void insertUser(User u) throws SQLException{
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("INSERT INTO pisi.client (first_name,last_name,email,password,gender, isAdmin) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, u.getFirstName());
		ps.setString(2, u.getLastName());
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getPassword());
		if(u.isMale()){
			ps.setInt(5, 1);
		}else {
			ps.setInt(5, 0);
		}
		if(u.isAdmin()){
			ps.setInt(6, 1);
		}else {
			ps.setInt(6, 0);
		}
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		u.setId(rs.getLong(1));
		
	}
	
	//this method check if user exists in the database
	public boolean userExist(User u) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT first_name as name FROM pisi.client WHERE email = ?");
		stmt.setString(1, u.getEmail());
		ResultSet rs = stmt.executeQuery();		
		return rs.next();
	}
	
	//this method checks if this i a valid email 
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
	
	//this method checks if this password is right for this email
	public boolean verifiedPassword(User u) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT first_name as name FROM pisi.client WHERE email = ? AND password = ?");
		stmt.setString(1, u.getEmail());
		stmt.setString(2, u.getPassword());
		ResultSet rs = stmt.executeQuery();		
		return rs.next();
	}
	
	public User getUser(String email){
		
	}
	
}
