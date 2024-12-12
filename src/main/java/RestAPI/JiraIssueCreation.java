package RestAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.File;


public class JiraIssueCreation {

	public static void main(String[] args) {
		RestAssured.baseURI="https://prajwalorg.atlassian.net/";
		
		String IssueResp = given().header("Content-Type","application/json")
		.header("Authorization","Basic cHJhajAwNzk1NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwM1NCejJ3LUJmOU04MGVNelNDcnJfbm5YZzFmT1I4Sk9jaW1vVXY2YTdiX0w1NGV1VlJaWmVCQ1E5elNZdlFobTZnR1pha3BUa1otY21MUG5zQTk5NGVVR0t5M09IVnJlR0wxM25NS240ZTBvMU9UVVdydDJYYUtodUh3QmNXZ1d6WEE2MW5qTlBRTlpUdkF6M1lxTXItY25DUS0wVWVlMUZsWVVMQUlkdUlBPTFENzZFRThC")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Website Login Button is not displayed - Automation - RestAssured.\",\r\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "")
		.log().all()
		.when().post("rest/api/2/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js= new JsonPath(IssueResp);
		String bugId = js.getString("id");
		System.out.println(bugId);
		
		//Adding Attachment
		
		given()
		.pathParam("key", bugId)
		.header("Authorization","Basic cHJhajAwNzk1NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwM1NCejJ3LUJmOU04MGVNelNDcnJfbm5YZzFmT1I4Sk9jaW1vVXY2YTdiX0w1NGV1VlJaWmVCQ1E5elNZdlFobTZnR1pha3BUa1otY21MUG5zQTk5NGVVR0t5M09IVnJlR0wxM25NS240ZTBvMU9UVVdydDJYYUtodUh3QmNXZ1d6WEE2MW5qTlBRTlpUdkF6M1lxTXItY25DUS0wVWVlMUZsWVVMQUlkdUlBPTFENzZFRThC")
		.header("X-Atlassian-Token","no-check")
		.multiPart("file",new File("C:\\Users\\prajw\\eclipse-workspace\\RestAssured\\src\\main\\java\\files\\Issue_img.png")).log().all()
		.post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
	}

	
}
