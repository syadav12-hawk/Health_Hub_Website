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
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




@WebServlet("/UpdateFeatureCatalog")

public class UpdateFeatureCatalog extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request, pw);
		String productCatagory = request.getParameter("productCatagory");
		String productID = request.getParameter("productID");
		String requestType = request.getParameter("requestType");
		
		
		String featureToUpdate  = request.getParameter("featureToBeUpdated");
		String valueToBeUpdated=String.valueOf(request.getParameter("newFetureValue"));
		
		System.out.println(" \nFeatures to be Updated "+featureToUpdate);
		System.out.println(" \nValue to be Updated "+valueToBeUpdated);
		System.out.println("Inside Update feature Catahlog to update "+productID);
		//String valueOfFeatureTobeUpdated=request.getParameter("valueOfFeatureTobeUpdated");
		
		/*
		if (featureToUpdate.equalsIgnoreCase("price")){
			String valueToBeUpdated=request.getParameter("price");
		}
		if (featureToUpdate.equalsIgnoreCase("condition")){
			String valueToBeUpdated=request.getParameter("condition");
		}
		if (featureToUpdate.equalsIgnoreCase("discount")){
			String valueToBeUpdated= request.getParameter("discount");
		}
		*/
		
		
		//str = "Hello I'm your String";
        //String[] splitted = productID.split("\\s+");
		//String product= splitted[0];
		//System.out.println("Product to be added"+product);
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		
		
		System.out.println(" request Type"+requestType);
		System.out.println(" Product Category"+productCatagory);
		System.out.println(" Product ID "+productID);
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		
		String filePath = TOMCAT_HOME+"\\webapps\\Tutorial_2\\ProductCatalog.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Result</a>");
		pw.print("</h2><div class='entry'>");
		
		
		
		try {	
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            //updateElementValue(doc);
			/*
			if (requestType.equalsIgnoreCase("Delete")){
				pw.print("<h4 style='color:green'>"+productID+" has been deleted. Please restart the server.</h4>");
				deleteProduct(doc,productID,productCatagory);
			}
            */
			
			pw.print("<h4 style='color:green'>"+productID+" has been updated. Please restart the server.</h4>");
			updateProduct(doc,featureToUpdate,valueToBeUpdated,productCatagory,productID);
			/*
			if (requestType.equalsIgnoreCase("Add")){
				pw.print("<h4 style='color:green'>"+productID+" has been added. Please restart the server.</h4>");
				addProduct(doc,productID,productCatagory,product);
			}
			*/
            
            //write the updated document to file or console
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(TOMCAT_HOME+"\\webapps\\Tutorial_2\\ProductCatalog.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");
            
        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
		utility.printHtml("Footer.html");
	
	}
		
		
	private static void addElement(Document doc) {
	NodeList employees = doc.getElementsByTagName("Employee");
	Element emp = null;
	
	//loop for each employee
	for(int i=0; i<employees.getLength();i++){
		emp = (Element) employees.item(i);
		Element salaryElement = doc.createElement("salary");
		salaryElement.appendChild(doc.createTextNode("10000"));
		emp.appendChild(salaryElement);
        }
    }

    private static void deleteElement(Document doc) {
        NodeList employees = doc.getElementsByTagName("Employee");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<employees.getLength();i++){
            emp = (Element) employees.item(i);
            Node genderNode = emp.getElementsByTagName("gender").item(0);
            emp.removeChild(genderNode);
        }
        
    }
	
//-------------------------------------------------------------------------	
	private static void deleteProduct(Document doc,String productID,String productCatagory) {
	NodeList elementNames = doc.getElementsByTagName(productCatagory);
	Element ele = null;
	//loop for each employee
	System.out.println("Lengh"+elementNames.getLength());
	for(int i=0; i<elementNames.getLength();i++){
		ele = (Element) elementNames.item(i);
		Node name = ele.getElementsByTagName("name").item(0).getFirstChild();
		if (name.getNodeValue().equalsIgnoreCase(productID)){
			Node manufacturer=ele.getElementsByTagName("manufacturer").item(0).getFirstChild();
			manufacturer.setNodeValue("NULL");
			
		}
	}
    }
	
//------------------------------------------------------------------------------------



//Adding Product 

	private static void addProduct(Document doc,String productID,String productCatagory,String product) {
	NodeList elementNames = doc.getElementsByTagName(productCatagory);
	Element ele = null;
	//loop for each employee
	for(int i=0; i<elementNames.getLength();i++){
		ele = (Element) elementNames.item(i);
		Node name = ele.getElementsByTagName("name").item(0).getFirstChild();
		if (name.getNodeValue().equalsIgnoreCase(productID)){
			Node manufacturer=ele.getElementsByTagName("manufacturer").item(0).getFirstChild();
			manufacturer.setNodeValue(product);
		}
		//name.setNodeValue(name.getNodeValue().toUpperCase());
	}
    }
	
//--------------------------------------------------------------------------------------

//---------------------------------------------------------------------------------------
    private static void updateProduct(Document doc,String featureToUpdate, String valueToBeUpdated,String productCatagory,String productID) {
        NodeList elementNames = doc.getElementsByTagName(productCatagory);
        Element emp = null;
        //loop for each employee
        for(int i=0; i<elementNames.getLength();i++){
            emp = (Element) elementNames.item(i);
            Node name = emp.getElementsByTagName("name").item(0).getFirstChild();
			if (name.getNodeValue().equalsIgnoreCase(productID)){
			   Node manufacturer=emp.getElementsByTagName(featureToUpdate).item(0).getFirstChild();
			   manufacturer.setNodeValue(valueToBeUpdated);
		    }
         
        }
    }


//--------------------------------------------------------------------------







	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}