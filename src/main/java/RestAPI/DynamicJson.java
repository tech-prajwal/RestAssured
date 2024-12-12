package RestAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson {

	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) throws IOException{
		RestAssured.baseURI="http://216.10.245.166";
		Response resp=given().header("Content-Type","application/json").
		body(new String(Files.readAllBytes(Paths.get("C:\\Users\\prajw\\eclipse-workspace\\RestAssured\\src\\main\\java\\files\\addplace.json")))).
		when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response();
		 String responseBody = resp.getBody().asString();
		 System.out.println("Response Body: " + responseBody);
		JsonPath js= new JsonPath(responseBody);
		String id=js.get("ID");
		System.out.println(id);
}
	
	@DataProvider(name = "BooksData")
    public Object[][] getData() {
        return new Object[][] {
            {"ojfwty", "9363"},
            {"cwetee", "4253"},
            {"okmfet", "533"}
        };
    }
}
	