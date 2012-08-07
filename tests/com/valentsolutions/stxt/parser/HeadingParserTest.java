package com.valentsolutions.stxt.parser;

import com.valentsolutions.stxt.dom.Heading;
import com.valentsolutions.stxt.dom.Text;

import junit.framework.TestCase;

/**
 * @author finlay
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HeadingParserTest extends TestCase {

	/**
	 * Constructor for HeadingParserTest.
	 * @param arg0
	 */
	public HeadingParserTest(String arg0) {
		super(arg0);
	}

	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testParse() 
		throws Exception
	{
		String text = "=== Heading 3";
		
		HeadingParser parser = new HeadingParser();
		Heading heading = (Heading) parser.parse(text);
        
        assertEquals(3, heading.getLevel());
        assertEquals(1, heading.getChildren().length);
        
        Text t = (Text) heading.getChildren()[0];
        assertEquals("Heading 3", t.getContent());
	}

    public void testParseWithTrailing() 
        throws Exception
    {
        String text = "=== Heading 3 ========";
        
        HeadingParser parser = new HeadingParser();
        Heading heading = (Heading) parser.parse(text);
        
        assertEquals(3, heading.getLevel());
        assertEquals(1, heading.getChildren().length);
        
        Text t = (Text) heading.getChildren()[0];
        assertEquals("Heading 3", t.getContent());
    }
    
    public void testParseWithTightSpacing() 
        throws Exception
    {
        String text = "=== Heading 3===";
        
        HeadingParser parser = new HeadingParser();
        Heading heading = (Heading) parser.parse(text);
        
        assertEquals(3, heading.getLevel());
        assertEquals(1, heading.getChildren().length);
        
        Text t = (Text) heading.getChildren()[0];
        assertEquals("Heading 3", t.getContent());
    }
}
