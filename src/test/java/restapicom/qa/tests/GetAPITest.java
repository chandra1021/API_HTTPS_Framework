package restapicom.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.APIBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends  APIBase{

	String URL;
	RestClient restClient;

	@BeforeMethod
	public void setUp() {
		//APIBase apiBase = new APIBase();// this is to initialize the prop file to fetch data
		URL= prop.getProperty("URL")+prop.getProperty("serviceURL");
	}

	@Test(enabled = false)
	public void testMethod() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		CloseableHttpResponse closebaleHttpResponse = restClient.get(URL);
		
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is ----> "+statusCode);

		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");


		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Response JSON is ----> "+responseJSON);
		
		/* This utility will fetch the value of the single data in JSON response */
		String per_page = TestUtil.getValueByJPath(responseJSON, "per_page");
		System.out.println("per_page value is ---> "+per_page);
		
		
		/* get the values of JSON Array */
		String last_name = TestUtil.getValueByJPath(responseJSON, "/data[10]/last_name");
		String id = TestUtil.getValueByJPath(responseJSON, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJSON, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name");
		
		System.out.println("===========================================");
		System.out.println(last_name);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);
		System.out.println("===========================================");
		
		/* get the Header from JSON Response into HashMap*/
		Header[] headerArray = closebaleHttpResponse.getAllHeaders();
		HashMap<String, String> HeadersList = new HashMap<String,String>();

		for (Header header : headerArray) {
			HeadersList.put(header.getName(), header.getValue());
		}
		System.out.println("Headers list id ---> "+HeadersList);
		
	}
	
	@Test
	public void testMethodWithHeadersIncluded() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		/* Add all the needed Headers into HashMap*/
		HashMap<String, String> headermap= new HashMap<String,String >();
		headermap.put("Content-Type", "aplication/json");
		
		
		
		CloseableHttpResponse closebaleHttpResponse = restClient.get(URL,headermap);
		
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is ----> "+statusCode);

		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");


		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Response JSON is ----> "+responseJSON);
		
		/* This utility will fetch the value of the single data in JSON response */
		String per_page = TestUtil.getValueByJPath(responseJSON, "per_page");
		System.out.println("per_page value is ---> "+per_page);
		
		
		/* get the values of JSON Array */
		String last_name = TestUtil.getValueByJPath(responseJSON, "/data[1]/last_name");
		String id = TestUtil.getValueByJPath(responseJSON, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJSON, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name");
		
		System.out.println("===========================================");
		System.out.println(last_name);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);
		System.out.println("===========================================");
		
		/* get the Header from JSON Response into HashMap*/
		Header[] headerArray = closebaleHttpResponse.getAllHeaders();
		HashMap<String, String> HeadersList = new HashMap<String,String>();

		for (Header header : headerArray) {
			HeadersList.put(header.getName(), header.getValue());
		}
		System.out.println("Headers list id ---> "+HeadersList);
		
	}
}
