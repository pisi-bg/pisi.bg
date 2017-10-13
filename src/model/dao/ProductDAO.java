package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.DBManager;
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

	public HashMap<String, ArrayList<Product>> getProductsByAnimal(int animal_id) throws SQLException{
		HashMap<String, ArrayList<Product>> products = new HashMap<>();
		
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT p.product_id as id , p.product_name as name, p.description as description, AVG(r.raiting) as rating,"
														+ " c.category_name as category, a.animal_name as animal, p.unit as unit, p.price_per_unit as price,"
														+ " m.brand_name as brand, m.description as manufacturerDescription"
														+" FROM pisi.product as p"
														+" JOIN pisi.product_category AS c ON (p.product_category_id = c.product_category_id)"
														+" JOIN pisi.manufacturer AS m ON (p.manufacturer_id = m.manifacture_id)"
														+" LEFT JOIN pisi.rating as r ON (p.product_id = r. product_id)"
														+" LEFT JOIN pisi.animal as a ON(p.animal_id = a.animal_id)"
														+ " WHERE p.animal_id = ?"
														+" GROUP BY r.product_id;");
		stmt.setInt(1, animal_id);
		ResultSet rs = stmt.executeQuery();		
		String category = null;
		
		while(rs.next()){
			category = rs.getString("category");
			if(!products.containsKey(category)){
				products.put(category, new ArrayList<>());
			}
			
			// check if there is no rating for this product DB will return null;
			Double rating = new Double(rs.getDouble("rating"));
			if(rating.equals(null)){
				rating = new Double(0);
			}
			
			products.get(category).add(new Product(rs.getLong("id"), rs.getString("name"),
					rs.getString("description"),
					rs.getInt("price"), 
					rs.getString("animal"),
					rs.getString("category"),
					rs.getString("brand"),
					rs.getString("manufacturerDescription"),
					rating,
					rs.getInt("unit"),
					ImageDAO.getInstance().getImagesForProduct(rs.getLong("id"))));
		}
		return products;
	}
	
	// adding product form admin
	public void insertProduct(Product p) throws SQLException{
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("", Statement.RETURN_GENERATED_KEYS);
		
		// TODO UPDATE and set PS !!!!
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		p.setId(rs.getLong(1));
	}
	
	// 
}
