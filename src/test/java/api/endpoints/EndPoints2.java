package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.Response;

public class EndPoints2 {
	public static String bearer="e6b3d74e1b5e886bbdc25dc1aa77681ef872a2ffeb42644e1d9b502d0e3edfb0";
	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	public static Response postUser(User payload) {
		String post_url=getURL().getString("post_url");
		Response res=given()
				.headers("Authorization","Bearer "+bearer)
				.contentType("application/json")
				.body(payload)
			.when()
				.post(post_url);
		return res;
	}
	
	public static Response getUser(int id) {
		String get_url=getURL().getString("get_url");
		Response res=given()
				.headers("Authorization","Bearer "+bearer)
				.pathParam("userid",id)
			.when()
				.get(get_url);
		return res;
	}
	
	public static Response updateUser(int id,User payload) {
		String put_url=getURL().getString("put_url");
		Response res=given()
					.headers("Authorization","Bearer "+bearer)
					.pathParam("userid", id)
					.contentType("application/json")
					.body(payload)
				.when()
					.put(put_url);
		return res;
	}
	
	public static Response delUser(int id) {
		String delete_url=getURL().getString("delete_url");
		Response res=given()
					.headers("Authorization","Bearer "+bearer)
					.pathParam("userid", id)
				.when()
					.delete(delete_url);
		return res;
	}
}
