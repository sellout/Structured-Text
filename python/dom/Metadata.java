package com.valentsolutions.stxt.dom;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 */
public class Metadata
	implements StxtElement
{
    protected Map /* String, String */ headers = new TreeMap();
	protected String TITLE = "title";
	protected String DATE = "date";
	protected String AUTHOR = "author";
	protected String REVISION = "revision";

    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#getElementName()
     */
    protected String getElementName()
    {
        return "metadata";
    }

    public String getHeader(String header)
    {
        return (String) this.headers.get(header.toLowerCase());
    }

    public void setHeader(String header, String value)
    {
        this.headers.put(header.toLowerCase(), value);
    }
    
    public void unsetHeader(String header)
    {
        this.headers.remove(header.toLowerCase());
    }

    /**
     * @see com.valentsolutions.stxt.dom.StxtElement#toDOM(Document)
     */
    public Node toDOM(Document document)
    {
        Element domElement = document.createElementNS(StxtElement.NAMESPACE_URI,
                                                      this.getElementName());
        
        for (Iterator keys = this.headers.keySet().iterator();
             keys.hasNext();
             )
        {
			String key = (String) keys.next();

			Element header = document.createElementNS(StxtElement.NAMESPACE_URI,
			                                          "header");
			header.setAttribute("key", key);
			header.appendChild(document.createTextNode((String) this.headers.get(key)));
			domElement.appendChild(header);
        }

        return domElement;
    }

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object other)
	{
		boolean areEqual = this.getClass().equals(other.getClass());
		
		Metadata otherMeta = (Metadata) other;
		
		areEqual = areEqual && this.headers.size() == otherMeta.headers.size();
		
		for (Iterator headIt = this.headers.keySet().iterator();
			 areEqual && headIt.hasNext();)
		{
			Object key = headIt.next();
			areEqual = this.headers.get(key).equals(otherMeta.headers.get(key));
		}
		
		return areEqual;
	}
}