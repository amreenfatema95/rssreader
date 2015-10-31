package rssReader;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.syndication.feed.synd.SyndEntry;

/** Simple RSS reader window */
public class ReaderWindow extends JFrame {

    /** Scroll pane in the middle, in case there are lots of news items */
	private JScrollPane scroller; 
    
    /** The news items themselves - initially, none */
	private ArrayList<SyndEntry> news;
    /** Make a new RSS reader window */     
	public ReaderWindow() {
		
		
        // For testing purposes, this fetches the test news articles,
        // then calls 'displayNews' to display them.
		//news = SimpleRSSAPI.getTestNews();	
		
		//displayNews();
		
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel top = new JPanel(new FlowLayout());
		add(top,BorderLayout.NORTH);
		JLabel FeedURL = new JLabel("Feed URL:");
		top.add(FeedURL);
		JTextField feedURLText = new JTextField(50);
		top.add(feedURLText);
		feedURLText.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				news = SimpleRSSAPI.getNews(feedURLText.getText());
				if (news!=null)
				{
					displayNews();
				}
			}
		});
		
		JPanel bottom = new JPanel();
		scroller = new JScrollPane(bottom);
		add(scroller, BorderLayout.CENTER);
		JLabel displayText = new JLabel("<html><tag><h3>Enter a URL above, and hit enter</h3></tag></html>");
		bottom.add(displayText);
		
		
		
		pack();
	}

	/** Show the news articles in the 'news' member variable */
	private void displayNews() {
        // Create a panel for the news articles
		JPanel newsPanel = new JPanel();
        
        // Give it a GridLayout - one row for each news item
		newsPanel.setLayout(new GridLayout(news.size(),1));

		for (SyndEntry e : news) 
		{
            // Create a SyndEntryPanel for the news item; add it to newsPanel
			newsPanel.add(new SyndEntryPanel(e));
		}
		
		// Put it in the scroll pane
		scroller.setViewportView(newsPanel);

		pack();
	}
	
	

}

