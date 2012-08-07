package com.valentsolutions.stxt.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @inv this.href != null
 */
public class Link
	extends InlineMarkup
{
	protected String href;

    /**
     * Constructor
     */
    public Link(String uri)
    {
        this.href = uri;
    }

    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#getElementName()
     */
    protected String getElementName()
    {
        return "link";
    }

    /**
     * Returns the href.
     * @return String
     * 
     * @post return != null
     */
    public String getHref()
    {
        return href;
    }

    /**
     * Sets the href.
     * @param href The href to set
     * 
     * @pre href != null
     */
    public void setHref(String uri)
    {
        this.href = uri;
    }

    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#toDOM(Document)
     */
    public Node toDOM(Document document)
    {
        // TODO: bad casting, gotta work on the toDOM inheritance
        Element domElement = (Element) super.toDOM(document);
        domElement.setAttribute("xlink:href", this.href);
        
        return domElement;
    }
}