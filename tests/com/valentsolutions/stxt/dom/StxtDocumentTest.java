package com.valentsolutions.stxt.dom;

import java.io.OutputStreamWriter;

import com.valentsolutions.stxt.DocumentFactory;
import com.valentsolutions.stxt.SimpleOutputter;

import junit.framework.TestCase;

/**
 * 
 */
public class StxtDocumentTest
    extends TestCase
{

    /**
     * Constructor for StxtDocumentTest.
     * @param arg0
     */
    public StxtDocumentTest(String arg0)
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

    public void testGetBody()
    {
    	StxtDocument document = new StxtDocument();
    	TestCase.assertEquals(new Body(), document.getBody());

    	document = DocumentFactory.makeSampleDocument();
		TestCase.assertEquals(DocumentFactory.makeSampleBody(), document.getBody());
    }

    public void testSetBody()
    {
		StxtDocument document = new StxtDocument();
		TestCase.assertEquals(new Body(), document.getBody());

		Body body = DocumentFactory.makeSampleBody();
		document.setBody(body);
		TestCase.assertEquals(body, document.getBody());
    }

    public void testGetMetadata()
    {
		StxtDocument document = new StxtDocument();
		TestCase.assertEquals(new Metadata(), document.getMetadata());

		document = DocumentFactory.makeSampleDocument();
		TestCase.assertEquals(DocumentFactory.makeSampleMetadata(), document.getMetadata());
    }

    public void testSetMetadata()
    {
		StxtDocument document = new StxtDocument();
		TestCase.assertEquals(new Metadata(), document.getMetadata());

		Metadata metadata = DocumentFactory.makeSampleMetadata();
		document.setMetadata(metadata);
		TestCase.assertEquals(metadata, document.getMetadata());
    }

    /*
     * Test for Document toDOM()
     */
    public void testToDOM()
        throws Exception
    {
        StxtDocument document = new StxtDocument();
        
        Body body = document.getBody();
        body.appendChild(new Para());
        Para para = new Para();
        para.appendChild(new Text("Here's the text in our para tag. "));
        Bold bold = new Bold();
        bold.appendChild(new Text("some bold text for ya"));
        para.appendChild(bold);
        para.appendChild(new Text(" Wooo! extra text"));
        body.appendChild(para);

        SimpleOutputter outputter = SimpleOutputter.makeOutputter(SimpleOutputter.Format.RAW);
        outputter.processDocument(document, new OutputStreamWriter(System.out));
    }

//    public void testFromDOM()
//    {
//    	TestCase.assertTrue(false);
//    }

    /*
     * Test for Node toDOM(Document)
     */
//    public void testToDOMDocument()
//    {
//		TestCase.assertTrue(false);
//    }
}
