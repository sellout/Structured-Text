package com.valentsolutions.stxt.parser;

import java.io.FileReader;
import java.io.OutputStreamWriter;

import junit.framework.TestCase;

import com.valentsolutions.stxt.SimpleOutputter;
import com.valentsolutions.stxt.dom.StxtDocument;

/**
 * 
 */
public class StxtParserTest
    extends TestCase
{

    /**
     * Constructor for StxtParserTest.
     * @param arg0
     */
    public StxtParserTest(String arg0)
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
//        StxtParser parser = new StxtParser();
//        StxtDocument expected = DocumentFactory.makeSampleDocument();
//        StxtDocument actual = parser.parse(new FileReader("tests/data/parse/parse1.stxt"));
//        
//        StringWriter expectedString = new StringWriter();
//        StringWriter actualString = new StringWriter();
//        
//        RawOutputter outputter = new RawOutputter();
//        outputter.processDocument(expected, expectedString);
//        outputter.processDocument(actual, actualString);
//
//        TestCase.assertEquals("Documents don't match",
//                              expectedString.toString(),
//                              actualString.toString());
    }

    public void testParseMetadata()
        throws Exception
    {
        StxtParser parser = new StxtParser();
        StxtDocument doc = parser.parse(new FileReader("tests/data/parse/metadata.stxt"));

        SimpleOutputter outputter = SimpleOutputter.makeOutputter(SimpleOutputter.Format.RAW);
        outputter.processDocument(doc, new OutputStreamWriter(System.out));
    }

}
