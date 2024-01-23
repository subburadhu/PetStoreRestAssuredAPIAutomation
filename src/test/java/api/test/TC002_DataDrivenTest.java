package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class TC002_DataDrivenTest {
	
	@Test(priority=1, dataProvider="CreateUserData", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID,String userName,String fName,String lName,String userEmail, String pwd, String phoneNo) 
	{
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phoneNo);
		
		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

	
	@Test(priority=2, dataProvider="GetUserNamesData", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) 
	{
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
}
