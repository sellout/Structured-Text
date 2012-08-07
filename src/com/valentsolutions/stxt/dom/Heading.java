package com.valentsolutions.stxt.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @inv this.level >= 1
 */
public class Heading
	extends BlockMarkup
    implements Block
{
    protected int level;

    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#getElementName()
     */
    protected String getElementName()
    {
        return "heading";
    }

	/**
	 * @pre level >= 1
	 */
	public Heading(int level)
	{
		this.level = level;
	}

    /**
     * Returns the level.
     * @return int
     * 
     * @post return >= 1
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * Sets the level.
     * @param level The level to set
     * 
     * @pre level >= 1
     */
    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#toDOM(Document)
     */
    public Node toDOM(Document document)
    {
        // TODO: bad casting, gotta work on the toDOM inheritance
        Element domElement = (Element) super.toDOM(document);
        domElement.setAttribute("level", String.valueOf(this.level));
        String content = collectContent();
        content = content.replaceAll("[^\\p{L}\\p{N}\\.\\-_:]", "_");
        domElement.setAttribute("id", "_" + content);
        return domElement;
    }

	public boolean equals(Object other)
	{
		boolean areEqual = super.equals(other);
		
		if (areEqual)
		{
			areEqual = this.level == ((Heading) other).level;
		}
		
		return areEqual;
	}
}
