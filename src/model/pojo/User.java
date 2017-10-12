package model.pojo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
	private long id;
	private String firstName;
	private String lastName;	
	private String email;
	private String password;
	private boolean isMale;
	private boolean isAdmin;
		
	private List<Product> cart;

	//constructor with email and pass for login
	public User(String email, String pass){
		this.email = email;
		this.password = pass;
	}
	
	//constructor with fullpack info for register
	public User(String firstName, String lastName, String email, String password, boolean isMale) {
		this(email,password);
		this.firstName = firstName;
		this.lastName = lastName;		
		this.isMale = isMale;
		this.cart = new ArrayList<>();
		this.isAdmin = false;
	}
	
	//check if user is admin
	public boolean isAdmin() {
		return isAdmin;
	}

	//return user email
	public String getEmail() {
		return email;
	}

	//return user password
	public String getPassword() {
		return password;
	}
	
	//return true if user is male and false if user is female
	public boolean isMale(){
		return this.isMale;
	}

	//return user first name
	public String getFirstName() {
		return firstName;
	}

	//return user last name
	public String getLastName() {
		return lastName;
	}

	//return unmodifiable collection of user products in cart;
	public List<Product> getCart() {
		return Collections.unmodifiableList(cart);
	}
	
	//set id which is returned by the database
	public void setId(long id) {
		this.id = id;
	}
	

}
