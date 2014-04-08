package main.java.chat;

import java.io.IOException;

/**
 * 
 * This interface is cloned from A2
 * @author Madison Cunning
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
}
