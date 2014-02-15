package com.sternkn.testtasks.rss.parser;

import java.io.IOException;

/**
 *  Signals that a rss reading exception has occurred. 
 */
public class RssReadException extends IOException
{
	static final long serialVersionUID = 781837582814609L;
	
	/**
     * Constructs a {@code RssReadException} with {@code null}
     * as its error detail message.
     */
    public RssReadException(){
    	super();
    }
    
    /**
     * Constructs a {@code RssReadException} with the specified detail message.
     *
     * @param msg
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
    public RssReadException(String msg){
    	super(msg);
    }
}
