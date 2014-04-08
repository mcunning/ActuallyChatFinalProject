package main.java.chat;

import java.io.IOException;

/**
 * 
 * Cloned from A2
 *
 */
public interface Chat
{
	void initialize(Responder responder);

    /**
     * Get the sentence from the user
     * @return the String of the sentence
     */
    String getSentence();
    void chat() throws IOException;
//	String getBotName();
//	String getResponse(String text);
//	String getBotVersion();

}
