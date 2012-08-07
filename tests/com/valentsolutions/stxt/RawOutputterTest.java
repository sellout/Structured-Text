package com.valentsolutions.stxt;

import java.io.StringWriter;

import junit.framework.TestCase;

import com.valentsolutions.stxt.dom.*;

/**
 * 
 */
public class RawOutputterTest
    extends TestCase
{
    // Gotta make sure we're using the right line ending for the system.
    public static final String ENDL = System.getProperty("line.separator");

    /**
     * Constructor for RawOutputterTest.
     * @param arg0
     */
    public RawOutputterTest(String arg0)
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

    public void testProcessDocument()
        throws Exception
    {
        final String expectedOut =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + ENDL +
            "<stxtDocument>" + // xmlns=\"http://valentsoftware.com/stxt\">" +
                "<metadata/>" +
                "<body>" +
                    "<heading level=\"2\">heading text</heading>" +
                    "<para>" +
                        "para text" +
                        "<bold>bold text</bold>" +
                        "<italic>italic text</italic>" +
                        "<superscript>super text</superscript>" +
                        "<subscript>sub text</subscript>" +
                        "<monospace>mono text</monospace>" +
                        "<link xlink:href=\"URI\">link text</link>" +
                        "<image xlink:href=\"URI\" xlink:title=\"alt text\"/>" +
                        "trailing para text" +
                    "</para>" +
                    "<blockquote>" +
                        "<para>blockquote text</para>" +
                    "</blockquote>" +
                    "<code>code text</code>" +
                    "<list ordered=\"true\">" +
                        "<item><para>item 1</para></item>" +
                        "<item><para>item 2</para></item>" +
                        "<item><para>item 3</para></item>" +
                    "</list>" +
                    "<deflist>" +
                        "<entry>" +
                            "<term>term 1</term>" +
                            "<definition>def 1</definition>" +
                        "</entry>" +
                        "<entry>" +
                            "<term>term 2</term>" +
                            "<definition>def 2</definition>" +
                            "<definition>def 3</definition>" +
                        "</entry>" +
                    "</deflist>" +
                "</body>" +
            "</stxtDocument>";
        StxtDocument document = DocumentFactory.makeSampleDocument();
        StringWriter writer = new StringWriter();
        SimpleOutputter outputter = new SimpleOutputter(SimpleOutputter.Format.RAW);
        outputter.processDocument(document, writer);
        
        TestCase.assertEquals("raw output did not match expected",
                              expectedOut,
                              writer.toString());
    }
}
