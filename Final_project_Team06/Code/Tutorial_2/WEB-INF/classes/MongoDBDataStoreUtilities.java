import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
                	
public class MongoDBDataStoreUtilities
{
static DBCollection myReviews;
public static DBCollection getConnection()
{
MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("CustomerReviews");
 myReviews= db.getCollection("myReviews");	
return myReviews; 
}


//public static String insertReview(String productname,String username,String producttype,String productmaker,String reviewrating,String reviewdate,String reviewtext,String retailerpin,String price,String retailercity)
public static String insertReview(String productname,String producttype,String productprice,String productmaker,
	String reviewrating,String storeId,String retailercity,String state,String zipcode,String prodSale,
	String userName,String age,String gender,String occupation,String reviewdate,String reviewtext)
{
	try
		{		
			myReviews=getConnection();
			BasicDBObject doc = new BasicDBObject("title", "myReviews").
				append("userName", userName).
				append("productName", productname).
				append("productType", producttype).
				append("productprice", productprice).
				append("productmaker", productmaker).
				append("reviewrating", reviewrating).
				append("storeId", storeId).
				append("retailercity", retailercity).
				append("state", state).
				append("zipcode", zipcode).
				append("prodSale", prodSale).
				append("age", age).
				append("gender", gender).
				append("reviewdate", reviewdate).
				append("productType", producttype).
				append("reviewtext", reviewtext);
				
				
				//append("productMaker", productmaker).
				//append("reviewRating",Integer.parseInt(reviewrating)).
				//append("reviewDate", reviewdate).
				//append("reviewText", reviewtext).
				//append("retailerpin", retailerpin).
				//append("retailercity", retailercity).
				//append("price",(int) Double.parseDouble(price));
				
			myReviews.insert(doc);
			
			System.out.println("Successfull");
			return "Successfull";
		}
		catch(Exception e)
		{
		return "UnSuccessfull";
		}	
		
}

public static HashMap<String, ArrayList<Review>> selectReview()
{	
	HashMap<String, ArrayList<Review>> reviews=null;
	
	try
	{

		getConnection();
		DBCursor cursor = myReviews.find();
		reviews=new HashMap<String, ArrayList<Review>>();
		while (cursor.hasNext())
		{
				BasicDBObject obj = (BasicDBObject) cursor.next();				
		
			   if(!reviews.containsKey(obj.getString("productName")))
				{	
					ArrayList<Review> arr = new ArrayList<Review>();
					reviews.put(obj.getString("productName"), arr);
				}
				ArrayList<Review> listReview = reviews.get(obj.getString("productName"));		
				Review review =new Review(obj.getString("productName"),
										  obj.getString("producttype"),
										  obj.getString("productprice"),
										  obj.getString("productmaker"),
										  obj.getString("reviewrating"),
										  obj.getString("storeId"),
										  obj.getString("retailercity"),
										  obj.getString("state"),
										  obj.getString("zipcode"),
										  obj.getString("prodSale"),
										  obj.getString("userName"),
										  obj.getString("age"),
										  obj.getString("occupation"),
										  obj.getString("gender"),
										  obj.getString("reviewdate"),
										  obj.getString("reviewtext")
										 );
				System.out.println(obj.get("productName").toString());	
				System.out.printf(obj.getString("productName"),
										  obj.getString("producttype"),
										  obj.getString("productprice"),
										  obj.getString("productmaker"),
										  obj.getString("reviewrating"),
										  obj.getString("storeId"),
										  obj.getString("retailercity"),
										  obj.getString("state"),
										  obj.getString("zipcode"),
										  obj.getString("prodSale"),
										  obj.getString("userName"),
										  obj.getString("age"),
										  obj.getString("occupation"),
										  obj.getString("gender"),
										  obj.getString("reviewdate"),
										  obj.getString("reviewtext")
										 );
				//add to review hashmap
				listReview.add(review);
			
		}
 		
		return reviews;
	}
	catch(Exception e)
	{
	 reviews=null;
	 return reviews;	
	}	

     
	}
	

  public static  ArrayList <Bestrating> topProducts(){
	  ArrayList <Bestrating> Bestrate = new ArrayList <Bestrating> ();
	  try{
		  
	  getConnection();
	  int retlimit =5;
	  DBObject sort = new BasicDBObject();
	  sort.put("reviewrating",-1);
	  DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
	  while(cursor.hasNext()) {
		  BasicDBObject obj = (BasicDBObject) cursor.next();
		
		  String prodcutnm = obj.get("productName").toString();
		  String rating = obj.get("reviewrating").toString();
	      Bestrating best = new Bestrating(prodcutnm,rating);
		  Bestrate.add(best);
	  }
	
	}catch (Exception e){ System.out.println(e.getMessage());}
   return Bestrate;
  }
  
 public static ArrayList <Mostsoldzip> mostsoldZip(){
	  ArrayList <Mostsoldzip> mostsoldzip = new ArrayList <Mostsoldzip> ();
	  try{
		  
	  getConnection();
      DBObject groupProducts = new BasicDBObject("_id","$zipcode"); 
	  groupProducts.put("count",new BasicDBObject("$sum",1));
	  DBObject group = new BasicDBObject("$group",groupProducts);
	  DBObject limit=new BasicDBObject();
      limit=new BasicDBObject("$limit",5);
	  
	  DBObject sortFields = new BasicDBObject("count",-1);
	  DBObject sort = new BasicDBObject("$sort",sortFields);
	  AggregationOutput output = myReviews.aggregate(group,sort,limit);
	  
	  //DBCursor cursor = myReviews.aggregate(group,sort,limit);
      for (DBObject res : output.results()) {
		//while(cursor.hasNext()) {
			//DBObject res = (DBObject) cursor.next();
			String zipcode =(res.get("_id")).toString();
			String count = (res.get("count")).toString();
			System.out.println("Inside Most Sold by Zip..................................................................."+zipcode+","+count);			
			Mostsoldzip mostsldzip = new Mostsoldzip(zipcode,count);
			mostsoldzip.add(mostsldzip);
	
	  }
	  
	 
	  
	}catch (Exception e)
	{ 
		System.out.println("Inside Most Sold by ZIP......................................................................................................................");
		System.out.println(e.getMessage());
	}
      return mostsoldzip;
  }
  
public static ArrayList <Mostsold> mostsoldProducts(){
	ArrayList <Mostsold> mostsold = new ArrayList <Mostsold> ();
	try{
		  System.out.println("Inside Most Sold......................................................................................................................");
		  getConnection();
		  DBObject groupProducts = new BasicDBObject("_id","$productName"); 
		  groupProducts.put("count",new BasicDBObject("$sum",1));
		  DBObject group = new BasicDBObject("$group",groupProducts);
		  DBObject limit=new BasicDBObject();
		  limit=new BasicDBObject("$limit",5);
		  
		  DBObject sortFields = new BasicDBObject("count",-1);
		  DBObject sort = new BasicDBObject("$sort",sortFields);
		  AggregationOutput output = myReviews.aggregate(group,sort,limit);
		  //DBCursor cursor = myReviews.aggregate(group,sort,limit);
	  
		  for (DBObject res : output.results()) {
			//while(cursor.hasNext()) {
			  
				String prodcutname =(res.get("_id")).toString();
				String count = (res.get("count")).toString();	
				
				System.out.println("Inside Most Sold.........................................................................."+prodcutname+","+count);
				Mostsold mostsld = new Mostsold(prodcutname,count);
				mostsold.add(mostsld);
		
		}
	  
	 
	  
	}catch (Exception e)
	{ 
		System.out.println("Inside Most Sold Exception......................................................................................................................");
		System.out.println(e.getMessage());
	}
      return mostsold;
  }	

  //Get all the reviews grouped by product and zip code;
public static ArrayList<Review> selectReviewForChart() {

		
        ArrayList<Review> reviewList = new ArrayList<Review>();
        try {

            getConnection();
            Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
            dbObjIdMap.put("retailerpin", "$retailerpin");
            dbObjIdMap.put("productName", "$productName");
            DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
            groupFields.put("count", new BasicDBObject("$sum", 1));
            DBObject group = new BasicDBObject("$group", groupFields);

            DBObject projectFields = new BasicDBObject("_id", 0);
            projectFields.put("retailerpin", "$_id");
            projectFields.put("productName", "$productName");
            projectFields.put("reviewCount", "$count");
            DBObject project = new BasicDBObject("$project", projectFields);

            DBObject sort = new BasicDBObject();
            sort.put("reviewCount", -1);

            DBObject orderby = new BasicDBObject();            
            orderby = new BasicDBObject("$sort",sort);
            

            AggregationOutput aggregate = myReviews.aggregate(group, project, orderby);

            for (DBObject result : aggregate.results()) {

                BasicDBObject obj = (BasicDBObject) result;
                Object o = com.mongodb.util.JSON.parse(obj.getString("retailerpin"));
                BasicDBObject dbObj = (BasicDBObject) o;
                Review review = new Review( dbObj.getString("productName"),
											dbObj.getString("producttype"),
											dbObj.getString("productprice"),
											dbObj.getString("productmaker"),
											dbObj.getString("reviewrating"),
											dbObj.getString("storeId"),
											dbObj.getString("retailercity"),
											dbObj.getString("state"),
											dbObj.getString("zipcode"),
											dbObj.getString("prodSale"),
											dbObj.getString("userName"),
											dbObj.getString("age"),
											dbObj.getString("occupation"),
											dbObj.getString("gender"),
											dbObj.getString("reviewdate"),
											dbObj.getString("reviewtext")
											 );
				
                reviewList.add(review);
                
            }
            return reviewList;

        }

        catch (

        Exception e) {
            reviewList = null;
            
            return reviewList;
        }

    }
  

	
}	