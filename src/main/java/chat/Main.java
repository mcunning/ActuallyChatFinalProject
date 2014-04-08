package main.java.chat;

import java.io.IOException;

/**
 * Cloned from A2
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
