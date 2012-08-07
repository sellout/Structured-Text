package com.valentsolutions.stxt.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Block;
import com.valentsolutions.stxt.dom.Code;

/**
 * 
 */
public class CodeParser
    extends BlockParser
{
    public Block parse(String buffer)
        throws StxtException
    {
        Pattern p = Pattern.compile("^\\s*:{2,}\\s*" + BlockScanner.NEWLINE + "(.*?)\\s*" + BlockScanner.NEWLINE + "\\s*:{2,}\\s*$", Pattern.DOTALL);
        Matcher m = p.matcher(buffer);
        
        if (! m.find())
            throw new StxtException("Illegal code format: '" + buffer + "'");
            
        String content = m.group(1);
        content = content.replaceAll("\\\\(\\\\|:|\\*|_|\\^|\\~|#|>)", "$1");
        
        Code code = new Code();
        code.appendChild(content);
        return code;
    }
}
