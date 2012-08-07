package com.valentsolutions.stxt.dom;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @inv this.children != null
 * @inv forall Object object in this.children
 *      | object instanceof Entry
 */
public class DefList
	implements Block
{

	protected ArrayList children = new ArrayList();

    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#getElementName()
     */
    protected String getElementName()
    {
        return "deflist";
    }

	/**
	 * @pre entry != null
	 */
	public void appendChild(Entry entry)
	{
		this.children.add(entry);
	}

	/**
	 * @post return != null
	 */
	public Entry[] getChildren()
	{
		return (Entry[]) this.children.toArray(new Entry[0]);
	}

    /**
     * @see com.valentsolutions.stxt.dom.StxtElement#toDOM(Document)
     */
	public Node toDOM(Document document)
    {
    	Element domElement = document.createElementNS(StxtElement.NAMESPACE_URI, this.getElementName());
    	Iterator childIt = this.children.iterator();
    	while (childIt.hasNext())
    	{
    		StxtElement child = (StxtElement) childIt.next();
    		domElement.appendChild(child.toDOM(document));
    	}
        return domElement;
    }

	public boolean equals(Object other)
	{
		boolean areEqual = this.getClass().equals(other.getClass());

		if (areEqual)
		{
			Entry[] thisChildren = this.getChildren();
			Entry[] otherChildren = ((DefList) other).getChildren();

			areEqual = thisChildren.length == otherChildren.length;

			for (int i = 0;
				 areEqual
				 && i < thisChildren.length
				 && i < otherChildren.length;
				 ++i)
			{
				areEqual = thisChildren[i].equals(otherChildren[i]);
			}
		}    
    	
		return areEqual;	
	}
}
