package com.valentsolutions.stxt.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Block;
import com.valentsolutions.stxt.dom.Heading;

/**
 * This class is responsible for parsing text that has been identified as a 
 * heading, to create a new Heading object.
 */
public class HeadingParser
    extends BlockParser
{
    /**
     * @see com.valentsolutions.stxt.parser.BlockParser#parse(String)
     */
    public Block parse(String buffer)
        throws StxtException
    {
        Pattern p = Pattern.compile("(=+)\\s(.*?)\\s*=*$");
        Matcher m = p.matcher(buffer);
        
        if (! m.find())
            throw new StxtException("Illegal heading format: '" + buffer + "'");
            
        String levels = m.group(1);
        String content = m.group(2);
        
        Heading heading = new Heading(levels.length());
        
        InlineScanner inline = new InlineScanner();
        heading.appendChildren(inline.scan(content));
        
        return heading;
    }
}
