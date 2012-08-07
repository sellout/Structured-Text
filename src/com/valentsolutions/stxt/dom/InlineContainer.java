package com.valentsolutions.stxt.dom;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Represents an element that is capable of containing Inline elements as
 * children.
 * 
 * @inv this.children != null
 * @inv forall Object object in this.children
 *      | object instanceof InlineElement
 */
public abstract class InlineContainer
{
	protected ArrayList children = new ArrayList();

    /**
     * Returns the name of this element as used in an XML document.
     */
    protected abstract String getElementName();

	/**
	 * @pre child != null
	 */
	public void appendChild(InlineElement child)
	{
		this.children.add(child);
	}
    
    /**
     * @pre children != null
     */
    public void appendChildren(InlineElement[] children)
    {
        for (int i = 0; i < children.length; ++i)
        {
            this.children.add(children[i]);
        }
    }
	
	/**
	 * @pre index >= 0
	 * @pre index < this.children.size()
	 * @pre child != null
	 * @post this.children.size() == this.children@pre.size() + 1
	 */
	public void insertChild(int index, InlineElement child)
	{
		this.children.add(index, child);
	}
	
	/**
	 * @pre index >= 0
	 * @pre index < this.children.size()
	 * @post return != null
	 */
	public InlineElement getChild(int index)
	{
		return (InlineElement) this.children.get(index);
	}

	/**
	 * @post return != null
	 */
	public InlineElement[] getChildren()
	{
	 	return (InlineElement[]) this.children.toArray(new InlineElement[0]);
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
			InlineElement[] thisChildren = this.getChildren();
			InlineElement[] otherChildren = ((InlineContainer) other).getChildren();

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
    
    public String collectContent()
    {
        String content = "";
        for (Iterator it = children.iterator(); it.hasNext();)
        {
            InlineElement child = (InlineElement) it.next();
            if (child instanceof Text)
            {
                content += ((Text) child).getContent();
            }
            else if (child instanceof InlineContainer)
            {
                content += ((InlineContainer) child).collectContent();
            }
        }
        
        return content;
    }
}
