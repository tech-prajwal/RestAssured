package RestAPI;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class Advjsonparse {

	public static void main(String[] args) {
		
		JsonPath jp = new JsonPath(Payload.course());
		
		//count of courses
		int count = jp.getInt("courses.size()");
		System.out.println(count);
		
		//purchase amount
		int totalAmount = jp.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//title of first course
		String FirstTitle = jp.getString("courses[0].title");
		System.out.println(FirstTitle);
		
		//all title and prices
		for(int i=0;i<count;i++) {	
		String Title = jp.getString("courses["+i+"].title");
		int Price = jp.getInt("courses["+i+"].price");
		System.out.println(Title + ": "+ Price);
		}
		
		//no. of copies sold by RPA
		String reqTitle = "RPA";
		for(int i=0;i<count;i++) {
			String Title = jp.getString("courses["+i+"].title");
			if(Title.equalsIgnoreCase(reqTitle)) {
				int noOfCopies = jp.getInt("courses["+i+"].copies");
				System.out.println(noOfCopies);
				break;
			}
		}
		
		//verify sum of courses price matches with purchase amount
		int sum = 0;
		for(int i=0;i<count;i++) {
			int price = jp.getInt("courses["+i+"].price");
			int copies = jp.getInt("courses["+i+"].copies");
			int priceOfACourse = price*copies;
			
			sum = sum + priceOfACourse;
		}
	System.out.println(totalAmount);
	System.out.println(sum);
		
		
	}

}
