import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UpdateCategoryDetails")

public class UpdateCategoryDetails extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String requestType = request.getParameter("productAction");
		response.setContentType("text/html");

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		System.out.println("request Type"+requestType);
		
		if (requestType.equalsIgnoreCase("Update")){
		
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Update Product Details</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<label >Enter Product Details:</label>");
			pw.print("<form name ='UpdateCategoryDetails' action='AddProductSQL' method='post'>");
			pw.print("<table>");
			pw.print("<tr><td>Name :<input type='text' name='prodName'></td></tr>");
			pw.print("<tr><td>Price :<input type='text' name='prodPrice'></td></tr>");
			pw.print("<tr><td>Type :<input type='text' name='prodType'></td></tr>");
			pw.print("<tr><td>Address :<input type='text' name='prodCondition'></td></tr>");
			pw.print("<tr><td>Discount :<input type='text' name='prodDiscount'></td></tr>");
			pw.print("<tr><td>Speclization :<input type='text' name='prodRetailer'></td></tr>");
			pw.print("<tr><td>Image:<input type='text' name='prodImg'></td></tr>");
			pw.print("<tr><td>No of Appoinments Available :<input type='text' name='itemCount'></td></tr>");
			pw.print("<tr><td>SaleInfo :<input type='text' name='saleInfo'></td></tr>");
			pw.print("</table>");

			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			//pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("</form>");

		}




		if (requestType.equalsIgnoreCase("Add")){
		
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Add a New product</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<label >Enter Product Details:</label>");
			pw.print("<form name ='UpdateCategoryDetails' action='AddProductSQL' method='post'>");
			pw.print("<table>");
			pw.print("<tr><td>Name :<input type='text' name='prodName'></td></tr>");
			pw.print("<tr><td>Price :<input type='text' name='prodPrice'></td></tr>");
			pw.print("<tr><td>Type :<input type='text' name='prodType'></td></tr>");
			pw.print("<tr><td>Address :<input type='text' name='prodCondition'></td></tr>");
			pw.print("<tr><td>Discount :<input type='text' name='prodDiscount'></td></tr>");
			pw.print("<tr><td>Speclization :<input type='text' name='prodRetailer'></td></tr>");
			pw.print("<tr><td>Image:<input type='text' name='prodImg'></td></tr>");
			pw.print("<tr><td>No of Appoinments Available :<input type='text' name='itemCount'></td></tr>");
			pw.print("<tr><td>SaleInfo :<input type='text' name='saleInfo'></td></tr>");
			pw.print("</table>");

			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			//pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("</form>");

		}
		
		
		if (requestType.equalsIgnoreCase("Delete")){
		
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Delete a Product</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<label >Enter Product Name to be Deleted:</label>");
			pw.print("<form name ='UpdateCategoryDetails' action='DelProductSQL' method='post'>");
			pw.print("<table>");
			pw.print("<tr><td>Name :<input type='text' name='prodName'></td></tr>");
			pw.print("</table>");

			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			//pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("</form>");

		}
		
		

		
		utility.printHtml("Footer.html");
		
	}
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

	}
}
