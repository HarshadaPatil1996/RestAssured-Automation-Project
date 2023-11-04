package api.Test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.EndPoints.UserEndPoints;
import api.Payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDriven_userTest
{//in the annotaions, we need to provide priority, data provider name, data provider class name
//class name is only nessesary if the dataprovider class is in diff. package form test class
	@Test(priority = 1,dataProvider = "alldata", dataProviderClass = DataProviders.class)
	public void Createusers_test(String userId,String username,String fname,String lname,String email,String pwd,String phone)
	{// in the method paranthesis, we need to declare all variables and datatypes we will get from excel sheet
		User payload=new User();
		payload.setId(Integer.parseInt(userId));
    	payload.setUsername(username);
    	payload.setFirstName(fname);
    	payload.setLastName(lname);
    	payload.setEmail(email);
    	payload.setPassword(pwd);
    	payload.setPhone(phone);
		
		
	    Response response = UserEndPoints.createUser(payload);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.header("Content-Type"), "application/json");
	
	}
	
	@Test(priority = 2, dataProvider = "usernames", dataProviderClass = DataProviders.class)

	public void DeleuserByusername_test(String userName)
	{
		Response response =UserEndPoints.DeleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
        ;
		
	}

}
