package testCases;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
 

public class DeleteOneProduct {
	
	SoftAssert softAssert; 
	HashMap<String,String> deletePayload;
	
	public DeleteOneProduct() {
		softAssert = new SoftAssert();
	}
	
	public Map<String,String> deletePayloadMap() {
		
		deletePayload = new HashMap<String,String>();
		
		deletePayload.put("id", "2");
		 
		System.out.println("deletePayloadMap: " +deletePayload.toString());
		return deletePayload;
	}
	
	 
	
	
	@Test
	public void deleteOneProduct() {
		
		Response response = 
		
		given()
 
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type","application/json; charset=UTF-8")
				.auth().preemptive().basic("demo@techfios.com", "abc123")
				.body(deletePayloadMap()).
				 
		when()
 
				.delete("/delete.php").
		then()
 
				.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("actualStatusCode: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, 200, "Status codes are not matching!");
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader: " + actualHeader);
		softAssert.assertEquals(actualHeader, "application/json; charset=UTF-8");
		
		
		String actualResponseBody = response.getBody().asString() ; 
		System.out.println("actualResponseBody: " + actualResponseBody);
		
		JsonPath jp = new JsonPath(actualResponseBody);                        
		 
/*		String actualproductId = jp.get("id");
		System.out.println("actualproductId:"  + actualproductId);
		softAssert.assertEquals(actualproductId , "2");*/
		
		String actualproductMessage = jp.get("message");
		System.out.println("actualproductMessage:"  + actualproductMessage);
		softAssert.assertEquals(actualproductMessage, "Product was deleted.", "Product messages are not matching!"); 
		
		softAssert.assertAll();												//captures failed assertion
		
		
		 
			
		}
				
			
		
	}

 
