import java.sql.*;
import java.util.*;
import java.sql.Date;
import java.time.*;
//import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;

public static void getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamespeed","root","root");							
	}
	catch(Exception e)
	{
	
	}
}


public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,
String deliveryMode,LocalDate deliveryDate,LocalDate orderDate,double shippingCost,double discount,String storePickUpID,String storePickUpAddress)
{
	try
	{
	
		getConnection();
		String delQuery="DELETE FROM customerOrders;";
		String insertIntoCustomerOrderQuery = "INSERT INTO customerorders(orderId,userName,orderName,orderPrice,userAddress,creditCardNo,deliveryMode,deliveryDate,orderDate,shippingCost,discount,storePickUpID,storePickUpAddress) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";	
		
        Statement stmt = conn.createStatement();
		//Statement stmt2 = conn.createStatement();
		stmt.executeUpdate(delQuery);
		
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setString(4,String.valueOf(orderPrice));
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
		
		pst.setString(7,deliveryMode);
		pst.setDate(8,java.sql.Date.valueOf(deliveryDate));
		pst.setDate(9,java.sql.Date.valueOf(orderDate));
		pst.setString(10,String.valueOf(shippingCost));
		pst.setString(11,String.valueOf(discount));
		pst.setString(12,storePickUpID);
		pst.setString(13,storePickUpAddress);
		pst.execute();
	}
	catch(Exception e)
	{
		
	 System.out.println("Error in SQL Query");
	 
	 System.out.println(e.toString());
	}		
}





public static void updateOrder(HashMap<Integer, ArrayList<OrderPayment>> orderPayments)
{
	try
	{
	
		getConnection();
		String delQuery="DELETE FROM customerOrders;";
		
		/*
		String createQuery="CREATE TABLE customerOrders "
							+"(orderId Integer,"
							+"userName varchar(40),"
							+"orderName varchar(40),"
							+"orderPrice varchar(40),"
							+"userAddress varchar(100),"
							+"creditCardNo varchar(40),"
							+"deliveryMode varchar(40),"
							+"deliveryDate DATE,"
							+"orderDate DATE,"
							+"shippingCost varchar(40),"
							+"discount varchar(40),"
							+"storePickUpID varchar(40),"
							+"storePickUpAddress varchar(40)"
							+");";

        */
		Statement stmt = conn.createStatement();
		//Statement stmt2 = conn.createStatement();
		stmt.executeUpdate(delQuery);
		//stmt2.executeUpdate(createQuery);
		
		int count=0;
		//ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
		for (ArrayList<OrderPayment> ListOrderPayment : orderPayments.values()){
		//for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
			count=count+1;
			for (OrderPayment oi : ListOrderPayment){	
				String insertIntoCustomerOrderQuery = "INSERT INTO customerorders(orderId,userName,orderName,orderPrice,userAddress,creditCardNo,deliveryMode,deliveryDate,orderDate,shippingCost,discount,storePickUpID,storePickUpAddress) "
		        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";	
			
				PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
				//set the parameter for each column and execute the prepared statement
				pst.setInt(1,count);
				pst.setString(2,oi.getUserName());
				pst.setString(3,oi.getOrderName());
				pst.setDouble(4,oi.getOrderPrice());
				pst.setString(5,oi.getUserAddress());
				pst.setString(6,oi.getCreditCardNo());
				
				pst.setString(7,oi.getDeliveryMode());
				pst.setDate(8,Date.valueOf(oi.getDeliveryDate()));
				pst.setDate(9,Date.valueOf(oi.getOrderDate()));
				pst.setDouble(10,oi.getShippingCost());
				pst.setDouble(11,oi.getDiscount());
				pst.setString(12,oi.getStorePickUpID());
				pst.setString(13,oi.getStorePickUpAddress());
				pst.execute();
				
			}
			
		}
		
		
	}
	catch(Exception e)
	{
	  System.out.println(e.toString());
	}		
}
















public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from customerorders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			
			System.out.println(rs.getInt("orderId"));
			if(!orderPayments.containsKey(rs.getInt("orderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("orderId"));		
			System.out.println("data is"+rs.getInt("orderId")+orderPayments.get(rs.getInt("orderId")));

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("orderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"),
			rs.getString("deliveryMode"),rs.getDate("deliveryDate").toLocalDate(),rs.getDate("orderDate").toLocalDate(),
			rs.getInt("shippingCost"),rs.getInt("discount"),rs.getString("storePickUpID"),rs.getString("storePickUpAddress"));
			listOrderPayment.add(order);
			System.out.println("data is"+rs.getInt("orderId")+orderPayments.get(rs.getInt("orderId")));
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}





//User
//-------------------------------------------------------------------------------------------------------
public static void insertUser(String username,String password,String usertype,String streetCust,
	String cityCust,String stateCust,double zipCust)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,usertype,streetCust,cityCust,stateCust,zipCust) "
		+ "VALUES (?,?,?,?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,usertype);
		pst.setString(4,streetCust);
		pst.setString(5,cityCust);
		pst.setString(6,stateCust);
		pst.setDouble(7,zipCust);
		
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"),
	         rs.getString("streetCust"),rs.getString("cityCust"),rs.getString("stateCust"),rs.getDouble("zipCust"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}



//Store
//-------------------------------------------------------------------------------------------------------


public static HashMap<Integer,Store> selectStore()
{	
	HashMap<Integer,Store> hm=new HashMap<Integer,Store>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectStoreQuery="select * from  storelocation";
		ResultSet rs = stmt.executeQuery(selectStoreQuery);
		while(rs.next())
		{	Store store = new Store(rs.getInt("StoreId"),rs.getString("StoreAdd"));
			hm.put(rs.getInt("StoreId"), store);
		}
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
	return hm;			
}




//Product
//------------------------------------------------------------------------
public static HashMap<String,Product> selectProduct()
{	
	HashMap<String,Product> hm=new HashMap<String,Product>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectStoreQuery="select * from  products";
		ResultSet rs = stmt.executeQuery(selectStoreQuery);
		while(rs.next())
		{	Product product = new Product(rs.getString("prodName"),rs.getString("prodPrice"),
				rs.getString("prodType"),rs.getString("prodCondition"),rs.getString("prodDiscount"),
				rs.getString("prodRetailer"),rs.getString("prodImg"),rs.getInt("itemCount"),rs.getString("saleInfo"));
				
			hm.put(rs.getString("prodName"), product);
		}
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
	return hm;			
}


public static void updateProduct(String prodName,String prodPrice,String prodType,
String prodCondition,String prodDiscount,String prodRetailer,
String prodImg,int itemCount,String saleInfo)
{
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String deleteProdQuery="DELETE FROM products WHERE prodName='"+prodName+"';";
		stmt.executeUpdate(deleteProdQuery);
		
		
		String insertProdQuery="INSERT INTO products(prodName,prodPrice,prodType,prodCondition,prodDiscount,prodRetailer,prodImg,itemCount,saleInfo) "+
		"VALUES (?,?,?,?,?,?,?,?,?);";
		
		PreparedStatement pst = conn.prepareStatement(insertProdQuery);
		pst.setString(1,prodName);
		pst.setString(2,prodPrice);
		pst.setString(3,prodType);
		pst.setString(4,prodCondition);
		pst.setString(5,prodDiscount);
		pst.setString(6,prodRetailer);
		pst.setString(7,prodImg);
		pst.setInt(8,itemCount);
		pst.setString(9,saleInfo);
		pst.execute();
		
		
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
	
}

public static void deleteProduct(String prodName)
{
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String deleteProdQuery="DELETE FROM products WHERE prodName='"+prodName+"';";
		stmt.executeUpdate(deleteProdQuery);

	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}

}









public static HashMap<String,ArrayList<String>> getCatManu(String Product_ID)
{	
	HashMap<String, ArrayList<String>> hm=new HashMap<String, ArrayList<String>>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectStoreQuery="select prodName,prodType,prodRetailer from  products where prodName="+"'"+Product_ID+"';";
		ResultSet rs = stmt.executeQuery(selectStoreQuery);
		
		
		while(rs.next())
		{	
				
			ArrayList<String> arr = new ArrayList<String>();
			arr.add(rs.getString("prodType"));
			arr.add(rs.getString("prodRetailer"));
			hm.put(rs.getString("prodName"), arr);
			System.out.println(rs.getString("prodName")+","+rs.getString("prodType")+","+rs.getString("prodRetailer"));
			System.out.println(arr);

		}
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
	System.out.println(hm);
	return hm;			
}


			//MySqlDataStoreUtilities.storeTransaction(String loginID,String Customer_name,int Customer_Age,String Customer_Occupation,
			//String Credit_Card_Number,int Order_ID,String Order_Date,String Expected_Delivery_Date,String Actual_Delivery_Date,
			//String Product_ID,String Product_Name,String Category,String Manufacturer,int Review_Rating,String Delivery_Tracking_ID,
			//String Delivery_Type,
			//String Delivery_Zip_Code,String Transaction_Status,String Order_Returned,String Order_Delivered_on_Time);

public static void storeTransaction(String loginID,String Customer_name,int Customer_Age,String Customer_Occupation,
			String Credit_Card_Number,int Order_ID,String Order_Date,String Expected_Delivery_Date,String Actual_Delivery_Date,
			String Product_ID,String Product_Name,String Category,String Manufacturer,int Review_Rating,String Delivery_Tracking_ID,
			String Delivery_Type,
			String Delivery_Zip_Code,String Transaction_Status,String Order_Returned,String Order_Delivered_on_Time)
{
		try
	{	
		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO transactions(loginID,Customer_name,Customer_Age,Customer_Occupation,"+
		"Credit_Card_Number,Order_ID,Order_Date,Expected_Delivery_Date,Actual_Delivery_Date,Product_ID,Product_Name,"+
		"Category,Manufacturer,Review_Rating,Delivery_Tracking_ID,Delivery_Type,Delivery_Zip_Code,Transaction_Status,"+
		"Order_Returned,Order_Delivered_on_Time) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,loginID);
		pst.setString(2,Customer_name);
		pst.setInt(3,Customer_Age);
		pst.setString(4,Customer_Occupation);
		pst.setString(5,Credit_Card_Number);
		pst.setInt(6,Order_ID);
		pst.setString(7,Order_Date);
		pst.setString(8,Expected_Delivery_Date);
		pst.setString(9,Actual_Delivery_Date);
		pst.setString(10,Product_ID);
		pst.setString(11,Product_Name);
		pst.setString(12,Category);
		pst.setString(13,Manufacturer);
		pst.setInt(14,Review_Rating);
		pst.setString(15,Delivery_Tracking_ID);
		pst.setString(16,Delivery_Type);
		pst.setString(17,Delivery_Zip_Code);
		pst.setString(18,Transaction_Status);
		pst.setString(19,Order_Returned);
		pst.setString(20,Order_Delivered_on_Time);
		
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}



	
}	