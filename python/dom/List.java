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
 *      | object instanceof Item
 */
public class List
	implements Block
{
	protected boolean ordered;
	protected ArrayList children = new ArrayList();

    /**
     * Constructor
     */
	public List(boolean ordered)
	{
		this.ordered = ordered;
	}

    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#getElementName()
     */
    protected String getElementName()
    {
        return "list";
    }

	public boolean isOrdered()
	{
		return this.ordered;
	}

	public void setOrdered(boolean ordered)
	{
		this.ordered = ordered;
	}

	/**
	 * @pre child != null
	 */
	public void appendChild(Item child)
	{
		this.children.add(child);
	}

	/**
	 * @post return != null
	 */
	public Item[] getChildren()
	{
		return (Item[]) this.children.toArray(new Item[0]);
	}

    /**
     * @see com.valentsolutions.stxt.dom.StxtElement#toDOM(Document)
     */
    public Node toDOM(Document document)
    {
        Element domElement = document.createElementNS(StxtElement.NAMESPACE_URI, this.getElementName());
        domElement.setAttribute("ordered", String.valueOf(this.ordered));

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
			areEqual = this.isOrdered() == ((List) other).isOrdered();
		}

		if (areEqual)
		{
			Item[] thisChildren = this.getChildren();
			Item[] otherChildren = ((List) other).getChildren();
			
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