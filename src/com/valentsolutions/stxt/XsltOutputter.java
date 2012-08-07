package com.valentsolutions.stxt;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.valentsolutions.stxt.dom.StxtDocument;

/**
 * Translates an StxtDocument to some other format as defined in an XSLT
 * stylesheet.
 * 
 * @inv this.transformer != null
 */
public class XsltOutputter
    implements StxtOutputter
{
    protected Transformer transformer;

    /**
     * Creates a new outputter with the given stylesheet. If the stylesheet is
     * null, we just output our internal XML format.
     */
    public XsltOutputter(Reader stylesheet)
        throws StxtException
    {
        try
        {
            TransformerFactory factory = TransformerFactory.newInstance();
            if (stylesheet != null)
            {
                this.transformer = factory.newTransformer(new StreamSource(stylesheet));
            }
            else
            {
                this.transformer = factory.newTransformer();
            }
        }
        catch (TransformerConfigurationException e)
        {
            throw new StxtException("Failed to parse stylesheet", e);
        }
    }

    protected void setParameter(String parameter, Object value)
    {
        this.transformer.setParameter(parameter, value);
    }

    /**
     * @see com.valentsolutions.stxt.StxtOutputter#processDocument(StxtDocument, Writer)
     */
    public void processDocument(StxtDocument document, Writer out)
        throws StxtException, IOException
    {
        try
        {
            this.transformer.transform(new DOMSource(document.toDOM()),
                                       new StreamResult(out));
        }
        catch (TransformerException e)
        {
            throw new StxtException("Failed to transform document", e);
        }
    }

}
