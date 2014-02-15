package com.sternkn.testtasks.rss.dao;

import java.io.IOException;

/**
 * Signals that a database exception has occurred.
 */
public class DbException extends IOException
{
	static final long serialVersionUID = 78183758281460L;
	
	/**
     * Constructs a {@code DbException} with {@code null}
     * as its error detail message.
     */
    public DbException(){
    	super();
    }
    
    /**
     * Constructs a {@code DbException} with the specified detail message.
     *
     * @param msg
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
    public DbException(String msg){
    	super(msg);
    }
}
