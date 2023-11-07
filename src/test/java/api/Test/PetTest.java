package api.Test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.google.gson.JsonArray;

import api.EndPoints.PetEndPoints;
import api.Payload.Pet;
import io.restassured.response.Response;

public class PetTest 

{ 
	Faker fk;
	Pet payload;
	JSONObject data3;
   @BeforeClass
	public void setup()
	{  fk=new Faker();
		JSONObject data1=new JSONObject();
		data1.put("id", fk.number().digits(1));
		data1.put("name", fk.name().firstName());
		
		JSONArray array1=new JSONArray();
		array1.put(data1);
		
		JSONArray array2=new JSONArray();
		array2.put(fk.funnyName());
		
		data3=new JSONObject();
		data3.put("id", fk.number().digits(1));
		data3.put("category", data1);
		data3.put("name",fk.dog().name());
		data3.put("photoUrls", array2);
		data3.put("tags", array1);
		data3.put("status", "available");
		
	}
   @Test(priority = 1)
   public void createNewPET_test()
   {
	 Response res = PetEndPoints.CreatenewPet(data3.toString());
		
		res.then().log().body();
		Assert.assertEquals(res.statusCode(), 200);
	
   }
   @Test(priority = 2)
   public void GetinfoPET_test()
   {
	 Response res = PetEndPoints.getpetinfobyid(data3.getString("id"));
		
		res.then().log().body();
		Assert.assertEquals(res.statusCode(), 200);
	
   }
  
   @Test(priority = 3)
   public void deletePetingo_test()
   {
	 Response res = PetEndPoints.deletepetbyid(data3.getString("id"));
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
	
   }
	
}
