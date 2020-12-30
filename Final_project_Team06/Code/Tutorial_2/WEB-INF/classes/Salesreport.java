

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.mongodb.AggregationOutput;


@WebServlet("/Salesreport")
public class Salesreport extends HttpServlet {

    //static DBCollection myReviews;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
		System.out.println("Inside Do Get ");
        displayPage(request, response, pw);
    }

    protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
            throws ServletException, IOException {
				
		System.out.println("Inside Display Page ");

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
		
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Sales Report</a></h2>"
				+ "<div class='entry'>");          
		pw.print("<div id = 'sales_div' style = 'width: 550px; height: 400px; margin: 0 auto'></div>");
		pw.print("<div id = 'sales_div2' style = 'width: 550px; height: 400px; margin: 0 auto'></div>");
		pw.print("<div id = 'barsale_div' style = 'width: 550px; height: 400px; margin: 0 auto'></div>");
		pw.println("</div></div></div>");
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='Salereport.js'></script>");
        utility.printHtml("Footer.html");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            
			System.out.println("Inside DoPost");
			HashMap<Integer, ArrayList<OrderPayment>> hm = MySqlDataStoreUtilities.selectOrder();
      
            ArrayList<OrderPayment> orders = getAllOrders(hm);

            String productJson = new Gson().toJson(orders);

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(productJson);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
	
    private static ArrayList<OrderPayment> getAllOrders(HashMap<Integer, ArrayList<OrderPayment>> hm){
		
		
		ArrayList<OrderPayment> all_orders = new ArrayList<>();
		System.out.println("Inside All Order ");
		for (Map.Entry mapElement : hm.entrySet()) { 
            int orderID = (int)mapElement.getKey();
			System.out.println(orderID);
			ArrayList<OrderPayment> torders = new ArrayList<>();
			torders=(ArrayList<OrderPayment>)mapElement.getValue();
			
			for (OrderPayment order: torders){
				all_orders.add(order);
			}
			//orders.add(order);	
        }

        return all_orders;
    }	
	

}
