package main.java.chat.apis;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * 
 * This class implements the Wikipedia API
 * @author Madison Cunning
 *
 */
public class ActuallyWiki 
{
	
	private final String baseUrl = "https://en.wikipedia.org/w/api.php?action=opensearch&search=";
	private String userQuery = "";
	private String fullUrl = "";
	
	public ActuallyWiki(){}

	/**
	 * 
	 * @param query reads in user query
	 * @return information from the specified wiki page
	 * @throws IOException
	 */
	public String getWikiInfo(String query) throws IOException
	{
		
		userQuery = URLEncoder.encode(query, "UTF-8"); 
		userQuery = userQuery.replaceAll(" ", "+");
		fullUrl = baseUrl + userQuery;
		
		URL myUrl = new URL(fullUrl);
		
		InputStream is = myUrl.openStream();
		
		JSONTokener token = new JSONTokener(is);
		JSONObject result = new JSONObject(token);

		is.close();
		
		
		return(String) ((JSONObject) result.getJSONObject("query").getJSONObject("pages")).get("extract");		
		
	}
	
	
}
