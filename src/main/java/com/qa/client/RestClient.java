package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {



	// GET Method without request Headers:
	public void get_CallWithJSONResponse(String url) throws ClientProtocolException, IOException{

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request

		/** closebaleHttpResponse will contain all the data related to response , we can use when ever needed**/
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL

		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is ----> "+statusCode);

		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");


		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Response JSON is ----> "+responseJSON);

		Header[] headerArray = closebaleHttpResponse.getAllHeaders();
		HashMap<String, String> HeadersList = new HashMap<String,String>();

		for (Header header : headerArray) {
			HeadersList.put(header.getName(), header.getValue());
		}
		System.out.println("Headers list id ---> "+HeadersList);

	}



	// GET Method without Headers: returns Response
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
		return closebaleHttpResponse; 

	}

	//GET Method with Headers:
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request

		/* Adding headers to the GET call */
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
		return closebaleHttpResponse;

	}

	// POST Method:
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//HttpPost httppost = new HttpPost(url); //http post request
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString)); //for payload creation

		//for headers:
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			httppost.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
		return closebaleHttpResponse;


	}

}
