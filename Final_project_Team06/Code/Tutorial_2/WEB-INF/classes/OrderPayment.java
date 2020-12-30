import java.io.IOException;
import java.io.*;
import java.time.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	private int orderId;
	private String userName;
	private String orderName;
	private double orderPrice;
	private String userAddress;
	private String creditCardNo;
	private String deliveryMode;
	private LocalDate deliveryDate;
	
	private LocalDate orderDate;//Will get it from Payment
	private double shippingCost ;//Will Hide from Chekout
	private double discount ; //Will Hide from CheckOut
	private String storePickUpID; //Will get from CheckOut
	private String storePickUpAddress; //Will get from Checkout
	
	
	public OrderPayment(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,
	String deliveryMode,LocalDate deliveryDate,LocalDate orderDate,double shippingCost,double discount,String storePickUpID,
	String storePickUpAddress){
		this.orderId=orderId;
		this.userName=userName;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.userAddress=userAddress;
	 	this.creditCardNo=creditCardNo;
		this.deliveryMode=deliveryMode;
		this.deliveryDate=deliveryDate;
		
		
		this.orderDate=orderDate;//
		this.shippingCost=shippingCost;//
		this.discount=discount;//
		this.storePickUpID=storePickUpID;//
		this.storePickUpAddress=storePickUpAddress;
		}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	public void setDeliveryMode(String deliveryMode){
		this.deliveryMode=deliveryMode;
	}
	
	public String getDeliveryMode(){
		return deliveryMode;
	}
	
	
	public void setDeliveryDate(LocalDate deliveryDate){
		this.deliveryDate=deliveryDate;
	}
	
	public LocalDate getDeliveryDate(){
		return deliveryDate;
	}
	
	
	public void setOrderDate(LocalDate orderDate){
		this.orderDate=orderDate;
	}
	
	public LocalDate getOrderDate(){
		return orderDate;
	}
	

	public void setShippingCost(double shippingCost){
		this.shippingCost=shippingCost;
	}
	
	public double getShippingCost(){
		return shippingCost;
	}


	public void setDiscount(double discount){
		this.discount=discount;
	}
	
	public double getDiscount(){
		return discount;
	}


	public void setStorePickUpID(String storePickUpID){
		this.storePickUpID=storePickUpID;
	}
	
	public String getStorePickUpID(){
		return storePickUpID;
	}


	public void setStorePickUpAddress(String storePickUpAddress){
		this.storePickUpAddress=storePickUpAddress;
	}
	
	public String getStorePickUpAddress(){
		return storePickUpAddress;
	}


}
