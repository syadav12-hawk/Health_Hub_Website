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




@WebServlet("/updateOrder")

public class updateOrder extends HttpServlet {
	private String error_msg;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String orderId = request.getParameter("orderId");
		String orderName = request.getParameter("orderName");
		
		
		Utilities utility = new Utilities(request, pw);
		User user=utility.getUser();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
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
		//pw.print("<td>deliveryDate:</td>");
		//pw.print("<td>Mode of Delivery:</td></tr>");
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
		{
			for(OrderPayment oi:entry.getValue())					
			{
				if ((oi.getOrderId()==Integer.parseInt(orderId)) && oi.getOrderName().equalsIgnoreCase(orderName) ){
					oi.setUserName(request.getParameter("newusername"));
					oi.setOrderPrice(Double.parseDouble(request.getParameter("newOrderPrice")));
					oi.setDeliveryDate(LocalDate.parse(request.getParameter("newdeliveryDate")));
	
				}

			}
		
		}
		
		try
		{	
		
		    MySqlDataStoreUtilities.updateOrder(orderPayments);
		
		/*
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
		
		}
		pw.print("<h3>Order has been Updated</h3>");
		utility.printHtml("Footer.html");	

	}
	
}
		
