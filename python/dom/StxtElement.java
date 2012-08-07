package com.valentsolutions.stxt.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Universal superclass for all elements.
 */
public interface StxtElement
{
    public static final String NAMESPACE_URI = "http://valentsoftware.com/stxt";

    /**
     * Returns the element as a DOM node.
     */
    public Node toDOM(Document document);
    
    /**
     * Method fromDOM.
     * @param domDocument
     * @return StxtDocument
     */
    // public static <element> fromDOM(Node node);
}
