package rssReader;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import com.sun.syndication.feed.synd.SyndEntry;

/** Widget to show a news item
 * 
 * This shows the details of a single news item, stored as a SyndEntry
 */
public class SyndEntryPanel extends JPanel 
{

	private SyndEntry toDisplay;
	private java.net.URI toOpen;
	private JLabel imageLabel;
	
    /** Create a widget to show a news item
     * 
     * The constructor creates JLabels, and adds them to itself,
     * displaying the article's title and content.  It then creates
     * an event handler, so that when it is clicked on, the link opens
     * in a web browser.
     * 
     * @param toDisplayIn  The news item to display
     */
	public SyndEntryPanel(SyndEntry toDisplayIn) 
	{
		toDisplay = toDisplayIn;

        // Delete the following two lines of code - they're only here as a placeholder
        // TODO write your code here, getting the news article details from 'toDisplay'
        // and adding them to this JPanel
		
		JPanel mainPanel = new JPanel(new FlowLayout());
		this.add(mainPanel);  
			try 
			{
				    ImageIcon imageIcon = new ImageIcon(new URL (getThumbnailURL()));
				    imageLabel = new JLabel(imageIcon);
				    mainPanel.add(imageLabel);
				   // System.out.println(getThumbnailURL());
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				imageLabel = new JLabel("");
			    mainPanel.add(imageLabel);
				
			} 
			
		JPanel details = new JPanel (new GridLayout(3,1));
		mainPanel.add(details);
		details.addMouseListener(new MyPanelListener());
        String newsTitle = "<html><tag><h1><font color=red><b><i>"+toDisplayIn.getTitle()+"</h1></font></b></i></tag></html>";
		JLabel title = new JLabel(newsTitle);
        details.add(title);
        String newsDate = "<html><tag><h5><font color=blue>"+toDisplayIn.getPublishedDate().toString()+"</h5></font></tag></html>";
        JLabel date = new JLabel(newsDate);
        details.add(date);
        String newsDescription = "<html><tag><h3>"+toDisplayIn.getDescription().getValue()+"</h3></tag></html>";
        JLabel description = new JLabel(newsDescription);
        details.add(description);
        
	}
	
	public String getThumbnailURL()
	{
		 java.util.List l = (java.util.List) toDisplay.getForeignMarkup(); 
	     String urlString = null;  
		 Iterator itr = l.iterator(); 
	        while (itr.hasNext()) 
	        {
	            org.jdom.Element o = (org.jdom.Element) itr.next();
	            if (o.getNamespacePrefix().equals("media") && o.getName().equals("thumbnail"))       
	            {                
				    String thumbnailURL = o.getAttribute("url").getValue();
				    thumbnailURL = thumbnailURL.replace("%2f", "/");
				    urlString = thumbnailURL;
				  //  System.out.println("The media:thumbnail URL is " + thumbnailURL);
	            }
	        }
	        return urlString;
	}
	
	class MyPanelListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount()==1)
			{
				try {
					toOpen = new java.net.URI(toDisplay.getLink());
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					java.awt.Desktop.getDesktop().browse(toOpen);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
