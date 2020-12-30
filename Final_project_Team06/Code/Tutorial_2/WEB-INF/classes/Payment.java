import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.time.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String userName;
		String storePickUpID;
	    String storePickUpAddress;


		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		
		HttpSession session = request.getSession(true);
		userName=(String)session.getAttribute("username");
		
		//request.getSession().setAttribute("currentUser", userName);
		// get the payment details like credit card no address processed from cart servlet	

		//String userAddress=request.getParameter("userAddress");
		String name=request.getParameter("name");//
		String Customer_Age=request.getParameter("Customer_Age");//
		String Customer_Occupation=request.getParameter("Customer_Occupation");//
		String Review_Rating=request.getParameter("Review_Rating");//
		
		String streetAddress=request.getParameter("streetAddress");
		String cityAddress=request.getParameter("cityAddress");
		String stateAddress=request.getParameter("stateAddress");
		String zipAddress=request.getParameter("zipAddress");
		String creditCardNo=request.getParameter("creditCardNo");
		String deliveryMode=request.getParameter("deliveryMode");
		
		System.out.println("Priting the Delivery Mode "+ deliveryMode);
		
		

		
		
		
		double shippingCost=Double.parseDouble(request.getParameter("shippingCost"));
		double discount=Double.parseDouble(request.getParameter("discount"));
		
		
		
		if (deliveryMode.equals("PickUP")){
			int StoreId=Integer.parseInt(request.getParameter("pickUpAdr"));
			HashMap<Integer,Store> hm=new HashMap<Integer,Store>();
			hm=MySqlDataStoreUtilities.selectStore();
			Store store=(Store)hm.get(StoreId);
			storePickUpAddress=store.getStoreAdd();
			storePickUpID=String.valueOf(store.getStoreId());
		}
		else{
			storePickUpID="Home Delivery";
			storePickUpAddress="Home Delivery";
		}
		//String storePickUpAddress=pickUpAdr;
		String userAddress=name+",\n"+streetAddress+",\n"+cityAddress+",\n"+stateAddress+",\n"+zipAddress;
		//System.out.print("the user address is" +userAddress);
		//System.out.print(creditCardNo);
		System.out.print("Store ID is" +storePickUpID);
		System.out.print("Store Address is" +storePickUpAddress);
		
		LocalDate orderDate = LocalDate.now();
        System.out.println("Current Date: " + orderDate);
        LocalDate deliveryDate = orderDate.plusDays(14);
        System.out.println("Date after Increment: " + deliveryDate);
		
		
		/*
		Date todayDate = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String date = df.format(todayDate.plusDays(5));
		System.out.println(date);
		*/
		HashMap<String,Product> phm=new HashMap<String,Product>();
		phm=MySqlDataStoreUtilities.selectProduct();
		
		
		
		if(!userAddress.isEmpty() && !creditCardNo.isEmpty() )
		{
			//Random rand = new Random(); 
			//int orderId = rand.nextInt(100);
			int orderId=utility.getOrderPaymentSize()+1;

			//iterate through each order

			for (OrderItem oi : utility.getCustomerOrders())
			{

				//set the parameter for each column and execute the prepared statement
				String prodName=String.valueOf(oi.getName());
				//prodName=prodName.replaceAll("\\s", "");
				Product product=(Product)phm.get(prodName);
				discount=Double.parseDouble(product.getProdDiscount());

				utility.storePayment(orderId,userName,oi.getName(),oi.getPrice(),userAddress,creditCardNo,deliveryMode,deliveryDate,
				orderDate,shippingCost,discount,storePickUpID,storePickUpAddress,
				name,Customer_Age,Customer_Occupation,Review_Rating,zipAddress);
				
				System.out.println(" "+orderId+","+userName+","+oi.getName());
			}

			//remove the order details from cart after processing
			
			OrdersHashMap.orders.remove(utility.username());	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			
			pw.print("<h2>Your Order");
			pw.print("&nbsp&nbsp");  
			pw.print("is stored ");
			pw.print("<br>Your Order No is "+(orderId));
			if(deliveryMode.equals("PickUP")){
				//pw.print("<br>Please pick up your order from our "+storePickUpAddress+" office on"+deliveryDate+".");	
			}
			else{
				pw.print("<br>Your order will be delivered to Your Addrress: '"+userAddress+"' by "+deliveryDate+".");
			}	
		
			pw.print("</h2></div></div></div>");
			
			


				
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
