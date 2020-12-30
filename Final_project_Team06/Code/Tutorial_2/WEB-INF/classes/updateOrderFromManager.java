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




@WebServlet("/updateOrderFromManager")

public class updateOrderFromManager extends HttpServlet {
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
		pw.print("<a style='font-size: 24px;'>Enter new Order Details</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table class='gridtable'>");
		pw.print("<tr>");
		pw.print("<td>NewUserName:</td>");
		pw.print("<td>NewproductPrice:</td>");
		pw.print("<td>deliveryDate:</td></tr>");
		//pw.print("<td>Mode of Delivery:</td></tr>");
		
		pw.print("<form method='post' action='updateOrder'>");
		pw.print("<tr>");
        pw.print("<td><input type='text' name='newusername' value=''></td>");	
		pw.print("<td><input type='text' name='newOrderPrice' value=''></td>");
		pw.print("<td><input type='date' name='newdeliveryDate' value=''></td>");
		pw.print("<td><input type='submit' name='Order' value='UpdateOrder' class='btnbuy'></td>");
		pw.print("</tr>");
		pw.print("<td><input type='hidden' name='orderId' value='"+orderId+"'></td>");
		pw.print("<td><input type='hidden' name='orderName' value='"+orderName+"'></td>");
		pw.print("</table>");
		pw.print("</form>");
		
		utility.printHtml("Footer.html");	
	}
	
}
		
