package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
		
		Faker faker;
		User userPayload;
		public Logger logger;
		
	
		@BeforeClass
		public void setup() {
			faker=new Faker();
			userPayload=new User();
			
			userPayload.setId(faker.idNumber().hashCode());
			userPayload.setUsername(faker.name().username());
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			userPayload.setPassword(faker.internet().password(5,10));
			userPayload.setPhone(faker.phoneNumber().cellPhone());
			
			//logs
			logger=LogManager.getLogger(this.getClass());
			
			logger.debug("debugging.....");
		}
		
		@Test(priority = 1)
		public void testPostUser(){
			logger.info("******Creating the user*******");
			Response response= UserEndPoints2.CreateUser(userPayload);
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(),200);
			logger.info("******User id is created*******");
		}
		
		@Test(priority = 2)
		public void testGetUserByName(){
			
			logger.info("******Reading the user info*******");
			Response response=UserEndPoints2.ReadUser(this.userPayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("******user info is displayed*******");
			
		}
		
		@Test(priority = 3)
		public void testUpdateUserByName(){
			logger.info("******Updating the user info*******");
			//update the data using the payload
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			
			
			Response response=UserEndPoints2.UpdateUser(this.userPayload.getUsername(),userPayload);
			response.then().log().body();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("******User Updated *******");
			
			//Validate whether the data sent is correct or not by using the get request
			Response responseAfterUpdate=UserEndPoints2.ReadUser(this.userPayload.getUsername());
			response.then().log().all();
			Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
			
			
		}
		
		@Test(priority = 4)
		public void deleteUserByName(){
			logger.info("******Deleting the user *******");
			Response respone=UserEndPoints2.DeleteUser(this.userPayload.getUsername());
			Assert.assertEquals(respone.getStatusCode(),200);
			logger.info("******User deleted*******");
			
		}
}
