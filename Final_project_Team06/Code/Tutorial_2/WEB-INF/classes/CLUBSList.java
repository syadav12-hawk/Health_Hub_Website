import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CLUBSList")

public class CLUBSList extends HttpServlet {

	/* TV Page Displays all the tvs and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		
		String name = null;
		String CategoryName = request.getParameter("maker");
        
		
		
		if (CategoryName == null){
			name="";
		}
		else{name=CategoryName;}
		
		/* Checks the Tablets type whether it is microsft or SAMSUNG or SAMSUNG */
		
		HashMap<String,Product> hm=new HashMap<String,Product>();
		hm=MySqlDataStoreUtilities.selectProduct();
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Clubs</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		pw.print("<tr>");

		/*
		for (String key : hm.keySet()) { 
			Product product=hm.get(key);
			
			System.out.println("----------------------------------------------");
			System.out.println("Product Name :"+key);
			System.out.println("Retailer :"+product.getProdRetailer());
			System.out.println("CategoryName :"+CategoryName);
			System.out.println("Test :"+product.getProdRetailer().trim().equalsIgnoreCase(CategoryName.trim()));
			System.out.println("----------------------------------------------");
			
			if (product.getProdRetailer().trim().equalsIgnoreCase(CategoryName.trim())){
				show_clubs(key,pw,hm);
			}
		}
		*/
		int count=0;
		if (CategoryName == null)	
		{	
			
			for (String key : hm.keySet()) { 
				Product product=hm.get(key);
				//int count=0
				if(count>2){
					count=0;
					pw.print("</tr>");
					pw.print("<tr>");
				}
				
				if (product.getProdType().equals("HEALTHCLUB")){
					show_clubs(key,pw,hm);
					count=count+1;
				}
				
		    }
		} 
		else{
			for (String key : hm.keySet()) { 
				Product product=hm.get(key);
				if(count>2){
					count=0;
					pw.print("</tr>");
					pw.print("<tr>");
				}
				
				if (product.getProdRetailer().equals(CategoryName)){
					show_clubs(key,pw,hm);
					count=count+1;
				}
		    }
		}
		
		pw.print("</tr>");
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
		
	}
	
	public void show_clubs(String name,PrintWriter pw,HashMap<String,Product> hm){
		Product product=hm.get(name);		
		
		pw.print("<td><div id='shop_item'>");
		pw.print("<h3>"+product.getProdName()+"</h3>");
		pw.print("<strong>$"+product.getProdPrice()+"</strong><ul>");
		pw.print("<li id='item'><img src='images/healthclubs/"+product.getImage()+"' alt='' /></li>");///////
		//pw.print("<li id='item'>Address :"+product.getProdCondition()+"</li>");
        pw.print("<li id='item'><a href='ShowMap?address="+product.getProdCondition()+"&description="+product.getSaleInfo()+"'>Address :"+product.getProdCondition()+"</a></li>");
		pw.print("<li id='item'>Discount :"+product.getProdDiscount()+"%</li>");
		pw.print("<li><form method='post' action='Cart'>" +
				"<input type='hidden' name='name' value='"+product.getProdName()+"'>"+
				"<input type='hidden' name='type' value='HealthClubs'>"+
				"<input type='hidden' name='maker' value='"+product.getProdRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"<input type='submit' class='btnbuy' value='Book Appointment'></form></li>");
		pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+product.getProdName()+"'>"+
				"<input type='hidden' name='type' value='HealthClubs'>"+
				"<input type='hidden' name='maker' value='"+product.getProdRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"<input type='hidden' name='price' value='"+product.getProdPrice()+"'>"+
				"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
		pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+product.getProdName()+"'>"+
				"<input type='hidden' name='type' value='HealthClubs'>"+
				"<input type='hidden' name='maker' value='"+product.getProdRetailer()+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"<input type='submit' value='ViewReview' class='btnreview'></form></li>");
		pw.print("</ul></div></td>");
		
		
	}
}
