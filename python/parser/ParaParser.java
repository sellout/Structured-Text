package com.valentsolutions.stxt.parser;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Block;
import com.valentsolutions.stxt.dom.Para;

/**
 * Write a comment.
 */
public class ParaParser
    extends BlockParser
{
    public Block parse(String buffer)
        throws StxtException
    {
        Para para = new Para();
        
        InlineScanner scanner = new InlineScanner();
        para.appendChildren(scanner.scan(buffer));

        return para;
    }
}
