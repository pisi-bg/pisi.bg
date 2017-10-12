package model.pojo;

import java.util.List;

public class Product {

	private long id;
	private String name;
	private String description;
	private double price;
	private String category;
	private String subCategory;
	private String brand;
	private String manifactureInfo;
	private int isStock;
	private List<String> images;
	
	
	
	public void setId(long id) {
		this.id = id;
	}
}
