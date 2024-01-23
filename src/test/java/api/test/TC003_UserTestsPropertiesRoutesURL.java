package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserRoutePropertiesURL;
import api.payload.User;
import io.restassured.response.Response;

public class TC003_UserTestsPropertiesRoutesURL {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() 
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void verify_postUser() 
	{
	
		logger.info("********* Creating User *************");
		Response response = UserRoutePropertiesURL.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********* User is created *************");
	}
	
	@Test(priority=2)
	public void verify_GetUserByName()
	{
		logger.info("********* Reading User Info *************");
		Response response = UserRoutePropertiesURL.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********* User info is displayed *************");
	}
	
	@Test(priority=3)
	public void verify_updateUserByName() 
	{
	
		logger.info("********* Updating User Information *************");
		
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
				
		Response responseAfterUpdate = UserRoutePropertiesURL.updateUser(this.userPayload.getUsername(),userPayload);
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("********* User updated *************");
	}
	
	@Test(priority=4)
	public void verify_deleteUserByName()
	{
		logger.info("********* Deleting User *************");
		Response response = UserRoutePropertiesURL.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********* Deleted User *************");
	}
	
}
