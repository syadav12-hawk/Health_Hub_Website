import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SoundSystemList")

public class SoundSystemList extends HttpServlet {

	/* SoundSystems Page Displays all the SoundSystems and their Information in SoundSystem Speed */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the SoundSystems type whether it is electronicArts or activision or takeTwoInteractive */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, SoundSystem> hm = new HashMap<String, SoundSystem>();
		
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.soundsystems);
			name = "";
		}
		else
		{
		  if(CategoryName.equals("BOSS"))
		  {
			for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.soundsystems.entrySet())
				{
				if(entry.getValue().getRetailer().equals("BOSS"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "BOSS";
		  }
		  else if(CategoryName.equals("KLH"))
		  {
			for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.soundsystems.entrySet())
				{
				if(entry.getValue().getRetailer().equals("KLH"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
			name = "KLH";
		  }
		  else if(CategoryName.equals("KLIPSCH"))
		  {
			for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.soundsystems.entrySet())
				{
				if(entry.getValue().getRetailer().equals("KLIPSCH"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "KLIPSCH";
		  }
		  
		  else if(CategoryName.equals("SONOS"))
		  {
			for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.soundsystems.entrySet())
				{
				if(entry.getValue().getRetailer().equals("SONOS"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "SONOS";
		  }

		  else if(CategoryName.equals("SVS"))
		  {
			for(Map.Entry<String,SoundSystem> entry : SaxParserDataStore.soundsystems.entrySet())
				{
				if(entry.getValue().getRetailer().equals("SVS"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "SVS";
		  }
		  
		}

		/* Header, Left Navigation Bar are Printed.

		All the SoundSystems and SoundSystems information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" SoundSystems</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, SoundSystem> entry : hm.entrySet()){
			SoundSystem soundsystem = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+soundsystem.getName()+"</h3>");
			pw.print("<strong>"+ "$" + soundsystem.getPrice() + "</strong><ul>");
			pw.print("<li id='item'><img src='images/soundsystems/"+soundsystem.getImage()+"' alt='' /></li>");
			pw.print("<li id='item'>Condition :'"+soundsystem.getCondition()+"'</li>");
			pw.print("<li id='item'>Discount :'"+soundsystem.getDiscount()+"%'</li>");
						
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='soundsystems'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='soundsystems'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='hidden' name='price' value='"+soundsystem.getPrice()+"'>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='soundsystems'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
		
	}

}
