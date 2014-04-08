package main.java.chat;

import java.io.IOException;

/**
 * 
 * This class is cloned from A2 and initializes the chat
 * @author Madison Cunning
 * @version 1.0
 *
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
    	Chat chat = new ActuallyChat();
        chat.initialize(new ActuallyResponder());
        chat.chat();	
    }
}
