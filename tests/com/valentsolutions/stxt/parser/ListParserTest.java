package com.valentsolutions.stxt.parser;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.List;
import com.valentsolutions.stxt.parser.ListParser;

import junit.framework.TestCase;

/**
 * @author finlay
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ListParserTest extends TestCase
{

    /**
     * Constructor for ListParserTest.
     * @param arg0
     */
    public ListParserTest(String arg0)
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

    public void testSimpleParse()
        throws StxtException
    {
        String text = 
            " * Item 1\n" +
            " * Item 2\n" +
            " * Item 3\n";

        ListParser parser = new ListParser();
        List list = (List) parser.parse(text);

		assertEquals(3, list.getChildren().length);
    }

    public void testOrderedVsUnordered()
        throws StxtException
    {
        String unordered = 
            " * Item 1\n" +
            " * Item 2\n" +
            " * Item 3\n";

        String ordered = 
            " # Item 1\n" +
            " # Item 2\n" +
            " # Item 3\n";

        ListParser parser = new ListParser();

        List ulist = (List) parser.parse(unordered);
        assertEquals(false, ulist.isOrdered());                    
		assertEquals(3, ulist.getChildren().length);

        List olist = (List) parser.parse(ordered);
        assertEquals(true, olist.isOrdered());                    
		assertEquals(3, olist.getChildren().length);
    }

    public void testMultilineParse()
        throws StxtException
    {
        String text = 
            " * Item 1\n" +
            " * Item 2\n" +
            "   more of item 2\n" +
            " * Item 3\n";

        ListParser parser = new ListParser();
        List list = (List) parser.parse(text);

        assertEquals(3, list.getChildren().length);
    }

    public void testMultiblockParse()
        throws StxtException
    {
        String text = 
            " * Item 1\n" +
            " * Item 2\n" +
            "\n" +
            "   second block in item 2\n" +
            " * Item 3\n";

        ListParser parser = new ListParser();
        List list = (List) parser.parse(text);

		assertEquals(3, list.getChildren().length);                    
		assertEquals(2, list.getChildren()[1].getChildren().length);                    
    }
}
