package main.java.chat.util;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.java.chat.component.Keyword;
import main.java.chat.component.Response;

/**
 * 
 * @author alex
 * edited by Maddie
 */
public final class ConfigReader {

	public static void readConfig(List<Keyword> keywordsTarget, String inputFile)
	{
		if(keywordsTarget == null)
		{
			throw new NullPointerException("You need to specify the target list in the input parameters for the method readConfig.");
		}
		try
		{
			BufferedReader read = new BufferedReader(new InputStreamReader(ConfigReader.class.getResourceAsStream(inputFile)));
			while(true)
			{
				try
				{
					String line = read.readLine();
					if( line == null ) break;
					
					String[] configParts = line.split( "#" );
					
					String[] keywords = configParts[ 0 ].split( "\\|" );
					Keyword.KeywordType type = configParts[ 1 ].equals( "word" ) ? Keyword.KeywordType.WORD : Keyword.KeywordType.PHRASE;
					Keyword.MatchType sentenceMatch = getMatchType( configParts[ 2 ] );
					Keyword.MatchType wordMatch = getMatchType( configParts[ 3 ] );
					int keywordWeight = Integer.parseInt( configParts[4] );
					
					List<Response> responsesTarget = new ArrayList<Response>();	
					String[] responses = configParts[ 5 ].split( "\\[R\\]" );
					for( int i = 1; i < responses.length; i++ )
					{
						String[] responseParts = responses[i].split("\\\\\\\\");
						String[] responseKeywords = responseParts[0].split("\\|");
						Response.QuestionFlag questionFlag= Response.QuestionFlag.EITHER_QUESTION_OR_STATEMENT;
						
						if (responseParts[1].equals( "no"))
						{
							questionFlag = Response.QuestionFlag.STATEMENT_ONLY;
						}
						else if (responseParts[1].equals ("yes"))
						{
							questionFlag= Response.QuestionFlag.QUESTION_ONLY;
						}
						else if (responseParts[1].equals ("no-preference"))
						{
							questionFlag = Response.QuestionFlag.EITHER_QUESTION_OR_STATEMENT; 
						}

						String[] responseOptions = responseParts[ 2 ].split("\\|");
						int responseWeight = Integer.parseInt( responseParts[ 3 ] );
						responsesTarget.add( new Response( responseKeywords, questionFlag, responseOptions, responseWeight ) );
					}
					
					Collections.sort( responsesTarget, new Comparator<Response>()
					{
						@Override
						public int compare( Response o1, Response o2 ) {
							return ( (Integer) o2.getWeight() ).compareTo( o1.getWeight() );
						}
					});
					
					keywordsTarget.add( new Keyword( keywords,type, sentenceMatch, wordMatch, responsesTarget, keywordWeight ) );
					
					Collections.sort( keywordsTarget, new Comparator<Keyword>()
					{
						@Override
						public int compare(Keyword o1, Keyword o2) {
							return ( (Integer) o2.getWeight() ).compareTo( o1.getWeight() );
						}
						
					});
				}
				catch(EOFException e)
				{
					// Done
					break;
				}
				catch(IOException io)
				{
					break;
				}
			}
			
			read.close();
		}
		catch( IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static Keyword.MatchType getMatchType(String match)
	{
		if (match.equals("starts-with"))
		{
			return Keyword.MatchType.STARTS_WITH;
		}
		else if (match.equals( "ends-with"))
		{
			return Keyword.MatchType.ENDS_WITH;
		}
		else if (match.equals( "exact"))
		{
			return Keyword.MatchType.EXACT;
		}
		else if (match.equals("contains"))
		{
			return Keyword.MatchType.CONTAINS;
		}
		return Keyword.MatchType.CONTAINS;
	}
}
