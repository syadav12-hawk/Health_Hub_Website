import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PhoneList")

public class PhoneList extends HttpServlet {

	/* Trending Page Displays all the Phones and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the Phones type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Phone> hm = new HashMap<String, Phone>();

		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.phones);
			name = "";
		} 
		else 
		{
			if(CategoryName.equals("SAMSUNG")) 
			{	
				for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("SAMSUNG"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="SAMSUNG";
			} 
			else if (CategoryName.equals("IPHONE"))
			{
				for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("IPHONE"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "IPHONE";
			} 
			else if (CategoryName.equals("LG")) 
			{
				for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("LG"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "LG";
			}
			
			
			else if (CategoryName.equals("ONEPLUS")) 
			{
				for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("ONEPLUS"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "ONEPLUS";
			}
			
			
			else if (CategoryName.equals("GOOGLE")) 
			{
				for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("GOOGLE"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "GOOGLE";
			}
	    }

		/* Header, Left Navigation Bar are Printed.

		All the phones and tablet information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " Phones</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Phone> entry : hm.entrySet()) {
			Phone Phone = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + Phone.getName() + "</h3>");
			pw.print("<strong>" + Phone.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/phones/"
					+ Phone.getImage() + "' alt='' /></li>");
					
			pw.print("<li id='item'>Condition :'"+Phone.getCondition()+"'</li>");
			pw.print("<li id='item'>Discount :'"+Phone.getDiscount()+"%'</li>");					
					
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='hidden' name='price' value='"+Phone.getPrice()+"'>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
