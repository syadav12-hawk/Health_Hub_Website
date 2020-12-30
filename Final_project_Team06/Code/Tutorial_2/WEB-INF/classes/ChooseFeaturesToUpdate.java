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

@WebServlet("/ChooseFeaturesToUpdate")

public class ChooseFeaturesToUpdate extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			String productCatagory = request.getParameter("productCatagory");
			String requestType = request.getParameter("requestType");
			String productID = request.getParameter("productID");
			
			
			

			Utilities utility = new Utilities(request, pw);
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to Pay");
				response.sendRedirect("Login");
				return;
			}
			// get the payment details like credit card no address processed from cart servlet	
			
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Update the features</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("</h2><div class='entry'>");
			pw.print("</h2><div class='entry'>");
			pw.print("<form name ='ChooseFeaturesToUpdate' action='ChooseValueForfeature' method='post'>");
			pw.print("<label >Select feature to be updated: </label>");
			pw.print("<select id='featureID' name='featureID'>");
		    pw.print("<option value='price'>Price</option>");
		    pw.print("<option value='condition'>Condition</option>");
		    pw.print("<option value='discount'>Discount</option>");
			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("<input type='hidden' name='productID' value='"+productID+"'>");
			pw.print("<input type='hidden' name='productCatagory' value="+productCatagory+">");
	        pw.print("</select>");
			pw.print("</form>"); 

			utility.printHtml("Footer.html");
			System.out.println("Inside Chhoose Festures to update "+productID);
		}	
	//}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
