import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class Product implements Serializable{
	private int id;
	private String prodName;
	private String prodPrice;
	private String prodType;
	private String prodCondition;//
	private String prodDiscount;//
	private String prodRetailer;//
	private String prodImg;
	private int itemCount;
	private String saleInfo;

	
	
	public Product(String prodName, String prodPrice, String prodType,
	String prodCondition,
	String prodDiscount,String prodRetailer,String prodImg,int itemCount,String saleInfo) {
		this.prodName=prodName;
		this.prodPrice=prodPrice;
		this.prodType=prodType;
		this.prodCondition=prodCondition;
		this.prodDiscount=prodDiscount;
		this.prodRetailer=prodRetailer;
		this.prodImg=prodImg;
		this.itemCount=itemCount;
		this.saleInfo=saleInfo;
		//this.zipCust=zipCust;
	}
	
	
	public Product(){
	}
	

	public String getSaleInfo() {
		return saleInfo;
	}

	public void setSaleInfo(String saleInfo) {
		this.saleInfo = saleInfo;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}


	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	
	
	public String getProdCondition() {
		return prodCondition;
	}

	public void setProdCondition(String prodCondition) {
		this.prodCondition = prodCondition;
	}

	public String getProdDiscount() {
		return prodDiscount;
	}

	public void setProdDiscount(String prodDiscount) {
		this.prodDiscount = prodDiscount;
	}

	public String getProdRetailer() {
		return prodRetailer;
	}

	public void setProdRetailer(String prodRetailer) {
		this.prodRetailer = prodRetailer;
	}


	public String getImage(){
		return prodImg;
	}
	
	public void getImage(String prodImg){
		this.prodImg=prodImg;
	}



}
