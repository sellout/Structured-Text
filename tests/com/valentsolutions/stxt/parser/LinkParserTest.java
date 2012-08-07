/**
 * Created on Jan 31, 2003
 *
 * To change this generated comment edit the template variable "filecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of file comments go to
 * Window>Preferences>Java>Code Generation.
 */
package com.valentsolutions.stxt.parser;

import com.valentsolutions.stxt.dom.InlineElement;
import com.valentsolutions.stxt.dom.Link;
import com.valentsolutions.stxt.dom.Text;

import junit.framework.TestCase;

/**
 * Write a comment.
 */
public class LinkParserTest extends TestCase
{

    /**
     * Constructor for LinkParserTest.
     * @param arg0
     */
    public LinkParserTest(String arg0)
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

    public void testSimpleLink()
        throws Exception
    {
        String test = "http://foo.bar/";
        
        LinkParser lp = new LinkParser();
        Link link = (Link) lp.parse(test);
        
        assertEquals("http://foo.bar/", link.getHref());
        
        InlineElement[] children = link.getChildren();
        assertEquals(1, children.length);
        Text text = (Text) children[0];
        assertEquals("http://foo.bar/", text.getContent());
    }

    public void testLinkWithText()
        throws Exception
    {
        String test = "http://foo.bar/ | link text";
        
        LinkParser lp = new LinkParser();
        Link link = (Link) lp.parse(test);
        
        assertEquals("http://foo.bar/", link.getHref());
        
        InlineElement[] children = link.getChildren();
        assertEquals(1, children.length);
        Text text = (Text) children[0];
        assertEquals("link text", text.getContent());
    }

    public void testLinkWithWhitespace()
        throws Exception
    {
        String test = " http://foo.bar/ |   link text    ";
        
        LinkParser lp = new LinkParser();
        Link link = (Link) lp.parse(test);
        
        assertEquals("http://foo.bar/", link.getHref());
        
        InlineElement[] children = link.getChildren();
        assertEquals(1, children.length);
        Text text = (Text) children[0];
        assertEquals("link text", text.getContent());
    }

}
