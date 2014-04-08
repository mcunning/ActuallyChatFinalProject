package main.java.chat;
import java.io.IOException;
/**
 * Cloned from A2
 */
import java.util.Scanner;

/**
 * 
 * This class is cloned from A2 and scans in the user input and initializes the Responder
 * @author Madison Cunning
 *
 *
 */
public class ActuallyChat implements Chat
{
    boolean chat;
    Responder responder;
    Scanner scan;

    public ActuallyChat()
    {
        chat = true;
        scan = new Scanner( System.in );

    
    }//Constructor
    


    @Override
    /**
     * Initializes responder and sends to location of configuration file
     */
    public void initialize( Responder responderIn )
    {
        responder = responderIn;
        responder.readConfigFile( "../../../resources/config.chat" );

        
    }//initialize

    @Override
    /**
     * 
     * Scans and returns user input
     * 
     */
    public String getSentence()
    {
        return scan.nextLine();
    }

    
    /**
     * 
     * @param string prints the string
     */
    private void print( String string )
    {
    	System.out.println( string );
    }
    
    @Override 
    /**
     * 
     * begins chats
     * 
     */
    public void chat() throws IOException
    {
    	print( "Hello!" );
    	
        while(chat)
			try 
        	{
				String sentence = getSentence();
				responder.respond(sentence);
			} 
        	catch (Exception e) 
        	{
				e.printStackTrace();
			}
    }
}
