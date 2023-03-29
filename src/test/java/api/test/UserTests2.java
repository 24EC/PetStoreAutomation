package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {


	Faker faker;
	User userPayload;

	public Logger logger;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());


		// logs
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority=1)
	public void testPostUser() {
		logger.info("***************creating user************");
		Response response = UserEndPoints2.CreateUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************creating user************");
	}

	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("***************get user************");
		Response response = UserEndPoints2.ReadUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************get user************");
	}

	@Test(priority=3)
	public void testUpdateUserByName() {
		//update data using payload
		logger.info("***************update user************");

		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		logger.info("***************update user************");
		Response response = UserEndPoints2.UpdateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		logger.info("***************update user************");
		Assert.assertEquals(response.getStatusCode(),200);
		//checking data after update
		Response responseAfterupdate = UserEndPoints2.ReadUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
		logger.info("***************update user************");
	}

	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("***************delete user************");

		Response response = UserEndPoints2.DeleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************delete user************");


	}
}

