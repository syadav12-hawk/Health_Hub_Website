import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchNear")

public class SearchNear extends HttpServlet {

	/* TV Page Displays all the tvs and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String product_search = request.getParameter("product_search");
        //String description = request.getParameter("description");
		System.out.println("Prooduct to be shown : "+product_search);

        Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Description</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");


		pw.print("<div class='mapouter'>"
		+"<div class='google_map'>"
		+"<iframe width='600' height='500' id='google_map' src='https://www.google.com/maps/embed/v1/search?key=AIzaSyAUL1gHXjCThQiC3WQZHkwfNMg5IjWQq0M&q="+product_search+"' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'>"
		+"</iframe>"
		+"</div>"
		+"<style>.mapouter{position:relative;text-align:right;height:500px;width:600px;}.gmap_canvas {overflow:hidden;background:none!important;height:500px;width:600px;}</style>"
		+"</div>");




//Show Address in Map
/*
pw.print("<div class='mapouter'>"
+"<div class='google_map'>"
+"<iframe width='600' height='500' id='google_map' src='https://www.google.com/maps/embed/v1/place?key=AIzaSyAUL1gHXjCThQiC3WQZHkwfNMg5IjWQq0M&q="+address+"' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'>"
+"</iframe>"
+"</div>"
+"<style>.mapouter{position:relative;text-align:right;height:500px;width:600px;}.gmap_canvas {overflow:hidden;background:none!important;height:500px;width:600px;}</style>"
+"</div>");	
*/
	
/*
pw.print("<div class='mapouter'>"
+"<div class='google_map'>"
+"<iframe width='600' height='500' id='google_map' src='https://www.google.com/maps/embed/v1/directions?key=AIzaSyAUL1gHXjCThQiC3WQZHkwfNMg5IjWQq0M&origin=&destination="+address+"' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'>"
+"</iframe>"
+"</div>"
+"<style>.mapouter{position:relative;text-align:right;height:500px;width:600px;}.gmap_canvas {overflow:hidden;background:none!important;height:500px;width:600px;}</style>"
+"</div>");
*/

		//pw.print("<p>About me : Family Medicine Specialist in Chicago, IL and has over 40 years of experience in the medical field.  He graduated from Loyola University Of Chicago/Stritch School Of Medicine medical school in 1980.  He is affiliated with medical facilities such as Advocate Illinois Masonic Medical Center and Mercy Hospital And Medical Center.  He is accepting new patients.  Be sure to call ahead with Dr. Knapp to book an appointment.</p>");
        pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");	
	


//https://www.google.com/maps/embed/v1/directions?key=AIzaSyAUL1gHXjCThQiC3WQZHkwfNMg5IjWQq0M&origin=Chicagoo&destination=California

//https://www.google.com/maps/embed/v1/search?key=AIzaSyAUL1gHXjCThQiC3WQZHkwfNMg5IjWQq0M&q=Doctorsnearme
/*
pw.print("<div class='mapouter'>"
+"<div class='google_map'>"
+"<iframe width='600' height='500' id='google_map' src='https://www.google.com/maps/embed/v1/directions?key=AIzaSyAUL1gHXjCThQiC3WQZHkwfNMg5IjWQq0M&origin=o&destination=California' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'>"
+"</iframe>"
+"</div>"
+"<style>.mapouter{position:relative;text-align:right;height:500px;width:600px;}.gmap_canvas {overflow:hidden;background:none!important;height:500px;width:600px;}</style>"
+"</div>");

pw.print("<div class='mapouter'>"
+"<div class='google_map'>"
+"<iframe width='600' height='500' id='google_map' src='https://www.google.com/maps/embed/v1/search?key=AIzaSyAUL1gHXjCThQiC3WQZHkwfNMg5IjWQq0M&q=doctors' frameborder='0' scrolling='no' marginheight='0' marginwidth='0'>"
+"</iframe>"
+"</div>"
+"<style>.mapouter{position:relative;text-align:right;height:500px;width:600px;}.gmap_canvas {overflow:hidden;background:none!important;height:500px;width:600px;}</style>"
+"</div>");*/

			
	}
}
