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
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.time.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;




@WebServlet("/cancelOrderFromManager")

public class cancelOrderFromManager extends HttpServlet {
	private String error_msg;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//String actions = request.getParameter("manageractions");
		
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");
		//String usertype = request.getParameter("usertype");
		
		Utilities utility = new Utilities(request, pw);
		User user=utility.getUser();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");		
		
		try
		{
			FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\PaymentDetails.txt"));
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
			orderPayments = (HashMap)objectInputStream.readObject();
		}
		catch(Exception e)
		{}
		
		
		
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder"))
		{   
	
			if(request.getParameter("orderName") != null)
				
			{   
		        //Check if the Cancel request is 5 days before delivery date. 
				LocalDate deliveryDate=LocalDate.parse(request.getParameter("deliveryDate"));
		        LocalDate todaysDate= LocalDate.now();
		        LocalDate checkDate=todaysDate.plusDays(5);
				int datecheck=checkDate.compareTo(deliveryDate);
				System.out.println("Inside Veiw Order Cancel  " +request.getParameter("deliveryDate"));
				System.out.println("Inside Veiw Order  Cancel " +checkDate.compareTo(deliveryDate));
				
				if (datecheck <0){
			
					String orderName=request.getParameter("orderName");
					int orderId=0;
					orderId=Integer.parseInt(request.getParameter("orderId"));
					ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
					//get the order from file
					try
					{
						orderPayments=MySqlDataStoreUtilities.selectOrder();
						/*
						FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\PaymentDetails.txt"));
						ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
						orderPayments = (HashMap)objectInputStream.readObject();*/
					}
					catch(Exception e)
					{
				
					}
					//get the exact order with same ordername and add it into cancel list to remove it later
					for (OrderPayment oi : orderPayments.get(orderId)) 
						{
								if(oi.getOrderName().equals(orderName) && oi.getUserName().equals(request.getParameter("username")))
								{
									ListOrderPayment.add(oi);
									pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");								
								}
						}
					//remove all the orders from hashmap that exist in cancel list
					orderPayments.get(orderId).removeAll(ListOrderPayment);
					if(orderPayments.get(orderId).size()==0)
						{
								orderPayments.remove(orderId);
						}
					//save the updated hashmap with removed order to the file	
					try
					{	
						
						MySqlDataStoreUtilities.updateOrder(orderPayments);
						
						/*
						FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\PaymentDetails.txt"));
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
						objectOutputStream.writeObject(orderPayments);
						objectOutputStream.flush();
						objectOutputStream.close();       
						fileOutputStream.close();*/
					}
					catch(Exception e)
					{
					
					}
                }else
				{
					pw.print("<h4 style='color:red'>Error!! Your delivery date is within 5 days from today and hence you can't cancel the order.</h4>");
				}
			}else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}
		
	}
		

}








