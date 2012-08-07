package com.valentsolutions.stxt.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Block;
import com.valentsolutions.stxt.dom.Item;
import com.valentsolutions.stxt.dom.List;

/**
 * This class is responsible for parsing the contents of a list block into 
 * individual list items, as well as recognizing the type of list (ordered,
 * unordered, etc.).
 */
public class ListParser 
    extends BlockParser
{
    /**
     * @see com.valentsolutions.stxt.parser.BlockParser#parse(String)
     */
    public Block parse(String buffer)
        throws StxtException
    {
        Pattern typePattern = Pattern.compile("^(\\s*)([\\*\\#])\\s+\\S");
        Matcher initialLine = typePattern.matcher(buffer);
        
        if (! initialLine.find())
            throw new StxtException("Illegal list format: '" + buffer + "'");
            
        // figure out the indentation and list style
        int indent = initialLine.group(1).length();
        String bullet = initialLine.group(2);
        boolean ordered = bullet.equals("#") ? true : false;

        List list = new List(ordered);

        // split into individual items
        String bulletPattern = "\\s{" + indent + "}\\" + bullet + "\\s+";
        Pattern itemPattern = Pattern.compile(
                "(?:\\A|" + BlockScanner.NEWLINE + ")" + bulletPattern + "(.*?)(?=(?:" + BlockScanner.NEWLINE + bulletPattern + ")|\\Z)",
                Pattern.DOTALL);
        Matcher listItems = itemPattern.matcher(buffer);
        
        while (listItems.find())
        {
            String content = listItems.group(1);

            Item item = new Item();
            BlockScanner scanner = new BlockScanner();
            item.appendChildren(scanner.scan(content));
            list.appendChild(item);
        } 
        
        return list;
    }       
}
