package model.pojo;

import java.util.List;

public class Product {

	private long id;
	private String name;
	private String description;
	private double price;
	private String animal;
	private String category;
	private String brand;
	private String manifactureInfo;
	private double rating;
	private int isStock;
	private List<String> images;
	
	

	public Product(long id, String name, String description, double price, String animal, String category,
			String brand, String manifactureInfo, double rating, int isStock, List<String> images) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.animal = animal;
		this.category = category;
		this.brand = brand;
		this.manifactureInfo = manifactureInfo;
		this.rating = rating;
		this.isStock = isStock;
		this.images = images;
	}



	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.description+ " " + this.animal + " " + this.category;
	}
}
