package main.java.chat.apis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONTokener;


/**
 * 
 * This class implements the Facebook API
 * @author Madison Cunning
 * @version 1.0
 *
 */
public class ActuallyFacebook
{

	private final String baseUrl = "https://graph.facebook.com/";
	private String user = "";
	private String fullUrl = "";
	private InputStream is;
	
	public ActuallyFacebook(){}
	
	/**
	 * 
	 * This returns the first name of the Facebook user that the chatBot user is searching
	 * @param name is the username that chatBot user inputs
	 * @return this user's first name
	 * @throws IOException
	 */
	public String getFirstName(String name) throws IOException
	{
		user = name;
		fullUrl = baseUrl + user;

		URL myUrl = new URL(fullUrl);
		try
		{
			is = myUrl.openStream();
		}
		catch(FileNotFoundException e)
		{
			return "File Not Found";
		}
		
		JSONTokener token = new JSONTokener(is);
		JSONObject result = new JSONObject(token);
		
		is.close();
		return(String) result.get("first_name");		
	}
	
	/**
	 * 
	 * This returns the last name of the Facebook user that the chatBot user is searching
	 * @param name is the username that chatBot user inputs
	 * @return this user's last name
	 * @throws IOException
	 */
	public String getLastName(String name) throws IOException
	{
		user = name;
		fullUrl = baseUrl + user;

		URL myUrl = new URL(fullUrl);
		try
		{
			is = myUrl.openStream();
		}
		catch(FileNotFoundException e)
		{
			return "File Not Found";
		}
		
		JSONTokener token = new JSONTokener(is);
		JSONObject result = new JSONObject(token);
		
		is.close();
		return(String) result.get("last_name");
	}
	
	/**
	 *
	 * This returns the id of the Facebook user that the chatBot user is searching
	 * @param name is the username that chatBot user inputs
	 * @return this user's Facebook ID
	 * @throws IOException
	 */
	public String getID(String name) throws IOException
	{
		user = name;
		fullUrl = baseUrl + user;

		URL myUrl = new URL(fullUrl);
		try
		{
			is = myUrl.openStream();
		}
		catch(FileNotFoundException e)
		{
			return "File Not Found";
		}
		
		JSONTokener token = new JSONTokener(is);
		JSONObject result = new JSONObject(token);
		
		is.close();
		return(String) result.get("id");
	}
	
	/**
	 * 
	 * This returns the gender of the Facebook user that the chatBot user is searching
	 * @param name is the username that chatBot user inputs
	 * @return this user's gender
	 * @throws IOException
	 */
	public String getGender(String name) throws IOException
	{
		user = name;
		fullUrl = baseUrl + user;
		URL myUrl = new URL(fullUrl);
		try
		{
			is = myUrl.openStream();
		}
		catch(FileNotFoundException e)
		{
			return "File Not Found";
		}
		
		JSONTokener token = new JSONTokener(is);
		JSONObject result = new JSONObject(token);
		
		is.close();
		return(String) result.get("gender");
	}
	
	/**
	 * 
	 * This returns the full name of the Facebook user that the chatBot user is searching
	 * @param name is the username that chatBot user inputs
	 * @return this user's full name
	 * @throws IOException
	 */
	public String getFullName(String name) throws IOException
	{
		user = name;
		fullUrl = baseUrl + user;
		URL myUrl = new URL(fullUrl);
		try
		{
			is = myUrl.openStream();
		}
		catch (FileNotFoundException e)
		{
			return "File Not Found";
		}
		
		JSONTokener token = new JSONTokener(is);
		JSONObject result = new JSONObject(token);
		
		is.close();
		return (String) result.get("name");
	}
}
