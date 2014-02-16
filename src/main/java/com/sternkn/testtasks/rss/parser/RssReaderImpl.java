package com.sternkn.testtasks.rss.parser;

import java.util.List;
import java.util.LinkedList; 

import com.sternkn.testtasks.rss.domain.RssNew;
import com.sternkn.testtasks.rss.domain.RssFeed;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;



public class RssReaderImpl implements RssReader
{
    public RssReaderImpl(RssFeed rssFeed) throws RssReadException
    {
    	if(rssFeed == null) throw new IllegalArgumentException("rssFeed is null");
    	
    	feed = rssFeed;
    	
    	try {
	        url = new URL(feed.getUrl());
	    } catch (MalformedURLException e) {
	        throw new RssReadException("Error parsing url " + feed.getUrl());
	    }
    }
    
    
    public List<RssNew> readNews() throws RssReadException
    {
    	List<RssNew> rssNews = new LinkedList<RssNew>();
    	
    	try 
		{
		    String title = "";
		    String link = "";

		    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		    InputStream in = url.openStream();
		    XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		    
		    /**
		     *  read the RSS XML document
		     * 
		     *  RSS XML format example: 
		     * 
		     *     <rss version="2.0">
		     *         <channel>
		     *             <title>JavaWorld</title>
		     *             <link>http://www.javaworld.com</link>
		     *             <description></description>
		     *             ....
		     *             <item>
		     *                 <title>Forrester: HTML5 apps still not as good as native apps</title>
		     *                 <link>http://www.javaworld.com/article/2094121</link>
		     *                 ...
		     *             </item>    
		     *             <item>
		     *                 <title>ObjectStreamClass: Peeking at a Java Object&#039;s Serialization</title>
		     *                 <link>http://www.javaworld.com/article/2095381</link>
		     *                 ...
		     *             </item>
		     *             ....    
		     *         </channel>
		     *     </rss>      
		     */
		    
		    int elementCounter           = 0;
		    
		    while (eventReader.hasNext()) 
		    {
		        XMLEvent event = eventReader.nextEvent();
		        
		        if (event.isStartElement()) 
		        {
		        	elementCounter++;
		            String localPart = event.asStartElement().getName().getLocalPart();
		            
		            System.out.println("localPart = " + localPart);

		            if(elementCounter == 1 && !localPart.equals(RSS)){
		            	throw new RssReadException("Invalid format RSS XML, the first element must be <rss>");
		            }
		            
		            if(elementCounter == 2 && !localPart.equals(CHANNEL)){
		            	throw new RssReadException("Invalid format RSS XML, the second element must be <channel>");
		            }
		            
		            if(localPart.equals(TITLE)){
		          	    title = getElementContent(event, eventReader);
		            }
		            	  
		            if(localPart.equals(LINK)){
		                link = getElementContent(event, eventReader);
		            }
		              
		        } 
		        else if (event.isEndElement()) 
		        {
		            if (event.asEndElement().getName().getLocalPart().equals(ITEM)) 
		            {
		            	RssNew rssNew = new RssNew(); 
		            	rssNew.setTitle(title);
		            	rssNew.setLink(link);
		            	rssNew.setRssFeed(feed);
		            	
		            	rssNews.add(rssNew);
		                event = eventReader.nextEvent();
		                continue;
		            }
		        }
		    }
		} 
    	catch (XMLStreamException xmle) {
		    throw new RssReadException(xmle.getMessage());
		}
    	catch (IOException ioe){
    		throw new RssReadException(ioe.getMessage());
    	}
    	
    	return rssNews;
    }
    
    private String getElementContent(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException 
	{
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
    
    private final RssFeed feed;
    private final URL url;
    private final String TITLE   = "title";
    private final String LINK    = "link";
    private final String ITEM    = "item";
    private final String RSS     = "rss";
    private final String CHANNEL = "channel";
}
