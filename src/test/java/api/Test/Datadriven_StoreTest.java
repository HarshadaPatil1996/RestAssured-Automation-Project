package api.Test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.EndPoints.StoreEndPoints;
import api.Payload.Store;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class Datadriven_StoreTest 
{
   Store payload;
   
   @Test(priority = 1 , dataProvider = "alldata" ,dataProviderClass =DataProviders.class )
   public void setup(String orderid,String petis, String  Quantity, String shipdate, String status ,String comple)
   {
	   payload=new Store();
	   payload.setId(orderid);
	   payload.setPetId(petis);
	   payload.setQuantity(Quantity);
	   payload.setShipDate(shipdate);
	   payload.setStatus(status);
	   payload.setComplete(comple);
  
	  Response res= StoreEndPoints.CreateNewOrder(payload);
	Assert.  assertEquals(res.getStatusCode(), 200);
	Assert.  assertEquals(res.header("Content-Type"), "application/json");
	  
   }
   @Test(priority = 2 , dataProvider = "Orderid" ,dataProviderClass =DataProviders.class )

   public void deleteallOrderByid(String orderid) 
   {
	  Response res= StoreEndPoints.DeleteOrderBy_OrderID(orderid);
	
	  Assert.  assertEquals(res.getStatusCode(), 200);
	  Assert. assertEquals(res.header("Content-Type"), "application/json");
	  
   }
   
	
	
	
}
