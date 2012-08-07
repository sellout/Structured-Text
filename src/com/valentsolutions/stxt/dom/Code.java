package com.valentsolutions.stxt.dom;

/**
 * 
 */
public class Code
    extends TextContainer
    implements Block
{
    /**
     * @see com.valentsolutions.stxt.dom.InlineContainer#getElementName()
     */
    protected String getElementName()
    {
        return "code";
    }
}
