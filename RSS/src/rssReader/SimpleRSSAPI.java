package rssReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.jdom.Namespace;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/** A simple wrapper class for fetching RSS news feeds
 *
 * You do not need to change this code, but I've included the source in case you're
 * curious about how it works.
 * 
 */
public class SimpleRSSAPI {

	/** Get a single SyndEntry to test your code with.
	 * 
	 * Use this with question 1, or in general, to avoid hammering real RSS servers
	 * whilst you find and fix bugs in your code.
	 * 
	 * @return A SyndEntry, containing two test news articles.
	 */
	public static ArrayList<SyndEntry> getTestNews() {
		ArrayList<SyndEntry> toReturn = new ArrayList<>(1);

		// First news article
		{
			SyndEntryImpl dummyNews = new SyndEntryImpl();				
		
			dummyNews.setTitle("PEP Lab declared Best Ever");
			dummyNews.setAuthor("Impartial Student");
			dummyNews.setPublishedDate(Calendar.getInstance().getTime());
			dummyNews.setLink("https://www.youtube.com/watch?v=oHg5SJYRHA0&hl=en-GB&gl=GB");
			
			SyndContentImpl content = new SyndContentImpl();
			content.setValue("This week's PEP lab has been voted 'Best Ever', in a poll of students hoping for extra marks in the assessed lab.");
			dummyNews.setDescription(content);
			
			ArrayList thumbnailList = new ArrayList(1);
			
			// This is how to get a 'media:thumbnail' object into the RSS feed for this item
			Element e = new Element("thumbnail", Namespace.getNamespace("media", "http://search.yahoo.com/mrss/"));
			e.setAttribute("url","http://www.kcl.ac.uk%2fImportedImages%2fSchools%2fNMS%2finformatics%2fpuffs%2fblackwhitebrainpuff.png");
			
			thumbnailList.add(e);
			
			dummyNews.setForeignMarkup(thumbnailList);
			toReturn.add(dummyNews);
		}
		
		// Second news article
		{
			SyndEntryImpl dummyNews = new SyndEntryImpl();				
		
			dummyNews.setTitle("King's student sets new world record on Flappy Bird");
			dummyNews.setAuthor("Fictitious Story");
			dummyNews.setPublishedDate(Calendar.getInstance().getTime());
			dummyNews.setLink("http://goo.gl/kuyOXO");
			
			SyndContentImpl content = new SyndContentImpl();
			content.setValue("Whilst meant to be working on their PRA Coursework, King's student Mr Deadline broke the world record on Flappy Bird, with a score of over 9000.");
			dummyNews.setDescription(content);
			
			ArrayList thumbnailList = new ArrayList(1);
			
			Element e = new Element("thumbnail", Namespace.getNamespace("media", "http://search.yahoo.com/mrss/"));
			e.setAttribute("url","http://www.kcl.ac.uk%2fImportedImages%2fGenericSymbols%2fCrest2-Puff.jpg");
			
			thumbnailList.add(e);
			
			dummyNews.setForeignMarkup(thumbnailList);
			toReturn.add(dummyNews);
		}
		
		
		return toReturn;
	}

	/** Get the RSS news feed from the URL given
	 * 
	 * @param urlString  The URL of the RSS feed, as a string
	 * @return  A list of SyndEntry objects, one for each news item.  Or, null if the URL was invalid.
	 */
	public static ArrayList<SyndEntry> getNews(String urlString) {
		
		ArrayList<SyndEntry> toReturn = null;
		
	    XmlReader reader = null;
	    try {
	        
	        // Try to make a URL from URL given
	        URL url  = new URL(urlString);
	        
	        // If that didn't thrown an exception, fetch the RSS feed data
	        reader = new XmlReader(url);
	        SyndFeed feed = new SyndFeedInput().build(reader);
	        
            // Get the news items from the RSS feed
	        List entries = feed.getEntries();
	
	        
	        
	        // Loop over the items
	        Iterator itr = entries.iterator();
	        
	        // Make an arraylist to hold the results
	        ArrayList<SyndEntry> newNews = new ArrayList<>();
	        while (itr.hasNext()) {                
	            SyndEntry entry = (SyndEntry) itr.next();
	            //store the next item on the ArrayList
	            newNews.add(entry);
	        }       
	        
	        // got to the end without errors, yay
	        toReturn = newNews;
	        
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    try {
	        // Try to close the RSS feed
	        if (reader != null) {
	            reader.close();
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return toReturn;
	}
}
