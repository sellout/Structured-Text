package com.valentsolutions.stxt.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Bold;
import com.valentsolutions.stxt.dom.InlineElement;
import com.valentsolutions.stxt.dom.Italic;
import com.valentsolutions.stxt.dom.Monospace;
import com.valentsolutions.stxt.dom.Subscript;
import com.valentsolutions.stxt.dom.Superscript;
import com.valentsolutions.stxt.dom.Text;

/**
 * This class scans for inline markup, such as bold, italic, etc., extracts
 * them from the text and returns them to the caller. Unlike the parser 
 * classes, it does not create the container to hold these elements.
 */
public class InlineScanner
{
    /**
     * Scans the buffer and extracts the inline markup found.
     * 
     * @pre buffer != null
     * @post return != null
     */
    public InlineElement[] scan(String buffer) 
        throws StxtException
    {
        ArrayList elements = new ArrayList();
        
        Pattern p = Pattern.compile(
            "(" + Regexes.IGNORED_ELEMENT + "(?:" +
                Regexes.IGNORED_ELEMENT + "|" +
                Regexes.BOLD + "|" +
                Regexes.ITALIC + "|" +
                Regexes.MONOSPACE + "|" +
                Regexes.LINK_START + "|" +
                Regexes.IMAGE_START + "|" +
                Regexes.SUPERSCRIPT + "|" +
                Regexes.SUBSCRIPT + "))|" +
            Regexes.INLINE_LB + "(?:" +
                "(" + Regexes.BOLD + ")|" +
                "(" + Regexes.ITALIC + ")|" +
                "(" + Regexes.MONOSPACE + ")|" +
                "(" + Regexes.LINK_START + ")|" +
                "(" + Regexes.IMAGE_START + ")" +
                ")" + Regexes.INLINE_LA + "|" +
            "(?:" +
                "(" + Regexes.SUPERSCRIPT + ")|" +
                "(" + Regexes.SUBSCRIPT + ")" +
                ")" + Regexes.INLINE_LA,
            Pattern.DOTALL);
        Matcher elemStart = p.matcher(buffer);
        
        // scan for the start of elements
        int pos = 0;
        while (elemStart.find(pos))
        {
            // process any text that came before the element
            String text = buffer.substring(pos, elemStart.start());
            elements.add(new Text(text));
//            System.out.println("T: '" + text + "'");
            
            pos = elemStart.end();
            
            // choose an end pattern to look for
            String endPattern;
            InlineElementParser parser;
            if (elemStart.group(1) != null)
            {
                // tack the match (sans backslash) onto the output
                elements.add(new Text(elemStart.group().substring(1)));
                pos = elemStart.end();
                continue;
            }
            else if (elemStart.group(2) != null)
            {
                endPattern = Regexes.EINLINE_LB + Regexes.BOLD + Regexes.EINLINE_LA;
                parser = new InlineElementParser(Bold.class);
            }
            else if (elemStart.group(3) != null)
            {
                endPattern = Regexes.EINLINE_LB + Regexes.ITALIC + Regexes.EINLINE_LA;
                parser = new InlineElementParser(Italic.class);
            }
            else if (elemStart.group(4) != null)
            {
                endPattern = Regexes.EINLINE_LB + Regexes.MONOSPACE + Regexes.EINLINE_LA;
                parser = new InlineElementParser(Monospace.class);
            }
            else if (elemStart.group(5) != null)
            {
                endPattern = Regexes.EINLINE_LB + Regexes.LINK_END + Regexes.EINLINE_LA;
                parser = new LinkParser();
            }
            else if (elemStart.group(6) != null)
            {
                endPattern = Regexes.EINLINE_LB + Regexes.IMAGE_END + Regexes.EINLINE_LA;
                parser = new ImageParser();
            }
            else if (elemStart.group(7) != null)
            {
                endPattern = Regexes.EINLINE_LB + Regexes.SUPERSCRIPT;
                parser = new InlineElementParser(Superscript.class);
            }
            else if (elemStart.group(8) != null)
            {
                endPattern = Regexes.EINLINE_LB + Regexes.SUBSCRIPT;
                parser = new InlineElementParser(Subscript.class);
            }
            else
            {
                throw new StxtException("Internal error, unrecognized match: '" + 
                    elemStart.group() + "'");    
            }
        
            // find the end of the current element            
            Pattern endp = Pattern.compile(endPattern, Pattern.DOTALL);
            Matcher elemEnd = endp.matcher(buffer);
            
            if (! elemEnd.find(pos))
            {
				// put the delimiter into the output literally.
                elements.add(new Text(elemStart.group()));
				pos = elemStart.end();
            }
            else
            {
				// process the element found.
				InlineElement child = parser.parse(buffer.substring(elemStart.end(), elemEnd.start()));
				elements.add(child);
            
				pos = elemEnd.end();
            }            
        }
     
        // process any trailing text after the last element   
        if (pos != buffer.length())
        {
            elements.add(new Text(buffer.substring(pos)));
//            System.out.println("T: '" + buffer.substring(pos) + "'");
        }
                                                                   
        return (InlineElement[]) elements.toArray(new InlineElement[0]);   
    }
}
