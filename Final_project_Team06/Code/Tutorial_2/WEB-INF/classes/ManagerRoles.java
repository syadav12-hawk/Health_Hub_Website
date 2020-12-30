import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/ManagerRoles")

public class ManagerRoles extends HttpServlet {
	private String error_msg;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String actions = request.getParameter("manageractions");
		
		Utilities utility = new Utilities(request, pw);
		User user=utility.getUser();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		if (actions.equalsIgnoreCase("showOrder")){

				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>Order</a>");
				pw.print("</h2><div class='entry'>");

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
				{}
				pw.print("<table class='gridtable'>");
				pw.print("<tr><td></td>");
				pw.print("<td>OrderId:</td>");
				pw.print("<td>UserName:</td>");
				pw.print("<td>productOrdered:</td>");
				pw.print("<td>productPrice:</td>");
				pw.print("<td>deliveryDate:</td></tr>");
				//pw.print("<td>Mode of Delivery:</td></tr>");
				for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
				{
					for(OrderPayment oi:entry.getValue())	
					//if(oi.getUserName().equals(user.getName())) 
					{
						pw.print("<form method='post' action='cancelOrderFromManager'>");
						pw.print("<tr>");			
						pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
						pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
						pw.print("<td>"+oi.getDeliveryDate()+"</td>");
						//<td>"+oi.getDeliveryMode()+"</td>
						pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
						pw.print("<input type='hidden' name='deliveryDate' value='"+oi.getDeliveryDate()+"'>");
						pw.print("<input type='hidden' name='username' value='"+oi.getUserName()+"'>");
						pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
						pw.print("</tr>");
						pw.print("</form>");
					}
				
				}
				pw.print("</table>");
				
				pw.print("</table>");

			
			
			
			
		}
		
		if (actions.equalsIgnoreCase("createUser")){
				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>Create User</a>");
				pw.print("</h2><div class='entry'>");
				pw.print("<form method='post' action='createUserFromManager'>");
				pw.print("<table>");
				pw.print("<h3>User name :</h3><input type='text' name='username' value='' class='input' required>");
				pw.print("<h3>Password :</h3><input type='password' name='password' value='' class='input' required>");
				pw.print("<h3>RePassword :</h3><input type='password' name='password' value='' class='input' required></td></tr>");
				
				pw.print("<h3>Street :</h3><input type='text' name='streetCust' value='' class='input' required></td></tr>");
				pw.print("<h3>City :</h3><input type='text' name='cityCust' value='' class='input' required></td></tr>");
				pw.print("<h3>State :</h3><input type='text' name='stateCust' value='' class='input' required></td></tr>");
				pw.print("<h3>Zip :</h3><input type='text' name='zipCust' value='' class='input' required></td></tr>");
				
				pw.print("<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='retailer'>Store Manager</option><option value='manager'>Salesman</option></select>");
				pw.print("</table><tr><td><input type='submit' class='btnbuy' name='ByUser' value='Create User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input></td></tr>");
				//pw.print("<input type='submit' name='submit' class='btnbuy'>");
				pw.print("</tr></form>");
				//createCustomers(username,password,usertype,);
		}
		
		if (actions.equalsIgnoreCase("updateOrder")){
				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>Order</a>");
				pw.print("</h2><div class='entry'>");
				
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
				{}
				pw.print("<table class='gridtable'>");
				pw.print("<tr><td></td>");
				pw.print("<td>OrderId:</td>");
				pw.print("<td>UserName:</td>");
				pw.print("<td>productOrdered:</td>");
				pw.print("<td>productPrice:</td>");
				pw.print("<td>deliveryDate:</td></tr>");
				//pw.print("<td>Mode of Delivery:</td>");
				for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
				{
					for(OrderPayment oi:entry.getValue())	 
					{
						pw.print("<form method='post' action='updateOrderFromManager'>");
						pw.print("<tr>");			
						pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
						pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
						pw.print("<td>"+oi.getDeliveryDate()+"</td>");
						pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
						pw.print("<input type='hidden' name='deliveryDate' value='"+oi.getDeliveryDate()+"'>");
						pw.print("<input type='hidden' name='deliveryMode' value='"+oi.getDeliveryMode()+"'>");
						pw.print("<input type='hidden' name='username' value='"+oi.getUserName()+"'>");
						pw.print("<td><input type='submit' name='Order' value='UpdateOrder' class='btnbuy'></td>");
						pw.print("</tr>");
						pw.print("</form>");
					}
				
				}
				pw.print("</table>");
				
				pw.print("</table>");
			
			
					
			
			
		}
		utility.printHtml("Footer.html");	       
	}

		



	
	protected void deleteCustomers(){
			HashMap<String, User> hm=new HashMap<String, User>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
			String usertype="customer";
			String username= "tester5";
			String password= "Rahul";
			
			
			try
			{
 			 FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\UserDetails.txt"));
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
			 hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
				
			}

			

			hm.remove(username);
			try{
				FileOutputStream fileOutputStream = new FileOutputStream(TOMCAT_HOME+"\\webapps\\Tutorial_2\\UserDetails.txt");
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(hm);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();			
			}
			catch(Exception e)
			{
				
			}
			System.out.println("Your "+username+" account has been deleted.");
                    
	}
	

	
	

}










