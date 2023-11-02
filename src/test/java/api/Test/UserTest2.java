package api.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.EndPoints.UserEndPoints;
import api.EndPoints.UserEndPoints2;
import api.Payload.User;
import api.utilities.ExtentReporteManager;
import io.restassured.response.Response;
@Listeners(ExtentReporteManager.class)
public class UserTest2 
{// this test accepts the urls from Properties file
	Faker faker;
	User payload;
	public Logger log;
    @BeforeClass
	public void SetUp()
	{ //data is created using faker class
    	faker=new Faker();
    	payload=new User();
    	
    	log=LogManager.getLogger(this.getClass());//got creating logs of test execution
    	
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
    {  log.debug("**** creating user ***");
    	//createing new user by passing data into respone body using POJO class
    	//here User is a pojo class which contains getters and setters to to assign and 
    	//retrive the data into variables
    	//payload is object of User class which we are apssing into create user method
    	Response response = UserEndPoints2.createUser(payload);
    	response.then().log().all();
    	Assert.assertEquals(response.getStatusCode(), 200);
    	Assert.assertEquals(response.header("content-type"),"application/json");
    	
    	log.debug("**** user created ***");
    }
    @Test(priority = 2)
    public void GetUser_test()
    {
    	log.debug("**** reading user data ***");
    Response response=UserEndPoints2.GetUser(this.payload.getUsername());
    	
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(), 200);
    Assert.assertEquals(response.header("server"), "Jetty(9.2.9.v20150224)");
    log.debug("**** user data sussesfully fetched ***");
    }
    
    @Test(priority = 3)    public void UpdateUser_test()
    {//while updating user we need to pass payload in response body = the uniqueid or username fo the user
    //we want to update
    //log().all()-->will get all the logs into console inclusing headers ,cookies,reponse body,statuscode 
    	 log.debug("**** updating user ***");
    	payload.setFirstName(faker.name().firstName());
    	payload.setLastName(faker.name().lastName());
    	payload.setEmail(faker.internet().safeEmailAddress());
    	
    	 Response response=   UserEndPoints2.UpdateUser(payload, this.payload.getUsername());
    	 response.then().log().body();
     	 Assert.assertEquals(response.getStatusCode(), 200);
         Response responseAfterupdate=UserEndPoints.GetUser(this.payload.getUsername());
    	
         responseAfterupdate.then().log().all();
         Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
         Assert.assertEquals(response.header("server"), "Jetty(9.2.9.v20150224)");
         log.debug("**** user updated ***");

    }
    @Test(priority = 4)
    public void DeleteUser_test()
    {
    	log.debug("**** deleting user ***");

    Response response=UserEndPoints2.DeleteUser(this.payload.getUsername());
    	
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(), 200);
    Assert.assertEquals(response.header("server"), "Jetty(9.2.9.v20150224)");
    log.debug("**** user deleted ***");

    }
    
}
