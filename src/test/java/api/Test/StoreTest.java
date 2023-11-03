package api.Test;

import static org.testng.Assert.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.EndPoints.StoreEndPoints;
import api.Payload.Store;
import api.utilities.ExtentReporteManager;
import io.opentelemetry.sdk.logs.data.Body;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
@Listeners(ExtentReporteManager.class)
public class StoreTest 
{    String number=RandomStringUtils.random(1);
	Store payload;
	Faker fk;
    @BeforeClass
	public void setup()
	{
		fk=new Faker();
		payload=new Store();	
		payload.setId(5);
		payload.setPetId(fk.idNumber().hashCode());
		payload.setQuantity(25);
		payload.setShipDate("2023-11-03T05:42:06.082+0000");
		payload.setStatus("true");
		payload.setComplete("true");
		
	}
	@Test(priority = 1)
	public void CreateOrder_test()
	{
		Response res = StoreEndPoints.CreateNewOrder(payload);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
    	Assert.assertEquals(res.header("content-type"),"application/json");
    	
	}
	@Test(priority = 2)
	public void GetOrder_test()
	{
		
		Response res 	=StoreEndPoints.GetOrderInfoBy_OrderID(5);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
    	Assert.assertEquals(res.header("content-type"),"application/json");
		
    	String acualDate = res.jsonPath().get("shipDate").toString();
    	Assert.assertEquals(acualDate, "2023-11-03T05:42:06.082+0000");
	}
	@Test(priority = 3)
	public void validateschema_test()
	{
		
		Response res 	=StoreEndPoints.GetOrderInfoBy_OrderID(5);
		Assert.assertEquals(res.getStatusCode(), 200);
    	res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(".\\Scemafile\\storePostschema.json"));
		
	}
	
	@Test(priority = 4)
	public void DeleteOrder_test()
	{

		Response res 	=StoreEndPoints.GetOrderInfoBy_OrderID(5);
		Assert.assertEquals(res.getStatusCode(), 200);
    	Assert.assertEquals(res.header("content-type"),"application/json");
	}
}
