package com.valentsolutions.stxt.dom;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @inv this.term != null
 * @inv this.definitions != null
 * @inv forall Object object in this.definitions
 *      | object instanceof Definition
 */
public class Entry
	implements StxtElement
{
	protected Term term;
	protected ArrayList definitions = new ArrayList();

    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#getElementName()
     */
    protected String getElementName()
    {
        return "entry";
    }
	
	/**
	 * @pre term != null
	 */
	public Entry(Term term)
	{
		this.term = term;
	}

    /**
     * Returns the term.
     * @return Term
     * 
     * @post return != null
     */
    public Term getTerm()
    {
        return term;
    }

    /**
     * Sets the term.
     * @param term The term to set
     * 
     * @pre term != null
     */
    public void setTerm(Term term)
    {
        this.term = term;
    }

	/**
	 * @pre definition != null
	 */
	public void appendDefinition(Definition definition)
	{
		this.definitions.add(definition);
	}

	/**
	 * @post return != null
	 */
	public Definition[] getDefinitions()
	{
		return (Definition[]) this.definitions.toArray(new Definition[0]);
	}

    /**
     * @see com.valentsolutions.stxt.dom.StxtElement#toDOM(Document)
     */
    public Node toDOM(Document document)
    {
    	Element domElement = document.createElementNS(StxtElement.NAMESPACE_URI, this.getElementName());

    	domElement.appendChild(this.term.toDOM(document));

    	Iterator definitionIt = this.definitions.iterator();
    	while (definitionIt.hasNext())
    	{
    		Definition definition = (Definition) definitionIt.next();
    		domElement.appendChild(definition.toDOM(document));
    	}
        return domElement;
    }

	public boolean equals(Object other)
	{
		boolean areEqual = this.getClass().equals(other.getClass());

		if (areEqual)
		{
			Entry otherCont = (Entry) other;

			areEqual = this.getTerm().equals(otherCont.getTerm())
			           && this.getDefinitions().length == otherCont.getDefinitions().length;
			
			if (areEqual)
			{
				for (int i = 0;
					 areEqual
					 && i < this.getDefinitions().length
					 && i < otherCont.getDefinitions().length;
					 ++i)
				{
					areEqual = this.getDefinitions()[i].equals(otherCont.getDefinitions()[i]);
				}
			}
		}    
    	
		return areEqual;	
	}
}