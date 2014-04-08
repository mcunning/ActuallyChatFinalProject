package main.java.chat;
import java.io.IOException;
/**
 * Cloned from A2
 */
import java.util.Scanner;

/**
 * 
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
    public void initialize( Responder responderIn )
    {
        responder = responderIn;
        responder.readConfigFile( "../../../resources/config.chat" );

        
    }//initialize

    @Override
    public String getSentence()
    {
        return scan.nextLine();
    }

    private void print( String string )
    {
    	System.out.println( string );
    }
    
    @Override 
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
