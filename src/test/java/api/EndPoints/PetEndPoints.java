package api.EndPoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.Payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints 
{
    public static ResourceBundle geturl()
    {
    	ResourceBundle routes=ResourceBundle.getBundle("routes");	
    	return routes;
    }
    
    public static Response CreatenewPet(String data)
    { String posturl= geturl().getString("Post_url_pet");
    
    	Response res = given()
    	.contentType(ContentType.JSON)
    	.accept(ContentType.JSON)
    	.body(data)
    	.when()
    	.post(posturl);
		return res;
    	
    }
    

	public static Response getpetinfobyid(String id)
    { String geturl= geturl().getString("Get_url_pet");
    	Response res = given()
    	.contentType(ContentType.JSON)
    	.accept(ContentType.JSON)
    	.pathParam("id", id)
    	.when()
    	.get(geturl);
		return res;
    	
    }
	

	public static Response deletepetbyid(String id)
    { String deleteurl= geturl().getString("Delete_url_pet");
    	Response res = given()
    	.contentType(ContentType.JSON)
    	.accept(ContentType.JSON)
    	.pathParam("id", id)
    	.when()
    	.delete(deleteurl);
		return res;
    	
    }
}
