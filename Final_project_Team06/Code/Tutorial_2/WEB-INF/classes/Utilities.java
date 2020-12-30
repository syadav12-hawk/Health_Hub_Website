import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;  
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Math; 
import java.time.LocalDate;
import java.util.Iterator;


@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{	
			
			  hm= MySqlDataStoreUtilities.selectUser();

			/*
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\UserDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
				*/
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	
	public ArrayList<OrderItem> updateCustomerOrder(String ordername,String username){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		System.out.println("User Name: "+username);
		System.out.println("Item to be removed"+ordername);
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
			System.out.println("Order ArrayList Before Update"+order);
			Iterator<OrderItem> itr = order.iterator();
			while (itr.hasNext()) {
			  OrderItem oi = itr.next();
			  System.out.println("Order Item"+oi);
			  System.out.println("Item to be removed"+ordername);
			  if (oi.getName().equals(ordername)) {
				 
				order.remove(oi);
				System.out.println("Order ArrayList After Update"+order);
				System.out.println("Item removed");
				break;
			  }
			}
		
		return order;
	}
	
	
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
				
				orderPayments=MySqlDataStoreUtilities.selectOrder();
				/*
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
				*/
			}
			catch(Exception e)
			{
			
			}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=size + 1;
					 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		
		HashMap<String,Product> hm=new HashMap<String,Product>();
		hm=MySqlDataStoreUtilities.selectProduct();
		if(!OrdersHashMap.orders.containsKey(username())){	
					
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equalsIgnoreCase("Doctors")){
			Product product;
			product = hm.get(name);
			OrderItem orderitem = new OrderItem(product.getProdName(), Double.valueOf(product.getProdPrice()), product.getImage(), product.getProdRetailer());
			orderItems.add(orderitem);
		}
		if(type.equalsIgnoreCase("Hospitals")){
			Product product;
			product = hm.get(name);
			OrderItem orderitem = new OrderItem(product.getProdName(), Double.valueOf(product.getProdPrice()), product.getImage(), product.getProdRetailer());
			orderItems.add(orderitem);
		}
		if(type.equalsIgnoreCase("HealthClubs")){
			Product product;
			product = hm.get(name);
			OrderItem orderitem = new OrderItem(product.getProdName(), Double.valueOf(product.getProdPrice()), product.getImage(), product.getProdRetailer());
			orderItems.add(orderitem);
		}
		
		if(type.equalsIgnoreCase("Medicares")){
			Product product;
			product = hm.get(name);
			OrderItem orderitem = new OrderItem(product.getProdName(), Double.valueOf(product.getProdPrice()), product.getImage(), product.getProdRetailer());
			orderItems.add(orderitem);
		}
		

		if(type.equalsIgnoreCase("Insurances")){
			Product product;
			product = hm.get(name);
			OrderItem orderitem = new OrderItem(product.getProdName(), Double.valueOf(product.getProdPrice()), product.getImage(), product.getProdRetailer());
			orderItems.add(orderitem);
		}


		if(type.equals("wearabletechnologies")){
			WearableTechnology wearabletechnology = null;
			wearabletechnology = SaxParserDataStore.wearabletechnologies.get(name);
			OrderItem orderitem = new OrderItem(wearabletechnology.getName(), wearabletechnology.getPrice(), wearabletechnology.getImage(), wearabletechnology.getRetailer());
			orderItems.add(orderitem);
		}

		if(type.equals("accessories")){	
			Accessory accessory = SaxParserDataStore.accessories.get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer());
			orderItems.add(orderitem);
		}
		
	}
	
	
public int getRandomOneZero(){
	if (Math.random()>=0.5)
	{
		return 1;
	}
	else{
		return 0;
	}
}
	// store the payment details for orders
	public void storePayment(int orderId,
		String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,String deliveryMode,LocalDate deliveryDate,
		LocalDate orderDate,double shippingCost,double discount,String storePickUpID,String storePickUpAddress,
		String name1,String Customer_Age1,String Customer_Occupation1,String Review_Rating1,String zipAddress1){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		
		String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				orderPayments=MySqlDataStoreUtilities.selectOrder();
				
				
				//FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\PaymentDetails.txt"));
				//ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				//orderPayments = (HashMap)objectInputStream.readObject();
				
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
			
		
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
			OrderPayment orderpayment = new OrderPayment(orderId,userName,orderName,orderPrice,userAddress,creditCardNo,
			deliveryMode,deliveryDate,
			orderDate,shippingCost,discount,
			storePickUpID,storePickUpAddress);
			
			Random r = new Random();
			Random r1 = new Random();
			Random r2 = new Random();
			Random r3 = new Random();
			Random r4 = new Random();
			
			int low = -5;
			int high = 5;
			int result = r.nextInt(high-low) + low;
			int result1 = r1.nextInt(1000-0) + 0;
			
			
			int result2=getRandomOneZero();
			int result3=getRandomOneZero();
			//int result4=getRandomOneZero();
			//int result2=getRandomOneZero();
			//int result2 = r2.nextInt(1-0)+1 + 0;
			//int result3 = r3.nextInt(1-0)+1 + 0;
			//int result4 = r4.nextInt(1-0)+1 + 0;
			
			System.out.println("Priting Random Values");
			System.out.println("Random N0 :"+result+","+result1+","+result2+","+result3);
			
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
			
			String loginID =userName;
			String Customer_name =name1; //get para
			int Customer_Age =Integer.parseInt(Customer_Age1); //get para
			String Customer_Occupation =Customer_Occupation1;//get para
			String Credit_Card_Number =creditCardNo;
			int Order_ID =orderId;
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String Order_Date = orderDate.format(formatter);
			
			
			//Date orderDate1 = Date.from(orderDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			//String Order_Date =df.format(orderDate1);
			//Date deliveryDate1 = Date.from(deliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			//Date deliveryDate2 = Date.from(deliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			//Date deliveryDate2=deliveryDate1;
			//Date deliveryDate2 = (Date)deliveryDate1.clone();
			int result4;
			if (deliveryDate.compareTo(deliveryDate.plusDays(result))>0){
				result4=0;
			}
			else{
				result4=1;
			}
			
			String Expected_Delivery_Date =deliveryDate.format(formatter);
			System.out.println(deliveryDate);
			//System.out.println(deliveryDate1);
			System.out.println(Expected_Delivery_Date);
			
			String Actual_Delivery_Date = deliveryDate.plusDays(result).format(formatter);//
			//String Actual_Delivery_Date="TEMP";
			//String Product_ID =orderName.replaceAll("\\s", "").toLowerCase();
			String Product_ID =orderName;
			String Product_Name =orderName;
			
			
			HashMap<String,ArrayList<String>> hm=MySqlDataStoreUtilities.getCatManu(Product_ID);//
			System.out.println(""+Product_ID);
			System.out.println(""+hm.containsKey(Product_ID));
			
			ArrayList<String> arr=(ArrayList<String>)hm.get(Product_ID);
			System.out.println(arr);
			System.out.println(hm);
			String Category=arr.get(0);//
			String Manufacturer=arr.get(1);//
			
			int Review_Rating =Integer.parseInt(Review_Rating1);//get para
			String Delivery_Tracking_ID = Integer.toString(result1);//
			String Delivery_Type =deliveryMode;
			String Delivery_Zip_Code =zipAddress1;//get para
			String Transaction_Status =Integer.toString(result2);//
			String Order_Returned = Integer.toString(result3);//
			String Order_Delivered_on_Time = Integer.toString(result4);//
			
System.out.println(
loginID+","+
Customer_name+","+
Customer_Age+","+
Customer_Occupation+","+
Credit_Card_Number+","+
Order_ID+","+	
Order_Date+","+
Expected_Delivery_Date+","+
Actual_Delivery_Date+","+	
Product_ID+","+
Product_Name+","+
Category+","+
Manufacturer+","+
Review_Rating+","+
Delivery_Tracking_ID+","+
Delivery_Type+","+
Delivery_Zip_Code+","+
Transaction_Status+","+
Order_Returned+","+
Order_Delivered_on_Time);

			MySqlDataStoreUtilities.storeTransaction(loginID,Customer_name,Customer_Age,Customer_Occupation,
			Credit_Card_Number,Order_ID,Order_Date,Expected_Delivery_Date,Actual_Delivery_Date,
			Product_ID,Product_Name,Category,Manufacturer,Review_Rating,Delivery_Tracking_ID,
			Delivery_Type,
			Delivery_Zip_Code,Transaction_Status,Order_Returned,Order_Delivered_on_Time);
			
			listOrderPayment.add(orderpayment);	
		
			
			// add order details into file

			try
			{	
			
			MySqlDataStoreUtilities.updateOrder(orderPayments);
			
			/*
			for (ArrayList<OrderPayment> ListOrderPayment : orderPayments.values()){
				for (OrderPayment oi : ListOrderPayment){
					
					System.out.println(oi.getOrderId()+","+oi.getCreditCardNo()
					+","+oi.getUserName()
					+","+oi.getOrderName()
					+","+oi.getOrderPrice()
					+","+oi.getUserAddress()
					+","+oi.getCreditCardNo()
					+","+oi.getDeliveryMode()
					+","+oi.getDeliveryDate()
					+","+oi.getOrderDate()
					+","+oi.getShippingCost()
					+","+oi.getDiscount()
					+","+oi.getStorePickUpID()
					+","+oi.getStorePickUpAddress());
					
					MySqlDataStoreUtilities.insertOrder(oi.getOrderId(),oi.getUserName(),oi.getOrderName(),oi.getOrderPrice(),
					 oi.getUserAddress(),oi.getCreditCardNo(),
					  oi.getDeliveryMode(),oi.getDeliveryDate(),
		              oi.getOrderDate(),oi.getShippingCost(),oi.getDiscount(),
					  oi.getStorePickUpID(),oi.getStorePickUpAddress());
				

				}
			}
			*/
			
		/*
			MySqlDataStoreUtilities.insertOrder(orderId,userName,orderName,orderPrice,userAddress,creditCardNo,deliveryMode,deliveryDate,
		orderDate,shippingCost,discount,storePickUpID,storePickUpAddress);
			
			
			
				FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\PaymentDetails.txt"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	objectOutputStream.writeObject(orderPayments);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();
		*/
			
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}

	
	/* getConsoles Functions returns the Hashmap with all tvs in the store.*/

	public HashMap<String, TV> getTVs(){
			HashMap<String, TV> hm = new HashMap<String, TV>();
			hm.putAll(SaxParserDataStore.tvs);
			return hm;
	}
	
	/* getGames Functions returns the  Hashmap with all Games in the store.*/

	public HashMap<String, SoundSystem> getSoundSystems(){
			HashMap<String, SoundSystem> hm = new HashMap<String, SoundSystem>();
			hm.putAll(SaxParserDataStore.soundsystems);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Phone in the store.*/

	public HashMap<String, Phone> getPhones(){
			HashMap<String, Phone> hm = new HashMap<String, Phone>();
			hm.putAll(SaxParserDataStore.phones);
			return hm;
	}
	
	
		public HashMap<String, Laptop> getLaptops(){
			HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
			hm.putAll(SaxParserDataStore.laptops);
			return hm;
	}
	
		public HashMap<String, VoiceAssistant> getVoiceAssistants(){
			HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();
			hm.putAll(SaxParserDataStore.voiceassistants);
			return hm;
	}
	
		public HashMap<String, WearableTechnology> getWearableTechnologies(){
			HashMap<String, WearableTechnology> hm = new HashMap<String, WearableTechnology>();
			hm.putAll(SaxParserDataStore.wearabletechnologies);
			return hm;
	}
	






    //public String storeReview(String productname,String producttype,String productmaker,String reviewrating,String reviewdate,String  reviewtext,String reatilerpin,String price,String city){
	
	public String storeReview(String productname,String producttype,String productprice,String productmaker,
	String reviewrating,String storeId,String retailercity,String state,String zipcode,String prodSale,
	String userName,String age,String gender,String occupation,String reviewdate,String reviewtext){
				  
	String message=MongoDBDataStoreUtilities.insertReview(productname,producttype,productprice,productmaker,reviewrating,storeId,
	retailercity,state,zipcode,prodSale,userName,age,gender,occupation,reviewdate,reviewtext);
		if(!message.equals("Successfull"))
		{ 
			return "UnSuccessfull";
		}
		else
		{
			HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(productname)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productname, arr);
		}
		ArrayList<Review> listReview = reviews.get(productname);		
		Review review = new Review(productname,producttype,productprice,productmaker,reviewrating,storeId,
	                               retailercity,state,zipcode,prodSale,userName,age,gender,occupation,reviewdate,reviewtext);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successfull";	
		}
	}





	
	
	
	/* getProducts Functions returns the Arraylist of tvs in the store.*/

	public ArrayList<String> getProductsTV(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, TV> entry : getTVs().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of soundsystems in the store.*/

	public ArrayList<String> getProductsSoundSystem(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SoundSystem> entry : getSoundSystems().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of Tablets in the store.*/

	public ArrayList<String> getProductsPhone(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Phone> entry : getPhones().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsLaptop(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptop> entry : getLaptops().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsVoiceAssistant(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VoiceAssistant> entry : getVoiceAssistants().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsWearableTechnology(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, WearableTechnology> entry : getWearableTechnologies().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}


}
