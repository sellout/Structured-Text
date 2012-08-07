package com.valentsolutions.stxt.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.valentsolutions.stxt.StxtException;

import org.w3c.dom.Document;

/**
 * Fragment for holding small pieces of structured text that don't require
 * blocks and metadata.
 */
public class StxtFragment
    extends InlineContainer
    implements StxtElement
{
    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#getElementName()
     */
    protected String getElementName()
    {
        return "stxtFragment";
    }

    public Document toDOM()
        throws StxtException
    {
        Document document;
        try
        {
            DocumentBuilderFactory builderFactory
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            document = docBuilder.newDocument();
        }
        catch (ParserConfigurationException e)
        {
            throw new StxtException("Failed to create a DocumentBuilder", e);
        }
        document.appendChild(this.toDOM(document));
        document.normalize();
        return document;
    }
}
