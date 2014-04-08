package main.java.chat;

import java.io.IOException;

/**
 * 
 * This interface is cloned from A2 and reads the configuration file and sends a response
 * @author Madison Cunning
 *
 */
public interface Responder
{
    /**
     * Reads the configuration file from the system.
     *
     */
    void readConfigFile(String relativePath);
    void respond( String inputSentence ) throws IOException;
    
}
