import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class Store implements Serializable{
	private int StoreId;
	private String StoreAdd;

	public Store(int StoreId,String StoreAdd) {
		this.StoreId=StoreId;
		this.StoreAdd=StoreAdd;

	}

	public int getStoreId() {
		return StoreId;
	}

	public void setStoreId(int StoreId) {
		this.StoreId = StoreId;
	}

	public String getStoreAdd() {
		return StoreAdd;
	}

	public void setStoreAdd(String StoreAdd) {
		this.StoreAdd = StoreAdd;
	}

}
