package model.pojo;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Order {

	private long orderId;
	private Client client;
	private LocalDateTime datetime;
	private double discount;

	private double finalPrice;

	private String recieverName;
	private String recieverContact;
	private String recieverAddress;
	private String recieverCity;

	private HashMap<Product, Integer> products;

	// constructor to send info in DB
	public Order(Client client, LocalDateTime datetime, double discount, double finalPrice, String recieverName,
			String recieverContact, String recieverAddress, String recieverCity, HashMap<Product, Integer> products) {
		this.client = client;
		this.datetime = datetime;
		this.discount = discount;
		this.finalPrice = finalPrice;
		this.recieverName = recieverName;
		this.recieverContact = recieverContact;
		this.recieverAddress = recieverAddress;
		this.recieverCity = recieverCity;
		this.products = products;
	}

	// constructor to retrieve info from DB
	public Order(long orderId, Client client, LocalDateTime datetime, double discount, double finalPrice,
			String recieverName, String recieverContact, String recieverAddress, String recieverCity,
			HashMap<Product, Integer> products) {
		this(client, datetime, discount, finalPrice, recieverName, recieverContact, recieverAddress, recieverCity,
				products);
	}

	public void setId(long orderId) {
		this.orderId = orderId;
	}

}
