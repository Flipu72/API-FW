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
 
public class UpdateOneProduct {
	
	SoftAssert softAssert; 
	HashMap<String,String> updatePayload;
	
	public UpdateOneProduct() {
		softAssert = new SoftAssert();
	}
	
	public Map<String,String> updatePayloadMap() {
		
		updatePayload = new HashMap<String,String>();
		
		updatePayload.put("id", "3782");
		updatePayload.put("name", "Phillip's API Laptop22");
		updatePayload.put("price", "4999");
		updatePayload.put("description", "The new and improved Fastest programming machine available. i9 11th gen processor.");
		updatePayload.put("category_id", "2");
		System.out.println("updatePayloadMap: " +updatePayload.toString());
		return updatePayload;
	}
	
	@Test
	public void updateOneProduct() {
		
		Response response = 
		
		given()
 
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type","application/json; charset=UTF-8")
				.auth().preemptive().basic("demo@techfios.com", "abc123")
//				.body(new File("src\\main\\java\\data\\createPayload.json")).
				.body(updatePayloadMap()).
		when()
 
				.put("/update.php ").
		then()
 
				.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("actualStatusCode: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, 200, "Status codes are not matching!");
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader: " + actualHeader);
		softAssert.assertEquals(actualHeader, "application/json; charset=UTF-8","Headers are not matching!");
		
		
		String actualResponseBody = response.getBody().asString() ; 
		System.out.println("actualResponseBody: " + actualResponseBody);
		
		JsonPath jp = new JsonPath(actualResponseBody);                        
		 
		String actualproductMessage = jp.get("message");
		System.out.println("actualproductMessage:"  + actualproductMessage);
		softAssert.assertEquals(actualproductMessage, "Product was updated.", "Product messages are not matching!");
		
		softAssert.assertAll();												//captures failed assertion
		
		
		 
			
		}
		
	}

 
