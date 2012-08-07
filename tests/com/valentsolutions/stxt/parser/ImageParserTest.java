/**
 * Created on Jan 31, 2003
 *
 * To change this generated comment edit the template variable "filecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of file comments go to
 * Window>Preferences>Java>Code Generation.
 */
package com.valentsolutions.stxt.parser;

import com.valentsolutions.stxt.dom.Image;

import junit.framework.TestCase;

/**
 * Write a comment.
 */
public class ImageParserTest
    extends TestCase
{

    /**
     * Constructor for LinkParserTest.
     * @param arg0
     */
    public ImageParserTest(String arg0)
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

    public void testSimpleImage()
        throws Exception
    {
        String test = "http://foo.bar/1.gif";
        
        ImageParser ip = new ImageParser();
        Image img = (Image) ip.parse(test);
        
        assertEquals("http://foo.bar/1.gif", img.getHref());
        assertEquals(null, img.getTitle());
    }

    public void testImageWithText()
        throws Exception
    {
        String test = "http://foo.bar/1.gif | alt text";
        
        ImageParser ip = new ImageParser();
        Image img = (Image) ip.parse(test);
        
        assertEquals("http://foo.bar/1.gif", img.getHref());
        assertEquals("alt text", img.getTitle());
    }

    public void testImageWithWhitespace()
        throws Exception
    {
        String test = " http://foo.bar/1.gif |   alt text    ";
        
        ImageParser ip = new ImageParser();
        Image img = (Image) ip.parse(test);
        
        assertEquals("http://foo.bar/1.gif", img.getHref());
        assertEquals("alt text", img.getTitle());
    }

}
