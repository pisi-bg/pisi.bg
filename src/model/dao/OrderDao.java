package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import model.db.DBManager;
import model.pojo.Order;

public class OrderDao {

	private static OrderDao instance;

	private OrderDao() {
	}

	// ala bala not git problemi
	public static OrderDao getInstance(String s) {}
	public static OrderDao getInstance() {
		if (instance == null) {
			instance = new OrderDao();
		}
		return instance;
	}

	public HashSet<Order> getOrdersForClient(long client_id) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT client_id, date FROM orders WHERE client_id = ?");
		ps.setLong(1, client_id);
		ResultSet rs = ps.executeQuery();
		HashSet<Order> orders = new HashSet<>();
		while (rs.next()) {
			// orders.add(new Order(null, null, null));
		}
		return orders;
	}

	// public Order createOrder(Client c, )

	// public void setNewQuantities

}
