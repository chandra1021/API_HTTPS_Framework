package restapicom.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.APIBase;
import com.qa.client.RestClient;
import com.qa.data.UsersPojoClass;

public class PostAPITest extends  APIBase{

	String URL;
	RestClient restClient;

	@BeforeMethod
	public void setUp() {
		//APIBase apiBase = new APIBase();// this is to initialize the prop file to fetch data
		URL= prop.getProperty("URL")+prop.getProperty("serviceURL");
	}

	@Test
	public void postApiTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		/* Add all the needed Headers into HashMap*/
		HashMap<String, String> headermap= new HashMap<String,String >();
		headermap.put("Content-Type", "application/json");

		/* we need to use Jackson API jar files to convert POJO to JSON */
		ObjectMapper mapper = new ObjectMapper();
		UsersPojoClass usersPojoClass = new UsersPojoClass("tom","Leader");

		/* Object to JSON File */
		mapper.writeValue(new File(System.getProperty("user.dir")+"/src/main/java/com/qa/data/TestData.json"),usersPojoClass);

		/* JSON file to String conversion */
		String userJSONString = mapper.writeValueAsString(usersPojoClass);
		System.out.println(userJSONString);


		CloseableHttpResponse closebaleHttpResponse = restClient.post(URL,userJSONString,headermap);

		System.out.println(closebaleHttpResponse.getStatusLine().getStatusCode());
		
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Response JSON is ----> "+responseJSON);
		
		/* JSON to Java Object */
		UsersPojoClass userObj = mapper.readValue(responseString, UsersPojoClass.class);
		System.out.println(userObj.getJob());
		System.out.println(userObj.getName());
		System.out.println(userObj.getCreatedAt());
		System.out.println(userObj.getId());
		

	}
}
