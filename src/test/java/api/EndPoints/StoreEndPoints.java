package api.EndPoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.Payload.Store;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class StoreEndPoints 
{
//create method/requests
	//post and put request nedd body-payload with pre-riquisite
	static ResourceBundle geturl()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		
		return routes;
	}
	
	public static Response CreateNewOrder(Store payload)
	{//no authorization needed
		Response response= given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.post(geturl().getString("Post_url_store"));
		return response;
	}
	
	public static Response GetOrderInfoBy_OrderID(String id)
	{//no authorization needed
		Response response= given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("id", id)
		.get(geturl().getString("Get_url_store"));
		return response;
	}
	
	public static Response UpdateOrderBy_OrderID(Store payload,String  id)
	{//no authorization needed
		Response response= given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("id", id)
		.put(geturl().getString("Put_url_store"));
		return response;
	}
	
	public static Response DeleteOrderBy_OrderID(String id)
	{//no authorization needed
		Response response= given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("id", id)
		.delete(geturl().getString("Delete_url_store"));
		return response;
	}
	
}
