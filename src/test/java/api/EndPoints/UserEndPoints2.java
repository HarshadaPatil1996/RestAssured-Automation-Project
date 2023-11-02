package api.EndPoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints2 
{
	
	static ResourceBundle geturl()
	{// this method is created to get datta from properties file which is located in src/test/resources
		
		ResourceBundle routes=ResourceBundle.getBundle("routes");//load Propertirs file
		return routes;//retrun the string data 
	}
	
	public static Response createUser(User payload)
	{//create new user save the response in a variable and return the response
		
		String Post_url=geturl().getString("Post_url");
		//get the string kry from PF fle store it in string variable
	    //and that variable ue will use in post req.
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)	
		.when()
		.post(Post_url);
		return response;
	}
	
	public static Response 	GetUser(String username)
	{
		String Get_url=geturl().getString("Get_url");
		Response response = given()
		.pathParam("username", username)
		.when()
		.get(Get_url);
		return response;
	}
	
	public static Response UpdateUser(User payload, String username)
	{	
		String Put_url=geturl().getString("Put_url");
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", username)
		.when()
		.put(Put_url);
		return response;
	}

	public static Response 	DeleteUser(String username)
	{String Delete_url=geturl().getString("Delete_url");
		Response response = given()
		.pathParam("username", username)
		.when()
		.delete(Delete_url);
		return response;
	}
}
