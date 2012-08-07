package com.valentsolutions.stxt.dom;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Represents an element that is only capable of containing Text nodes as 
 * children (eg, code elements).
 * 
 * @inv this.children != null
 * @inv forall Object object in this.children
 *      | object instanceof Text
 */
public abstract class TextContainer
{
	protected ArrayList children = new ArrayList();
    
    protected abstract String getElementName();

	public void appendChild(String child)
	{
		this.children.add(new Text(child));
	}

	/**
	 * @pre child != null
	 */
	public void appendChild(Text child)
	{
		this.children.add(child);
	}

	/**
	 * @post return != null
	 */	
	public Text[] getChildren()
	{
		return (Text[]) this.children.toArray(new Text[0]);
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
			Text[] thisChildren = this.getChildren();
			Text[] otherChildren = ((TextContainer) other).getChildren();

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
