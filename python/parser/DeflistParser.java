package com.valentsolutions.stxt.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Block;
import com.valentsolutions.stxt.dom.DefList;

/**
 * 
 */
public class DeflistParser
    extends BlockParser
{
    public Block parse(String buffer)
        throws StxtException
    {
        Pattern typePattern = Pattern.compile(";\\s+(.*?)(?=(\\s*" + BlockScanner.NEWLINE + "\\s*;)|\\Z)", Pattern.DOTALL);
        Matcher initialLine = typePattern.matcher(buffer);

        DefList list = new DefList();
        
        while (initialLine.find())
        {
            String content = initialLine.group(1);

            EntryParser parser = new EntryParser();
            list.appendChild(parser.parse(content));
        } 
        
        return list;
    }       
}
