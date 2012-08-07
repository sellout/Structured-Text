package com.valentsolutions.stxt.parser;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Block;

/**
 * This is the base class for block parsers (para, list, etc.).
 */
public abstract class BlockParser
{
    /**
     * Parse the text supplied and create a new block element from it. This
     * will also recurse to parse anything inside the block.
     * 
     * @pre buffer != null
     */
    public abstract Block parse(String buffer)
        throws StxtException;
}
