package api.test;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.EndPoints2;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTest2 {
	User userpayload; 
	int id;
	Logger log;
	@BeforeClass()
	public void before() {
		log=LogManager.getLogger("User");
	}
	@Test(priority=1,dataProvider="alldata",dataProviderClass=DataProviders.class)
	public void createNewUser(String name,String email,String gender,String status) {
		userpayload=new User();
		userpayload.setName(name);
		userpayload.setEmail(email);
		userpayload.setGender(gender);
		userpayload.setStatus(status);
		System.out.println(userpayload.getEmail());
		Response res=EndPoints2.postUser(userpayload);
		res.then().log().body();
		id=res.jsonPath().getInt("id");
		System.out.println(id);
		Assert.assertEquals(res.getStatusCode(), 201);
		log.info("Created New User");
		
	}
	
	@Test(priority=2,dependsOnMethods="createNewUser")
	public void getUser() {
		Response res=EndPoints2.getUser(id);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		log.info("User Details");
	}
	
	@Test(priority=3,dataProvider="updateddata",dataProviderClass=DataProviders.class)
	public void updateUser(String name,String email,String gender,String status) {
		userpayload=new User();
		userpayload.setName(name);
		userpayload.setEmail(email);
		userpayload.setGender(gender);
		userpayload.setStatus(status);
		Response res=EndPoints2.updateUser(id, userpayload);
		Assert.assertEquals(res.getStatusCode(),200 );
		res.then().log().body();
		log.info("User Details Updated");
	}
	
	@Test(priority=4)
	public void delUser() {
		Response res=EndPoints2.delUser(id);
		Assert.assertEquals(res.getStatusCode(), 200);
		log.info("User id Deleted");
	}
}
