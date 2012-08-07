package com.valentsolutions.stxt;

import java.io.FileReader;
import java.io.OutputStreamWriter;

import junit.framework.TestCase;

import com.valentsolutions.stxt.dom.*;

/**
 * 
 */
public class XsltOutputterTest
    extends TestCase
{

    /**
     * Constructor for XsltOutputterTest.
     * @param arg0
     */
    public XsltOutputterTest(String arg0)
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

        XsltOutputter outputter = new XsltOutputter(new FileReader("etc/stxt2html.xsl"));
        outputter.processDocument(document, new OutputStreamWriter(System.out));
    }
}
