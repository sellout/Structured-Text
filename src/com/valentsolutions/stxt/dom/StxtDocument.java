package com.valentsolutions.stxt.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.valentsolutions.stxt.StxtException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Represents an entire structured text document. This is the root of the 
 * document tree.
 *
 * @inv this.body != null
 * @inv this.metadata != null
 */
public class StxtDocument
	implements StxtElement
{
	protected Body body = new Body();
	protected Metadata metadata = new Metadata();

    public String getElementName()
    {
        return "stxtDocument";
    }

    /**
     * Returns the body.
     * @return Body
     * 
     * @post return != null
     */
    public Body getBody()
    {
        return this.body;
    }

    /**
     * Sets the body.
     * @param body The body to set
     * 
     * @pre body != null
     */
    public void setBody(Body body)
    {
        this.body = body;
    }

    /**
     * Returns the metadata.
     * @return Metadata
     * 
     * @post return != null
     */
    public Metadata getMetadata()
    {
        return this.metadata;
    }

    /**
     * Sets the metadata.
     * @param metadata The metadata to set
     * 
     * @pre metadata != null
     */
    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    /**
     * Returns the document as a DOM document.
     */
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
    
    /**
     * Parses an XML DOM into an STXT DOM. Postponed for now.
     * @param domDocument
     * @return StxtDocument
     */
//    public static StxtDocument fromDOM(Document domDocument)
//    {
//        return null;
//    }

    /**
     * @see com.valentsolutions.stxt.dom.StxtElement#toDOM(Document)
     */
    public Node toDOM(Document document)
    {
    	Element docElement = document.createElementNS(StxtElement.NAMESPACE_URI,
                                                      this.getElementName());
        docElement.appendChild(this.metadata.toDOM(document));
    	docElement.appendChild(this.body.toDOM(document));
    	return docElement;
    }

}
