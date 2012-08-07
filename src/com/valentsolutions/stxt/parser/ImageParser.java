package com.valentsolutions.stxt.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Image;
import com.valentsolutions.stxt.dom.InlineElement;

/**
 * This class is responsible for parsing the contents of an image.
 */
public class ImageParser
    extends InlineElementParser
{
    public InlineElement parse(String buffer)
        throws StxtException
    {
        Pattern p = Pattern.compile("\\s*(.*?)(?:\\s+\\|?\\s+(.*?)\\s*)?");
        Matcher m = p.matcher(buffer);
        
        if (! m.matches())
            throw new StxtException("Illegal image format: '" + buffer + "'");
            
        String uri = m.group(1);
        Image image = new Image(uri);

        String text = m.group(2);
        if (text != null)
            image.setTitle(text);
            
        return image;
    }
}
