package testCases;

 
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
 

public class CreateOneProduct {
	
	SoftAssert softAssert; 
	HashMap<String,String> createPayload;
	
	public CreateOneProduct() {
		softAssert= new SoftAssert();
		
	 	}
	
	public Map<String,String> createPayloadMap() {
		
		createPayload = new HashMap<String,String>();
		
		 
		createPayload.put("name", "Phillip's API Laptop1");
		createPayload.put("price", "2999");
		createPayload.put("description", "2TheFastest programming machine available. i9 11th gen processor.");
		createPayload.put("category_id", "2");
		System.out.println("createPayloadMap: " +createPayload.toString());
		return createPayload;
	}
	
	@Test
	public void createOneProduct() {
		
		Response response = 
		
		given()
 
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type","application/json; charset=UTF-8")
				.auth().preemptive().basic("demo@techfios.com", "abc123")
//				.body(new File("src\\main\\java\\data\\createPayload.json")).
				.body(createPayloadMap()).
		when()
 
				.post("/create.php ").
		then()
 
				.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("actualStatusCode: " + actualStatusCode);
//		Assert.assertEquals(actualStatusCode,  200);
		softAssert.assertEquals(actualStatusCode, 201, "Status codes are not matching!");
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader: " + actualHeader);
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8","Headers are not matching!");
		
		
		String actualResponseBody = response.getBody().asString() ; 
		System.out.println("actualResponseBody: " + actualResponseBody);
		
		JsonPath jp = new JsonPath(actualResponseBody);                        
		 
		String actualproductMessage = jp.get("message");
		System.out.println("actualproductMessage:"  + actualproductMessage);
		Assert.assertEquals(actualproductMessage, "Product was created.", "Product messages are not matching!");
		
		softAssert.assertAll();												//captures failed assertion
		
		
		 
			
		}
				
			
		
	}

 
