package com.valentsolutions.stxt.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Definition;
import com.valentsolutions.stxt.dom.Entry;
import com.valentsolutions.stxt.dom.Term;

/**
 * 
 */
public class EntryParser
{
    public Entry parse(String buffer)
        throws StxtException
    {
        Pattern p = Pattern.compile("^(.*?)(?=(\\s*(?:\\s*" + BlockScanner.NEWLINE + "?\\s+)?-{2}\\s+)|\\Z)", Pattern.DOTALL);
        Matcher m = p.matcher(buffer);
        
        if (! m.find())
            throw new StxtException("Illegal deflist entry format: '" + buffer + "'");
        
        InlineScanner scanner = new InlineScanner();
        Term term = new Term();
        term.appendChildren(scanner.scan(m.group(1)));
        Entry entry = new Entry(term);
        
        Pattern defPat = Pattern.compile("-{2}\\s*(.*?)(?=(?:\\s*" + BlockScanner.NEWLINE + "?\\s+-{2}\\s+)|\\Z)", Pattern.DOTALL);
        Matcher defMatch = defPat.matcher(buffer.substring(m.end(1)));

        while (defMatch.find())
        {
            Definition definition = new Definition();
            definition.appendChildren(scanner.scan(defMatch.group(1)));
            entry.appendDefinition(definition);
        }
        
        return entry;
    }
}
