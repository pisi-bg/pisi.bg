package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.db.DBManager;
import model.pojo.Product;

public class ProductDAO {

	private static ProductDAO instance;
	private ProductDAO(){}
	public static synchronized ProductDAO getInstance(){
		if(instance == null){
			instance = new ProductDAO();
		}
		return instance;
	}

	
	public void insertProduct(Product p) throws SQLException{
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("", Statement.RETURN_GENERATED_KEYS);
		
		// TODO SELECT and set PS !!!!
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		p.setId(rs.getLong(1));
	}
}
