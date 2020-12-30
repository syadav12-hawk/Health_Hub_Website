import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class User implements Serializable{
	private int id;
	private String name;
	private String password;
	private String usertype;
	
	private String streetCust;//
	private String cityCust;//
	private String stateCust;//
	private double zipCust;//
	
	
	public User(String name, String password, String usertype,String streetCust,
	String cityCust,String stateCust,double zipCust) {
		this.name=name;
		this.password=password;
		this.usertype=usertype;
		this.streetCust=streetCust;
		this.cityCust=cityCust;
		this.stateCust=stateCust;
		this.zipCust=zipCust;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	public String getStreetCust() {
		return streetCust;
	}

	public void setStreetCust(String streetCust) {
		this.streetCust = streetCust;
	}	

	public String getCityCust() {
		return cityCust;
	}

	public void setCityCust(String cityCust) {
		this.cityCust = cityCust;
	}		
	

	public String getStateCust() {
		return cityCust;
	}

	public void setStateCust(String stateCust) {
		this.stateCust = stateCust;
	}		

	public double getZipCust() {
		return zipCust;
	}

	public void setZipCust(double zipCust) {
		this.zipCust = zipCust;
	}	
}
