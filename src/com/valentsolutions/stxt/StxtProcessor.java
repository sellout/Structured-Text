package com.valentsolutions.stxt;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.valentsolutions.stxt.dom.StxtDocument;
import com.valentsolutions.stxt.parser.*;

/**
 * Takes either a stxt file or an XML file conforming to the stxt schema via
 * standard in, and translates it, sending the output to standard out. This is
 * the most basic of the UIs.
 */
public class StxtProcessor
{
    protected StxtParser parser = new StxtParser();
    protected SimpleOutputter outputter;

    public StxtProcessor(SimpleOutputter.Format format)
    {
        this.outputter = SimpleOutputter.makeOutputter(format);
    }
    
    public void setTemplate(String template)
    {
        outputter.setTemplate(template);
    }
    
    public void setStylesheet(String stylesheet)
    {
        if (outputter instanceof XmlOutputter)
        {
            ((XmlOutputter) outputter).setStylesheet(stylesheet);
        }
    }
    
    public void embedStylesheet(boolean embed)
    {
        if (outputter instanceof HtmlOutputter)
        {
            ((HtmlOutputter) outputter).embedStylesheet = embed;
        }
    }

    public void processDocument(Reader reader, Writer writer)
        throws IOException, StxtException
    {
        StxtDocument document;
        document = parser.parse(reader);
        outputter.processDocument(document, writer);
    }
}