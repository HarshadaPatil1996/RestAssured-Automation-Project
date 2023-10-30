package api.EndPoints;
import static io.restassured.RestAssured.given;

import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UserEndPoints 
{
	
	public static Response createUser(User payload)
	{//create new user save the response in a variable and return the response
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)	
		.when()
		.post(Routes.Post_url);
		return response;
	}
	
	public static Response 	GetUser(String username)
	{
		Response response = given()
		.pathParam("username", username)
		.when()
		.get(Routes.Get_url);
		return response;
	}
	
	public static Response UpdateUser(User payload, String username)
	{
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", username)
		.when()
		.put(Routes.Put_url);
		return response;
	}

	public static Response 	DeleteUser(String username)
	{
		Response response = given()
		.pathParam("username", username)
		.when()
		.delete(Routes.Delete_url);
		return response;
	}
}
