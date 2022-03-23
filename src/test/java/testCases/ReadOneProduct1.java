package testCases;

 
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
 

public class ReadOneProduct1 {
	
	SoftAssert softAssert;   
	
	public ReadOneProduct1() {
		softAssert= new SoftAssert();
		
	}
	
	
	
	@Test
	public void readOneProduct() {
		
		 
		
		
		
		Response response = 
		
		given()
 
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type","application/json")
				.auth().preemptive().basic("demo@techfios.com", "abc123")
				.queryParam("id", "3780").
				 
		when()
 
				.get("/read_one.php").
		then()
 
				.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		System.out.println("actualStatusCode: " + actualStatusCode);
//		Assert.assertEquals(actualStatusCode,  200);
		softAssert.assertEquals(actualStatusCode, 200, "Status codes are not matching!");
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println("actualHeader: " + actualHeader);
		softAssert.assertEquals(actualHeader, "application/json", "Headers are not matching!");
		
		
		String actualResponseBody = response.getBody().asString() ; 
		System.out.println("actualResponseBody: " + actualResponseBody);
		
		JsonPath jp = new JsonPath(actualResponseBody);                        
		 
		String actualproductId = jp.get("id");
		System.out.println("actualproductId:"  + actualproductId);
		softAssert.assertEquals(actualproductId , "3780", "Product Id's are not matching!");
		
		String actualproductName = jp.get("name");
		System.out.println("actualproductName:"  + actualproductName);
		softAssert.assertEquals(actualproductName , "Phillip's API Laptop1", "Product names are not matching!");
		
		String actualproductPrice = jp.get("price");
		System.out.println("actualproductPrice:"  + actualproductPrice);
		softAssert.assertEquals(actualproductPrice , "2999", "Product prices are not matching!");
		
		softAssert.assertAll();												//captures failed assertion
		
		
		 
			
		}
				
			
		
	}

 
