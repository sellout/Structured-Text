package com.valentsolutions.stxt.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Block;

/**
 * This class scans for block elements, extracts them from the text and
 * returns them to the caller. Unlike the parser classes, it does not
 * create the container to hold these elements.
 */
public class BlockScanner
{
    public static final String NEWLINE = "(?:\\r\\n?+|\\n)";
    public static final String NEWLINES = "(?:" + NEWLINE + "(?:\\s*" + NEWLINE +")+)";
 
    /**
     * Scan the buffer and extract the block elements found within.
     * 
     * @pre buffer != null
     * @post return != null
     */   
    public Block[] scan(String buffer)
        throws StxtException
    {
        ArrayList blocks = new ArrayList();
        
        Pattern p = Pattern.compile(
            "(?<=\\A|(?:" + NEWLINE + "(?:\\s{0,99}" + NEWLINE + ")))" +    // common look-behind
            "(" +
                "(\\s*=+\\s)|" +            // heading
                "(\\s*)(?=[\\*|#]\\s+)|" +  // list
                "(\\s*:{2,}\\s*" + NEWLINE + ")|" + // code
                "(\\s*>{2,}\\s+)|" +        // blockquote
                "(\\s*;\\s+)|" +            // deflist
                "(\\s*\\\\)(?=|\\*|#|:|>|;)|" + // para, ignored symbol
                "(\\s*)\\S" + // para
            ")",
            Pattern.DOTALL);
        Matcher blockStart = p.matcher(buffer);

        // scan for the start of a block
        int pos = 0;
        while (blockStart.find(pos))
        {
            pos = blockStart.end();
            
            String endPattern;
            BlockParser parser;
            if (blockStart.group(2) != null) // heading
            {
                endPattern = "(?=" + NEWLINES + "|\\Z)";
                parser = new HeadingParser();
            }
            else if (blockStart.group(3) != null) // list
            {
                int indent = blockStart.group().length() - 1 ;
                if (indent < 0)
                    indent = 0;
                    
                endPattern = "(?=(?:" + NEWLINES + "\\s{0," + indent + "}\\S)|\\Z)";
//                endPattern = 
//                    "(?=" +
//                        "(?:" + NEWLINES + 
//                            "\\s{0," + indent + "}&&[^(?:" + blockStart.group() + ")])" + 
//                        "|\\Z" +
//                    ")";
                parser = new ListParser();
            }
            else if (blockStart.group(4) != null) // code
            {
                endPattern = NEWLINE + "\\s*:{2,}\\s*(?=" + NEWLINES + "|\\Z)";
                parser = new CodeParser();
            }
            else if (blockStart.group(5) != null) // blockquote
            {
                int indent = blockStart.group().length() - 1 ;
                if (indent < 0)
                    indent = 0;
                    
                endPattern = "(?=(?:" + NEWLINES + "\\s{0," + indent + "}\\S)|\\Z)";
                parser = new BlockquoteParser();
            }
            else if (blockStart.group(6) != null) // deflist
            {
                endPattern = "(?=" + NEWLINES + "|\\Z)";
                parser = new DeflistParser();
            }
            else if (blockStart.group(7) != null ||
                     blockStart.group(8) != null) // para
            {
                endPattern = "(?=" + NEWLINES + "|\\Z)";
                parser = new ParaParser();
            }
            else
            {
                throw new StxtException(
                    "Internal error, unrecognized block starting at character " + blockStart.start());
            }
            
            // find the end of the current block
            Pattern endp = Pattern.compile(endPattern, Pattern.DOTALL);
            Matcher blockEnd = endp.matcher(buffer);
            
            if (! blockEnd.find(pos))
                throw new StxtException("Unterminated block starting at character " + blockStart.start() +
                    ": '" + buffer.substring(blockStart.start()) + "'");
            
            Block child = parser.parse(buffer.substring(blockStart.start(), blockEnd.end()));
            blocks.add(child);
            
            pos = blockEnd.end();
        }
        
        return (Block[]) blocks.toArray(new Block[0]);
    }
}
