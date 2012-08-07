package com.valentsolutions.stxt.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @
 */
public class Separator
    implements Block
{

    /**
     * @see com.valentsolutions.stxt.dom.StxtElement#toDOM(org.w3c.dom.Document)
     */
    public Node toDOM(Document document)
    {
        return document.createElementNS(StxtElement.NAMESPACE_URI, "separator");
    }

}
