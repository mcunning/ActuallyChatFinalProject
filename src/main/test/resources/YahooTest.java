package main.test.resources;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.json.JSONTokener;

public class YahooTest 
{
	public static void main(String[] args) throws IOException
	{
		String baseUrl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20answers.search%20where%20query%3D%22";
		String baseUrl2 = "%22%20and%20type%3D%22resolved%22&format=json&diagnostics=true&callback=";
		String userTimeline = "shave legs";
		
		userTimeline = URLEncoder.encode(userTimeline, "UTF-8");
		String fullUrl = baseUrl + userTimeline + baseUrl2;
		URL myUrl = new URL(fullUrl);
		
		InputStream is = myUrl.openStream();
		JSONTokener tok = new JSONTokener(is);
		JSONObject result = new JSONObject(tok);
		
		is.close();
		
		System.out.println(((JSONObject) result.getJSONObject("query")
				.getJSONObject("results").getJSONArray("Question").get(1)).get("ChosenAnswer"));	
	}	
}
