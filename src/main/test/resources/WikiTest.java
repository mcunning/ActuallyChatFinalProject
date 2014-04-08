package main.test.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class WikiTest 
{
	public WikiTest()
	{
		
	}
	public static void main(String args[]) throws IOException
	{
		String baseUrl = "https://en.wikipedia.org/w/api.php?action=opensearch&search=";
		String userQuery = "syrup";
		
		userQuery = userQuery.replaceAll(" ", "+");
		String fullUrl = baseUrl + userQuery;
		URL myUrl = new URL(fullUrl);
		
		InputStream is = myUrl.openStream();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		

		String newLine = "";
		newLine = reader.readLine();
		String[] result;
		String stop = "\"";
		result = newLine.split(stop);
		String search = "";

		for (int i=0; i<result.length; i++)
		{
			result[i] = result[i].replaceAll(" ", "+");
			System.out.println(result[i]);
			if(result[i].contains(search))
			{
				String tempResult = result[i];
				break; //break after get result
			}
		}
		
		String usable = result[3];
		String URLSearch = "https://en.wikipedia.org/w/api.php?action=query&prop=extracts&format=json&exlimit=10&exintro&explaintext&titles=";
		String totalURL = URLSearch + usable;
		
		URL myUrl2 = new URL(totalURL);
		
		InputStream is2 = myUrl2.openStream();
		BufferedReader streamReader = new BufferedReader(new InputStreamReader(is2, "UTF-8"));
		StringBuilder responseStringBuilder = new StringBuilder();
		
		String inputString; 
		while ((inputString = streamReader.readLine())!=null)
		{
			responseStringBuilder.append(inputString);
		}
		
		JSONObject json = new JSONObject(responseStringBuilder.toString());
		JSONObject query = json.getJSONObject("query");
		JSONObject pages = query.getJSONObject("pages");
		JSONObject nestedObject = null;
		
		String keys[] = pages.getNames(pages);
		
		for(int i=0; i<keys.length; i++)
		{
			try
			{
				nestedObject = pages.getJSONObject(keys[i]);
				if(nestedObject.has("pageid"))
				{
					break;
				}
			}
			catch (JSONException e)
			{
				
			}
		}
		
		String str = nestedObject.getString("extract");
		if (str!="")
		{
			System.out.println(str);
		}
		else
		{
			System.out.println("Not found");
		}
//		return (String) pages.get("name");
	}
}
