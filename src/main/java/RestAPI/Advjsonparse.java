package RestAPI;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class Advjsonparse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath jp = new JsonPath(Payload.course());
		//count of courses
		int count = jp.getInt("courses.size()");
		System.out.println(count);
		//purchase amount
		int amount = jp.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		
		
	}

}
