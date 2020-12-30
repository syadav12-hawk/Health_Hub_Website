import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String productName;
	private String producttype;
	private String productprice;
	private String productmaker;
	private String reviewrating;
	private String storeId;
	private String retailercity;//
	private String state;
	private String zipcode;
	private String prodSale;
	private String userName;
	private String age;
	private String gender;
	private String reviewdate;
	private String reviewtext;
	private String occupation;


	//public Review (String productName,String userName,String productType,String productMaker,String reviewRating,String reviewDate,String reviewText,String retailerpin,String price,String retailercity){
	public Review (String productname,String producttype,String productprice,String productmaker,
	String reviewrating,String storeId,String retailercity,String state,String zipcode,String prodSale,
	String userName,String age,String gender,String occupation,String reviewdate,String reviewtext){
		
		this.productName=productName;//
		this.producttype=producttype;//
		this.productprice=productprice;//
		this.productmaker=productmaker;//
	 	this.reviewrating=reviewrating;//
		this.storeId=storeId;//
	 	this.retailercity=retailercity;//
		this.state=state;//
		this.zipcode= zipcode;//
		this.prodSale= prodSale;//
		this.userName= userName;//
		this.age= age;//
		this.gender= gender;
		this.occupation= occupation;//
		this.reviewdate= reviewdate;//
		this.reviewtext= reviewtext;//
		

	}

/*
	public Review(String productName, String retailerpin, String reviewRating, String reviewText) {
       this.productName = productName;
       this.retailerpin = retailerpin;
       this.reviewRating = reviewRating;
       this.reviewText = reviewText;
    }
*/
	public void setOccupation(String occupation) {
		this.occupation=occupation;
	}

	public String getOccupation() {
		return occupation;
	}



	public void setGender(String gender) {
		this.gender=gender;
	}

	public String getGender() {
		return gender;
	}


	public void setAge(String age) {
		this.age=age;
	}

	public String getAge() {
		return age;
	}
	




	public void setProdSale(String prodSale) {
		this.prodSale=prodSale;
	}

	public String getProdSale() {
		return prodSale;
	}


	
	public void setZipcode(String zipcode) {
		this.zipcode=zipcode;
	}

	public String getZipcode() {
		return zipcode;
	}





	public void setState(String state) {
		this.state=state;
	}

	public String getState() {
		return state;
	}



	public void setRetailercity(String retailercity) {
		this.retailercity=retailercity;
	}

	public String getRetailercity() {
		return retailercity;
	}

	public void setStoreId(String storeId) {
		this.storeId=storeId;
	}

	public String getStoreId() {
		return storeId;
	}


	public void setProductName(String productName) {
		this.productName=productName;
	}

	public String getProductName() {
		return productName;
	}
	
	public void setUserName(String userName) {
		this.userName=userName;
	}
	
	public String getUserName() {
		return userName;
	}



	public String getProductType() {
		return producttype;
	}

	public void setProductType(String producttype) {
		this.producttype = producttype;
	}

	public String getProductMaker() {
		return productmaker;
	}

	public void setProductMaker(String productmaker) {
		this.productmaker = productmaker;
	}

	public void setReviewRating(String reviewrating) {
		this.reviewrating=reviewrating;
	}

	public String getReviewRating() {
		return reviewrating;
	}

	public String getReviewText() {
		return reviewtext;
	}
	public void setReviewText(String reviewtext) {
		this.reviewtext = reviewtext;
	}



	public String getReviewDate() {
		return reviewdate;
	}

	public void setReviewDate(String reviewdate) {
		this.reviewdate = reviewdate;
	}
    

	
	
	public String getRetailerCity() {
		return retailercity;
	}

	public void setRetailerCity(String retailercity) {
		this.retailercity = retailercity;
	}
	
	public String getProductprice() {
		return productprice;
	}

	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}

}
