package main.java.chat;

import static main.java.chat.util.Util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gtranslate.Language;
import com.gtranslate.Translator;

import main.java.chat.apis.ActuallyFacebook;
import main.java.chat.apis.ActuallyWiki;
import main.java.chat.apis.ActuallyYahoo;
import main.java.chat.component.Keyword;
import main.java.chat.component.Response;
import main.java.chat.util.ConfigReader;

/**
 * 
 * This class compares the user input against the configuration file and gets a response appropriately
 * @author Madison Cunning
 * @version 1.1
 *
 */
public final class ActuallyResponder implements Responder {
	private static List<Keyword> keywords;
	boolean french = false;
	
	public ActuallyResponder() 
	{
	}

   @Override
   /**
    * 
    * reads configuration file
    * 
    */
   public void readConfigFile(String relativePath)
    {
    	keywords = new ArrayList<Keyword>();
    	ConfigReader.readConfig(keywords, relativePath);
    }

   @Override
   /**
    * 
    * Finds response to user input based on if keywords are in the configuration file
    * @param inputSentence is the user input
    */
	public void respond(final String inputSentence) throws IOException
	{
    	ActuallyFacebook facebook = new ActuallyFacebook();
    	ActuallyYahoo yahoo = new ActuallyYahoo();
    	ActuallyWiki wiki = new ActuallyWiki();
    	String[] split = inputSentence.split( "\\s+" );
    	boolean respond = false;
		
        search: for( Keyword keyword : keywords )
        {
        	if( keyword.getType().equals( Keyword.KeywordType.PHRASE ) )
        	{
        		for( String k : keyword.getKeywords() )
    			{
        			if( ( keyword.getSentenceMatch().equals( Keyword.MatchType.EXACT ) && inputSentence.equals( k ) )
        				|| ( keyword.getSentenceMatch().equals( Keyword.MatchType.STARTS_WITH ) && inputSentence.startsWith( k ) )
        				|| ( keyword.getSentenceMatch().equals( Keyword.MatchType.ENDS_WITH ) && inputSentence.endsWith( k ) )
        				|| ( keyword.getSentenceMatch().equals(Keyword.MatchType.CONTAINS ) && inputSentence.contains( k ) ) )
        			{
    					respond = true;
    					pickResponse( inputSentence, keyword.getResponses() );
    					break search;
        			}
        		}
        	}
        	else
        	{
        		for( int i = 0; i < split.length; i++ )
        		{
        			String word = split[ i ].trim();
        			if( i > 0 && keyword.getSentenceMatch().equals( Keyword.MatchType.STARTS_WITH )
        					|| split.length > 1 && keyword.getSentenceMatch().equals( Keyword.MatchType.EXACT ) )
        			{
        				continue search;
        			}
        			
        			boolean cont = false;
        			for( String key : keyword.getKeywords() )
    				{
    					Scanner scan = new Scanner(System.in);
    					if(keyword.getWordMatch().equals(Keyword.MatchType.CONTAINS) && word.equals("facebook"))
    					{
    						respond = true; 
    						String FBInfo = "";
    						pickResponse(inputSentence, keyword.getResponses());
    						String FBName = scan.nextLine();
    						print("What would you like to know about this person? (First name, last name, full name, gender, id)");
    						String FBScan = scan.nextLine();
    						
    						String FBOutput="";
    						FBOutput = FBName.replaceAll(" ", "");
    						
    						if(FBOutput == "")
    						{
    							pickGenericResponse();
    						}
    						else
    						{
        						switch(FBScan)
        						{
        							case "first name": FBInfo = facebook.getFirstName(FBOutput) + " is this person's first name.";
        							break;
        							case "last name": FBInfo = facebook.getLastName(FBOutput) + " is this person's last name.";
        							break;
        							case "id": FBInfo = facebook.getID(FBOutput) + " is this person's facebook id number.";
        							break;
        							case "gender": FBInfo = "This person is a " + facebook.getGender(FBOutput) + ".";
        							break;
        							case "full name": FBInfo = facebook.getFullName(FBOutput) + " is that person's full name."; 
        							break;
        							default: FBInfo = "Sorry, I don't know that person.";
        							break;
        						}
        						
    						}
    	    				print(FBInfo);
    	    				break search; 
    					}
    					
    					else if (keyword.getWordMatch().equals(Keyword.MatchType.CONTAINS) && word.equals("answer"))
    					{
    						respond = true;
    						String yahooInfo = "";
    						print("What would you like to know about?");
    						String yahooQuery = scan.nextLine();
    						String yahooQuery2 = "";
    						yahooQuery2 = yahooQuery.replaceAll(" ", "+");
    						
    						yahooInfo = yahoo.getYahooInfo(yahooQuery2);
    						System.out.println(yahooInfo);
    						
    						break search;
    					}

    					else if (keyword.getWordMatch().equals(Keyword.MatchType.CONTAINS) && word.equals("information"))
    					{
    						respond = true;
    						String wikiInfo = "";
    						print("What would you like information on?");
    						String wikiQuery = scan.nextLine();
    						String wikiQuery2 = "";
    								
    						wikiQuery2 = wikiQuery.replaceAll(" ", "+");
    						
    						wikiInfo = wiki.getWikiInfo(wikiQuery2);
    						System.out.println(wikiInfo + "You're welcome");						
    					}

        			
        			if (keyword.getSentenceMatch().equals( Keyword.MatchType.ENDS_WITH ))
        			{
        				cont = true;
        				        					
        					
        					if(key.contains(split[split.length - 1]))
        					{
        						cont = false;
        					}
        				}
        			}

        			if (cont == true)
        			{
        				continue search;
        			}
        			
        			for( String k : keyword.getKeywords() )
        			{
        				if( keyword.getWordMatch().equals( Keyword.MatchType.EXACT ) && word.equals( k )
        						|| keyword.getWordMatch().equals( Keyword.MatchType.STARTS_WITH ) && word.startsWith( k ) 
        						|| keyword.getWordMatch().equals( Keyword.MatchType.ENDS_WITH ) && word.endsWith( k )
        						|| keyword.getWordMatch().equals( Keyword.MatchType.CONTAINS ) && word.contains( k ) )
        				{
        					respond = true;
        					pickResponse(inputSentence, keyword.getResponses() );
        					break search;
        				}
        			}
        		}
        	}
        }
    	if( ! respond )
    	{
//			translate();
    		pickGenericResponse();
    	}
    }
    
/**
 * 
 * @param inputSentence is the users input to the chatBot
 * @param responses is the responses from the chatBot
 */
   private void pickResponse( final String inputSentence, final List<Response> responses )
    {
    	boolean question = inputSentence.contains( "?" ) || startsWith( inputSentence, "do", "how", "is", "were", "can", "when", "who", "what", "where", "why" );
    	boolean respond = false;
    	
    	search: for( Response response : responses )
    	{
    		if( response.getQuestionFlag().equals( Response.QuestionFlag.QUESTION_ONLY ) && !question
    				|| response.getQuestionFlag().equals( Response.QuestionFlag.STATEMENT_ONLY) && question )
    		{
    			continue search;
    		}
    		
    		for( String keyword : response.getKeywords() )
    		{
    			if( inputSentence.contains( keyword ) || keyword.equals( "" ) )
    			{		
    				respond = true;	
    				print( randomFromArray( response.getResponses() ) );
    				break search;
    			}
    		}
    	}
    	if( ! respond )
    	{
//    			translate();
    			pickGenericResponse();
    	}    	
    }
    
/**
 * 
 * Uses google translate API to change users input in french to english
 * 
 */
   private void translate()
    {
    	ActuallyChat chatTrans = new ActuallyChat();
    	Translator translator = Translator.getInstance();
    	String toTranslate = translator.translate(chatTrans.getSentence(), Language.FRENCH, Language.ENGLISH);
    	System.out.println("Did you mean " + toTranslate + "?");
    }
   
/**
 * This picks a generic response to output to user
 */
   private void pickGenericResponse()
    {
    	print(randomFromArray(
    			"Let's talk about me some more.",
    			"That doesn't interest me.",
    			"I don't really know about that.",
    			"Can we talk about something else?",
    			"I'd rather not talk about that."
    		));
    }
}