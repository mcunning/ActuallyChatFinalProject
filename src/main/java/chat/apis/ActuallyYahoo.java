package main.java.chat.apis;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * 
 * This class implements the Yahoo! Answers API
 * @author Madison Cunning
 *
 */
public class ActuallyYahoo 
{
	private final String baseUrl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20answers.search%20where%20query%3D%22";
	private final String baseUrl2 = "%22%20and%20type%3D%22resolved%22&format=json&diagnostics=true&callback=";
	private String userSearch = "";
	private String fullUrl = "";
	
	public ActuallyYahoo(){}
	
/**
 * 
 * @param query is the information that the user enters to be searched in Yahoo!Answers
 * @return returns the chosen answer of the specified query
 * @throws IOException
 */
	public String getYahooInfo(String query) throws IOException
	{
		userSearch = URLEncoder.encode(query, "UTF-8");
		fullUrl = baseUrl + userSearch + baseUrl2;
		JSONObject result = new JSONObject();
		URL myUrl = new URL(fullUrl);
		
		Exception ex = null;

		do
		{
			ex = null;
			InputStream is = null;
			try
			{
				is = myUrl.openStream(); 
				JSONTokener token = new JSONTokener(is);
				result = new JSONObject(token);
				is.close();
			}
			catch (Exception e)
			{
				System.out.println("Still looking...");
				ex = e; 
			}
		}
		while (!(ex == null));
		
		return(String) ((JSONObject) result.getJSONObject("query").getJSONObject("results").getJSONArray("Question").get(1)).get("ChosenAnswer");		
	}
}
