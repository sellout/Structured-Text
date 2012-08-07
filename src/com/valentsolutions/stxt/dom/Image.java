package com.valentsolutions.stxt.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @inv href != null
 */
public class Image
	implements Block, InlineElement
{
	protected String href;
	protected String title;

    /**
     * Constructor
     * 
     * @pre href != null
     */
    public Image(String uri)
    {
        this.href = uri;
    }

    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#getElementName()
     */
    protected String getElementName()
    {
        return "image";
    }

    /**
     * Returns the title.
     * @return String
     */
    public String getTitle()
    {
        return title;
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
     * Sets the title.
     * @param title The title to set
     */
    public void setTitle(String text)
    {
        this.title = text;
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
     * @see com.valentsolutions.stxt.dom.StxtElement#toDOM(Document)
     */
    public Node toDOM(Document document)
    {
    	Element domElement = document.createElementNS(StxtElement.NAMESPACE_URI, this.getElementName());
    	domElement.setAttribute("xlink:title", this.getTitle());
    	domElement.setAttribute("xlink:href", this.getHref());
        return domElement;
    }

	public boolean equals(Object other)
	{
		boolean areEqual = this.getClass().equals(other.getClass());
		
		if (areEqual)
		{
			Image otherImage = (Image) other;

			areEqual = this.getTitle().equals(otherImage.getTitle())
			           && this.getHref().equals(otherImage.getHref());
		}
		
		return areEqual;
	}
}