package api.endpoints;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payload.User;

public class EndPoints {
	public static String bearer="e6b3d74e1b5e886bbdc25dc1aa77681ef872a2ffeb42644e1d9b502d0e3edfb0";
	public static Response createUser(User payload) {
		Response res=given()
				.contentType("application/json")
				.headers("Authorization","Bearer "+bearer)
				.body(payload)
		.when()
			.post(Routes.post_url);
		return res;
	}
	
	public static Response getUser(int id) {
			Response res=given()
					.headers("Authorization","Bearer "+bearer)
					.pathParam("userid", id)
				.when()
					.get(Routes.get_url);
			return res;
	}
	
	public static Response updateUser(int id,User payload) {
		Response res=given()
				.headers("Authorization","Bearer "+bearer)
				.contentType("application/json")
				.pathParam("userid",id)
				.body(payload)
			.when()
				.put(Routes.put_url);
		return res;
	}
	
	public static Response delUser(int id) {
		Response res=given()
				.headers("Authorization","Bearer "+bearer)
				.pathParam("userid", id)
			.when()
				.delete(Routes.delete_url);
		return res;
	}
}
