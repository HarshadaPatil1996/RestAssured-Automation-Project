package api.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import api.EndPoints.UserEndPoints;
import api.Payload.User;
import io.restassured.response.Response;

public class UserTest 
{
	Faker faker;
	User payload;
	
    @BeforeClass
	public void SetUp()
	{ 
    	faker=new Faker();
    	payload=new User();
    	
    	payload.setId(faker.idNumber().hashCode());
    	payload.setUsername(faker.name().username());
    	payload.setFirstName(faker.name().firstName());
    	payload.setLastName(faker.name().lastName());
    	payload.setEmail(faker.internet().safeEmailAddress());
    	payload.setPassword(faker.internet().password(5, 10));
    	payload.setPhone(faker.phoneNumber().cellPhone());
    	
	}
    @Test(priority = 1)
   public void Test_postUser()
    {
    	//createing new user by passing data into respone body using POJO class
    	//here User is a pojo class which contains getters and setters to to assign and 
    	//retrive the data into variables
    	//payload is object of User class which we are apssing into create user method
    	Response response = UserEndPoints.createUser(payload);
    	response.then().log().all();
    	Assert.assertEquals(response.getStatusCode(), 200);
    	Assert.assertEquals(response.header("content-type"),"application/json");
    	
    	
    }
    @Test(priority = 2)
    public void GetUser_test()
    {
    	
    Response response=UserEndPoints.GetUser(this.payload.getUsername());
    	
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(), 200);
    Assert.assertEquals(response.header("server"), "Jetty(9.2.9.v20150224)");
    	
    }
    
    @Test(priority = 3)
    public void UpdateUser_test()
    {//while updating user we need to pass payload in response body = the uniqueid or username fo the user
    //we want to update
    //log().all()-->will get all the logs into console inclusing headers ,cookies,reponse body,statuscode 
    	payload.setFirstName(faker.name().firstName());
    	payload.setLastName(faker.name().lastName());
    	payload.setEmail(faker.internet().safeEmailAddress());
    	
    	 Response response=   UserEndPoints.UpdateUser(payload, this.payload.getUsername());
    	 response.then().log().body();
     	 Assert.assertEquals(response.getStatusCode(), 200);
         Response responseAfterupdate=UserEndPoints.GetUser(this.payload.getUsername());
    	
         responseAfterupdate.then().log().all();
         Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
         Assert.assertEquals(response.header("server"), "Jetty(9.2.9.v20150224)");
    	
    }
    @Test(priority = 4)
    public void DeleteUser_test()
    {

    Response response=UserEndPoints.DeleteUser(this.payload.getUsername());
    	
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(), 200);
    Assert.assertEquals(response.header("server"), "Jetty(9.2.9.v20150224)");
    	
    }
    
}
