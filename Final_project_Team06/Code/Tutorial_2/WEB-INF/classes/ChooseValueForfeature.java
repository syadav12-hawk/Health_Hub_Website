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

@WebServlet("/ChooseValueForfeature")

public class ChooseValueForfeature extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			String productCatagory = request.getParameter("productCatagory");
			String requestType = request.getParameter("requestType");
			String productID = request.getParameter("productID");
			String featureID = request.getParameter("featureID");
			
			
			

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
			pw.print("<form name ='ChooseFeaturesToUpdate' action='UpdateFeatureCatalog' method='post'>");
			if (featureID.equalsIgnoreCase("price")){
				pw.print("<input type='hidden' name='featureToBeUpdated' value='price'>");
				pw.print("<tr><td>Enter the new Price : <input type='text' name='newFetureValue'></td></tr>");
				pw.print("<input type='submit' name='submit' class='btnbuy'>");
			}
			if (featureID.equalsIgnoreCase("discount")){
				pw.print("<input type='hidden' name='featureToBeUpdated' value='discount'>");
				pw.print("<tr><td>Enter the new discount : <input type='text' name='newFetureValue'></tr></td>");
				pw.print("<input type='submit' name='submit' class='btnbuy'>");
			}
			
			if (featureID.equalsIgnoreCase("condition")){
				pw.print("<label >Select the condition: </label>");
				pw.print("<select id='condition' name='newFetureValue'>");
				pw.print("<option value='New'>New</option>");
				pw.print("<option value='Used'>Used</option>");
				pw.print("<option value='Good'>Good</option>");
				pw.print("<option value='VeryGood'>VeryGood</option>");
				pw.print("<input type='submit' name='submit' class='btnbuy'>");
				pw.print("<input type='hidden' name='featureToBeUpdated' value='condition'>");

				pw.print("</select>");
			}
			pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("<input type='hidden' name='productID' value='"+productID+"'>");
			pw.print("<input type='hidden' name='productCatagory' value='"+productCatagory+"'>");
			pw.print("</form>"); 
			utility.printHtml("Footer.html");
			System.out.println("Inside Chhoose Value for feature to update "+productID);
		}	
	//}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
