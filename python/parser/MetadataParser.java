package com.valentsolutions.stxt.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Metadata;

/**
 * This class handles parsing a block of metadata.
 */
public class MetadataParser
{
    public Metadata parse(String buffer)
        throws StxtException
    {
        Metadata meta = new Metadata();
        
        Pattern pattern = Pattern.compile("^(.*?):\\s+(.*(" + BlockScanner.NEWLINE + "\\s+\\S+)?)$", Pattern.MULTILINE);
        Matcher match = pattern.matcher(buffer);
        
        Pattern leadingSpace = Pattern.compile(BlockScanner.NEWLINE + "\\s+");
        
        while (match.find())
        {
            String key = match.group(1);
            String value = match.group(2);
            value = leadingSpace.matcher(value).replaceAll(" ");

            meta.setHeader(key, value);            
        }
                
        return meta;
    }        
}
