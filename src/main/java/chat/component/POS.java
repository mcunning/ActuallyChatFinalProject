package main.java.chat.component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Sequence;

public final class POS {

	// instances
	private static POSModel model=null; 
	private static Sequence[] topSequences = null;
	private static String[] tags = null;
	private static String[] sent = null;

	public POS() {}
	
	public static String[] tag(final String in)
	{
		POSTaggerME tagger = new POSTaggerME(model);
		tags = tagger.tag(in.split("\\s+"));
		return tags; 
	}
	
	public static void init() throws FileNotFoundException 
	{

		// take in String from user
		final InputStream modelIn = new FileInputStream( "en-pos-maxent.bin" );
		try 
		{
			model = new POSModel(modelIn);
		} 
		catch (IOException e) 
		{
			// Model loading failed, handle the error
			e.printStackTrace();
		} 
		finally 
		{
			if (modelIn != null)
			{
				try 
				{
					modelIn.close();
				} 
				catch (IOException e) {
				}
			}
		}

		//test
		sent = new String[] { "Most", "large", "cities", "in", "the", "US", "had", "morning", "and", "afternoon", "newspapers", "." };
		System.out.println("/nSent[]:/n"); 
		for(String s: sent)
		{
			System.out.println(s);
		}
		 
		System.out.println("/ntags[]:/n");
		for(String s: tags)
		{
			System.out.println(s);
		}
	}//init

	// Get/Sets
	protected Sequence[] getTopSequences() 
	{
		return topSequences;
	}

	protected String[] getTags() 
	{
		
		return tags;
	}

	protected String[] getSent() 
	{
		return sent;
	}
	
}// class

