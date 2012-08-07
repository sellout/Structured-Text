package com.valentsolutions.stxt.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Block;
import com.valentsolutions.stxt.dom.Blockquote;

/**
 * 
 */
public class BlockquoteParser
    extends BlockParser
{
    public Block parse(String buffer)
        throws StxtException
    {
        Pattern typePattern = Pattern.compile("^\\s*>{2,}\\s*(.*)\\s+", Pattern.DOTALL);
        Matcher match = typePattern.matcher(buffer);
        
        if (! match.find())
            throw new StxtException("Illegal blockquote format");
            
        Blockquote blockquote = new Blockquote();

        String content = match.group(1);

        BlockScanner scanner = new BlockScanner();
        blockquote.appendChildren(scanner.scan(content));
        
        return blockquote;
    }
}
