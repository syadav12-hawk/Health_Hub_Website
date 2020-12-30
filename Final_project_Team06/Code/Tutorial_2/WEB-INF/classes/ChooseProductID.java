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

@WebServlet("/ChooseProductID")

public class ChooseProductID extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String productCatagory = request.getParameter("productCatagory");
		String requestType = request.getParameter("requestType");
		
		System.out.println("request Type"+requestType);
		
		
		response.setContentType("text/html");


		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		if (productCatagory.equalsIgnoreCase("tv")){
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Select the TV:</a>");
			pw.print("</h2><div class='entry'>");
			if (requestType.equalsIgnoreCase("Update")){
				pw.print("<form name ='ChooseProductID' action='ChooseFeaturesToUpdate' method='post'>");
			}else{
				pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			}
			pw.print("<label >Select Product :</label>");
			//Select Product Category
			pw.print("<select id='productID' name='productID'>");
		    pw.print("<option value='LG TV'>LG TV</option>");
		    pw.print("<option value='SAMSUNG TV'>SAMSUNG TV</option>");
		    pw.print("<option value='SONY TV'>SONY TV</option>");
		    pw.print("<option value='TCL TV'>TCL TV</option>");
	        pw.print("<option value='VIZIO TV'>VIZIO TV</option>");
			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("<input type='hidden' name='productCatagory' value="+productCatagory+">");
	        pw.print("</select>");
			pw.print("</form>");  
		}
		
		if (productCatagory.equalsIgnoreCase("soundsystem")){
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Select the soundsystem:</a>");
			pw.print("</h2><div class='entry'>");
			if (requestType.equalsIgnoreCase("Update")){
				pw.print("<form name ='ChooseProductID' action='ChooseFeaturesToUpdate' method='post'>");
			}else{
				pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			}
			
			//pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			pw.print("<label >Select Product :</label>");
			//Select Product Category
			pw.print("<select id='productID' name='productID'>");
		    pw.print("<option value='BOSS SOUND'>BOSS SOUND</option>");
		    pw.print("<option value='KLH SOUND'>KLH SOUND</option>");
		    pw.print("<option value='KLIPSCH SOUND'>KLIPSCH SOUND</option>");
		    pw.print("<option value='SONOS SOUND'>SONOS SOUND</option>");
	        pw.print("<option value='SVS SOUND'>SVS SOUND</option>");
			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("<input type='hidden' name='productCatagory' value="+productCatagory+">");
	        pw.print("</select>");
			pw.print("</form>");  
		}
		
		if (productCatagory.equalsIgnoreCase("laptop")){
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Select the laptop:</a>");
			pw.print("</h2><div class='entry'>");
			if (requestType.equalsIgnoreCase("Update")){
				pw.print("<form name ='ChooseProductID' action='ChooseFeaturesToUpdate' method='post'>");
			}else{
				pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			}
			
			//pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			pw.print("<label >Select Product :</label>");
			//Select Product Category
			pw.print("<select id='productID' name='productID'>");
		    pw.print("<option value='DELL LAPTOP'>DELL LAPTOP</option>");
		    pw.print("<option value='GOOGLE LAPTOP'>GOOGLE LAPTOP</option>");
		    pw.print("<option value='LENEVO LAPTOP'>LENEVO LAPTOP</option>");
		    pw.print("<option value='MACBOOK LAPTOP'>MACBOOK LAPTOP</option>");
	        pw.print("<option value='MICROSOFT LAPTOP'>MICROSOFT LAPTOP</option>");
			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("<input type='hidden' name='productCatagory' value="+productCatagory+">");

			
			
	        pw.print("</select>");
			pw.print("</form>");  
		}
		
		if (productCatagory.equalsIgnoreCase("voiceassistant")){
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Select the voiceassistant:</a>");
			pw.print("</h2><div class='entry'>");
			if (requestType.equalsIgnoreCase("Update")){
				pw.print("<form name ='ChooseProductID' action='ChooseFeaturesToUpdate' method='post'>");
			}else{
				pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			}
			//pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			pw.print("<label >Select Product :</label>");
			//Select Product Category
			pw.print("<select id='productID' name='productID'>");
		    pw.print("<option value='AMAZON ALEXA'>AMAZON ALEXA</option>");
		    pw.print("<option value='APPLE SIRI'>APPLE SIRI</option>");
		    pw.print("<option value='SAMSUNG BIXBY'>SAMSUNG BIXBY</option>");
		    pw.print("<option value='MICROSOFT CORTONA'>MICROSOFT CORTONA</option>");
	        pw.print("<option value='GOOGLE ASSISTANT'>GOOGLE ASSISTANT</option>");
			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("<input type='hidden' name='productCatagory' value="+productCatagory+">");
		
			
	        pw.print("</select>");
			pw.print("</form>");  
		}
		
		if (productCatagory.equalsIgnoreCase("wearabletechnology")){
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Select the wearabletechnology:</a>");
			pw.print("</h2><div class='entry'>");
			if (requestType.equalsIgnoreCase("Update")){
				pw.print("<form name ='ChooseProductID' action='ChooseFeaturesToUpdate' method='post'>");
			}else{
				pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			}			
			//pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			pw.print("<label >Select Product :</label>");
			//Select Product Category
			pw.print("<select id='productID' name='productID'>");
		    pw.print("<option value='APPLE SMART WATCH'>APPLE SMART WATCH</option>");
		    pw.print("<option value='OCULUS VR'>OCULUS VR</option>");
		    pw.print("<option value='TAGG PET TRACKER'>TAGG PET TRACKER</option>");
		    pw.print("<option value='GARMIN FITNESS WATCHES'>GARMIN FITNESS WATCHES</option>");
	        pw.print("<option value='SONY HEADPHONE'>SONY HEADPHONE</option>");
			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("<input type='hidden' name='productCatagory' value="+productCatagory+">");
		
			
	        pw.print("</select>");
			pw.print("</form>");  
		}
		
		
		
		if (productCatagory.equalsIgnoreCase("phone")){
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Select the phone:</a>");
			pw.print("</h2><div class='entry'>");
			if (requestType.equalsIgnoreCase("Update")){
				pw.print("<form name ='ChooseProductID' action='ChooseFeaturesToUpdate' method='post'>");
			}else{
				pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			}				
			//pw.print("<form name ='ChooseProductID' action='UpdateProductCatalog' method='post'>");
			pw.print("<label >Select Product :</label>");
			//Select Product Category
			pw.print("<select id='productID' name='productID'>");
		    pw.print("<option value='SAMSUNG GALAXY'>SAMSUNG GALAXY</option>");
		    pw.print("<option value='IPHONE 11 PRO'>IPHONE 11 PRO</option>");
		    pw.print("<option value='LG V60'>LG V60</option>");
		    pw.print("<option value='ONEPLUS 8'>ONEPLUS 8</option>");
	        pw.print("<option value='GOOGLE PIXEL'>GOOGLE PIXEL</option>");
			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			pw.print("<input type='hidden' name='requestType' value="+requestType+">");
			pw.print("<input type='hidden' name='productCatagory' value="+productCatagory+">");
		

	        pw.print("</select>");
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
