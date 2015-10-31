# RSS Reader #

### Author ###
Amreen Fatima S Surani

### Date ###
March 2015


1. INTRODUCTION
-----------------
RSS(Really simple syndication) Reader enables a user to publish frequently updated information like blogs,news etc. The RSS feed displays summarised text and meta data like publishing date.**This application has been tested to receive timely updates from the following websites:**

* http://www.kcl.ac.uk/nms/depts/informatics/news/index.aspx?SyndicationType=1 - the Department of Informatics news feed.


* http://feeds.bbci.co.uk/news/england/rss.xml - BBC News for England

2. External Libraries:
-----------------------
* jdom-1.1.3.jar

* rome-1.0.jar

3. USAGE
---------

The application can be initiated using a command line interface or through an IDE.

3.1 Usage through an IDE
------------------------

If an IDE like eclipse is being used then it would be required to run the RSSReader.java file. 

3.2 Usage through a command line interface
------------------------------------------

If the application is being initiated through a command line interface, then the following syntax can be used:

java -cp bin: lib/jdom-1.1.3.jar:lib/rome-1.0.jar rssReader.RSSReader

The following assumptions apply:
* All class files are in the bin folder, and
* Package is called rssReader