package com.valentsolutions.stxt.parser;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Block;

/**
 * Write a comment.
 */
abstract public class Parser
{
    abstract public Block parse(String buffer)
        throws StxtException;
}
