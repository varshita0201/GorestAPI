package api.test;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.EndPoints;
import api.payload.User;

public class UserTest {
	Faker f;
	User userpayload;
	public Logger log;
	int id;
	
	@BeforeClass
	public void setData() {
		
		log=LogManager.getLogger("User");
		f=new Faker();
		userpayload=new User();
		userpayload.setName(f.name().fullName());
		userpayload.setEmail(f.internet().emailAddress());
		userpayload.setGender("male");
		userpayload.setStatus("active");
	}
	
	@Test(priority=1)
	public void testpostUser() {
		
		Response res=EndPoints.createUser(userpayload);
		//res.then().log().body();
		res.then().statusCode(201);
		id=res.jsonPath().getInt("id");
		Assert.assertEquals(res.statusCode(), 201);
		log.info("User is successfully posted");
		
	}
	
	@Test(dependsOnMethods="testpostUser")
	public void testgetUser() {
	
		Response res=EndPoints.getUser(id);
		res.then().log().body();
		res.then().statusCode(200);
		Assert.assertEquals(res.statusCode(), 200);
		
		log.info("User details succesfully displayed");
		
	}
	
	@Test(priority=2,dependsOnMethods="testpostUser")
	public void testupdateUser() {
		userpayload.setName(f.name().fullName());
		userpayload.setEmail(f.internet().emailAddress());
		userpayload.setGender("female");
		userpayload.setStatus("active");
		Response res=EndPoints.updateUser(id,userpayload);
		//res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		Response data=EndPoints.getUser(id);
		data.then().log().body();
		log.info("User details Updated");
		
	}
	
	@Test(priority=3)
	public void testDelUser() {
		Response res=EndPoints.delUser(id);
		Assert.assertEquals(res.getStatusCode(), 204);
		log.info("User deleted");
	}
}
