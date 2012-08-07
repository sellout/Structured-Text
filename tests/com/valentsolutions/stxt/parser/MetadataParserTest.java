package com.valentsolutions.stxt.parser;

import com.valentsolutions.stxt.dom.Metadata;

import junit.framework.TestCase;

/**
 * Write a comment.
 */
public class MetadataParserTest extends TestCase
{

    /**
     * Constructor for MetadataParserTest.
     * @param arg0
     */
    public MetadataParserTest(String arg0)
    {
        super(arg0);
    }

    /**
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testParse()
        throws Exception
    {
        String testData = 
            "Author: author name\n" +
            "Title: document title\n" +
            "Date: 2003-01-01\n" +
            "Revision: 1\n"+
            "Unrecognized: junk\n";
            
        MetadataParser parser = new MetadataParser();
        Metadata meta = parser.parse(testData);
        
        assertEquals("author name", meta.getHeader("author"));
        assertEquals("document title", meta.getHeader("title"));
        assertEquals("2003-01-01", meta.getHeader("date"));
		assertEquals("1", meta.getHeader("revision"));
		assertEquals("junk", meta.getHeader("UNReCOGnIZEd"));
    }

    public void testMultilineParse()
        throws Exception
    {
        String testData = 
            "Author: author\n" +
            "        name\n" +
            "Title: document\n" +
            "title\n" +
            "Date: 2003-01-01\n";
            
        MetadataParser parser = new MetadataParser();
        Metadata meta = parser.parse(testData);
        
        assertEquals("author name", meta.getHeader("author"));
        assertEquals("document", meta.getHeader("title"));
        assertEquals("2003-01-01", meta.getHeader("date"));
    }

    public void testEmptyParse()
        throws Exception
    {
        MetadataParser parser = new MetadataParser();
        Metadata meta = parser.parse("");
        
        assertEquals(null, meta.getHeader("author"));
    }
}
