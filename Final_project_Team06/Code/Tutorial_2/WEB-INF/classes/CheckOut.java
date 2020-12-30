import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String typeOfReq = request.getParameter("CheckOut");
		String orderName = request.getParameter("orderName");
		PrintWriter pw = response.getWriter();
	    Utilities utility = new Utilities(request, pw);
		
		if (typeOfReq.equals("CheckOut")){
			storeOrders(request, response);
		}
		else{
			
			HttpSession session=request.getSession();
		//get the order product details	on clicking submit the form will be passed to submitorder page	
			String userName = session.getAttribute("username").toString();
			
			utility.updateCustomerOrder(orderName,userName);
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Item removed from Cart</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			//pw.print("<tr>");
			//pw.print("<a style='font-size: 24px;'>Order Updated</a>");
			//pw.print("</tr>");
			pw.print("</table></div></div></div>");
			utility.printHtml("Footer.html");
		}

		
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
        Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 

		//get the order product details	on clicking submit the form will be passed to submitorder page	
		
	    String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");
		String deliveryMode=request.getParameter("deliveryMode");
		System.out.println("Insie CheckOut"+deliveryMode);
		
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='CheckOut' action='Payment' method='post'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
		pw.print(userName);
		pw.print("</td></tr>");
		// for each order iterate and display the order name price
		for (OrderItem oi : utility.getCustomerOrders()) 
		{
			pw.print("<tr><td> Product Purchased:</td><td>");
			pw.print(oi.getName()+"</td></tr><tr><td>");
			pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
			pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			pw.print("Product Price:</td><td>"+ oi.getPrice());
			pw.print("</td></tr>");
		}
		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+orderTotal);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("<input type='hidden' name='shippingCost' value='0'>");
		pw.print("<input type='hidden' name='discount' value='0'>");
		pw.print("</td></tr></table><table><tr></tr><tr></tr>");	
		pw.print("<tr><td>");
     	pw.print("Credit/accountNo</td>");
		pw.print("<td><input type='text' name='creditCardNo'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
	    pw.print("Customer Name and Address</td></tr>");
		//pw.print("<tr><td>Name :<input type='text' name='userAddress'></td></tr>");
		pw.print("<tr><td>Name(Alphabets Only) :<input oninvalid='setCustomValidity('Please enter on alphabets only in name. ')' type='text'name='name'></td></tr>");
		
		pw.print("<tr><td>Age :<input type='text' name='Customer_Age'></td>");
		pw.print("<tr><td>Occupation :<input type='text' name='Customer_Occupation'></td>");
		pw.print("<tr><td>Rate your experience (1-5) :<input type='text' name='Review_Rating'></td>");
		
		pw.print("<tr><td>Street :<input type='text' name='streetAddress'></td>");
		pw.print("<td>   City (Alphabets Only):<input pattern='[a-zA-Z]*' oninvalid='setCustomValidity('Please enter on alphabets only in City. ')' type='text' name='cityAddress'></td>");
		pw.print("<tr><td>State(Alphabets Only) :<input pattern='[a-zA-Z]*' oninvalid='setCustomValidity('Please enter on alphabets only in State. ')' type='text' name='stateAddress'></td></tr>");
		pw.print("<tr><td>ZipCode(Five digits Only) :<input pattern='[0-9]{5}' oninvalid='setCustomValidity('Please enter on numbers only in Zip. ')' type='text' name='zipAddress'></td></tr>");
        pw.print("</tr>");
		pw.print("<tr><td colspan='2'>");
		

		if (deliveryMode.equals("PickUP")){
			pw.print("<label >Choose the Location:</label>");
			pw.print("<select id='pickUpAdr' name='pickUpAdr'>");
		    pw.print("<option value='1'>Chicago</option>");
		    pw.print("<option value='2'>NewYork</option>");
		    pw.print("<option value='3'>Delhi</option>");
		    pw.print("<option value='4'>Mumbai</option>");
	        pw.print("<option value='5'>London</option>");
	        pw.print("<option value='6'>Berlin</option>");
	        pw.print("<option value='7'>SanFrancisco</option>");
	        pw.print("<option value='8'>Tokyo</option>");
	        pw.print("<option value='9'>Moscow</option>");
	        pw.print("<option value='10'>Georgia</option>");
	        pw.print("</select>");
			
		}
		else {
			pw.print("<input type='hidden' name='pickUpAdr' value='0'>");
		}
		
		pw.print("<input type = 'hidden' name = 'deliveryMode' value = "+deliveryMode+" />");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
		
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
