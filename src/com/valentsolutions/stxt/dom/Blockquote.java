package com.valentsolutions.stxt.dom;

/**
 * 
 */
public class Blockquote
	extends BlockContainer
	implements Block
{
    /**
     * @see com.valentsolutions.stxt.dom.BlockContainer#getElementName()
     */
    protected String getElementName()
    {
        return "blockquote";
    }
}