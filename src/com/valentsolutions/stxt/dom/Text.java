package com.valentsolutions.stxt.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 
 */
public class Text
	implements InlineElement
{
	protected String content;
	
	public Text(String content)
	{
        // FIXME: probably not the best way to fix this thing
		this.content = content.replaceAll("\r", "");
	}
	
	public String getContent()
	{
		return this.content;
	}

    /**
     * @see com.valentsolutions.stxt.dom.StxtElement#toDOM(Document)
     */
    public Node toDOM(Document document)
    {
        return document.createTextNode(this.content);
    }

	public boolean equals(Object other)
	{
		return this.getClass().equals(other.getClass())
		       && this.getContent().equals(((Text) other).getContent());
	}
}
