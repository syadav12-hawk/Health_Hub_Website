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

@WebServlet("/createUserFromManager")

public class createUserFromManager extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		
		String streetCust = request.getParameter("streetCust");
		String cityCust = request.getParameter("cityCust");
		String stateCust = request.getParameter("stateCust");
		double zipCust = Double.parseDouble(request.getParameter("zipCust"));		
		
		
		Utilities utility = new Utilities(request, pw);
		User user=utility.getUser();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		
		//pw.print("<form method='post' action='createUserFromManager'>");
		//pw.print("<table style='width:100%'>Create User");
		//pw.print("</table><tr><td><input type='submit' class='btnbuy' name='ByUser' value='Create User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input></td></tr>");
		
		//pw.print("</tr></form>");
		//createCustomers(username,password,usertype);
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Result</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<h2>"+createCustomers(username,password,usertype,streetCust,cityCust,stateCust,zipCust)+"</h2>");
		utility.printHtml("Footer.html");	       
	}

		
	
	
	protected String createCustomers(String username,String password,String usertype,String streetCust,String cityCust,
	String stateCust,double zipCust){
			HashMap<String, User> hm=new HashMap<String, User>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
				try
				{

				hm= MySqlDataStoreUtilities.selectUser();

				/*
				 FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\UserDetails.txt"));
				 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				 hm= (HashMap)objectInputStream.readObject();
				 
				 */
				}
				catch(Exception e)
				{
					
				}

			// if the user already exist show error that already exist
			if(hm.containsKey(username)){
			   String error= "Username already exist as " + usertype;
			   return error;
			   }
			
			
			else{
				/*create a user object and store details into hashmap
				store the user hashmap into file  */
					try{
						User user = new User(username,password,usertype,streetCust,cityCust,stateCust,zipCust);
						hm.put(username, user);
						
						/*
						FileOutputStream fileOutputStream = new FileOutputStream(TOMCAT_HOME+"\\webapps\\Tutorial_2\\UserDetails.txt");
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
						objectOutputStream.writeObject(hm);
						objectOutputStream.flush();
						objectOutputStream.close();       
						fileOutputStream.close();
						*/
						
						MySqlDataStoreUtilities.insertUser(username,password,usertype,streetCust,cityCust,stateCust,zipCust);
						
					}
					catch(Exception e)
					{
						
					}
			    }
				return "Your "+usertype+" account has been created. Please login";
			}
	    }

             
	
	
	
	


