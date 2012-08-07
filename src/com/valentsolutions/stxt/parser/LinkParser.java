package com.valentsolutions.stxt.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.InlineElement;
import com.valentsolutions.stxt.dom.Link;
import com.valentsolutions.stxt.dom.Text;

/**
 * This class is responsible for parsing the contents of a link to create a
 * new Link object.
 */
public class LinkParser
    extends InlineElementParser
{
    /**
     * @see com.valentsolutions.stxt.parser.InlineElementParser#parse(String)
     */
    public InlineElement parse(String buffer)
        throws StxtException
    {
        Pattern p = Pattern.compile("\\s*(.*?)(?:\\s+\\|?\\s+(.*?)\\s*)?");
        Matcher m = p.matcher(buffer);
        
        if (! m.matches())
            throw new StxtException("Illegal link format: '" + buffer + "'");
            
        String uri = m.group(1);
        String text = m.group(2);

        Link link = new Link(uri);

        if (text != null)
        {
            InlineScanner childp = new InlineScanner();
            link.appendChildren(childp.scan(text));
        }
        else
        {
            link.appendChild(new Text(uri));
        }
        
        return link;
    }
}
