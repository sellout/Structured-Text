package com.valentsolutions.stxt.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.valentsolutions.stxt.StxtException;
import com.valentsolutions.stxt.dom.Body;
import com.valentsolutions.stxt.dom.Metadata;
import com.valentsolutions.stxt.dom.StxtDocument;

/**
 * Parser for structured text.
 */
public class StxtParser
{
    /**
     * Parse structured text into an StxtDocument.
     * 
     * @pre in != null
     * @post return != null
     */
    public StxtDocument parse(Reader in)
        throws StxtException, IOException
    {
        String stxt = readBuffer(in);

        StxtDocument doc = new StxtDocument();

        String metaTxt = this.findMetadata(stxt);
        MetadataParser metaparser = new MetadataParser();
        Metadata meta = metaparser.parse(metaTxt);
        doc.setMetadata(meta);
        
        String bodyTxt = this.findBody(stxt);
        Body body = doc.getBody();
        BlockScanner handler = new BlockScanner();
        body.appendChildren(handler.scan(bodyTxt));

        return doc;
    }

    /**
     * Reads the entire contents of the Reader and creates a String from them.
     * 
     * @pre in != null
     * @post return != null
     */
    private String readBuffer(Reader in) 
        throws IOException
    {
        StringBuffer result = new StringBuffer();
        BufferedReader inBuffer = new BufferedReader(in);
        int c;
        
        while ((c = inBuffer.read()) != -1)
            result.append((char) c);
        
        return result.toString();
    }

    /**
     * Find and return the substring that contains the metadata of the document.
     * 
     * @pre input != null
     * @post return != null
     */
    private String findMetadata(String input)
    {
        Pattern p = Pattern.compile("\\A\\+{5,}\\s*" + BlockScanner.NEWLINE + "(.*?)\\+{5,}\\s*" + BlockScanner.NEWLINE, Pattern.DOTALL);
        Matcher m = p.matcher(input);

        String metadata = "";        
        if (m.find())
        {
            metadata = m.group(1);
        }
            
        return metadata;
    }

    /**
     * Find and return the substring that contains the body of the document.
     * 
     * @pre input != null
     * @post return != null
     */
    private String findBody(String input)
    {
        Pattern p = Pattern.compile("\\A(?:\\+{5,}(?:.*?)\\+{5,})?(.*)\\Z", Pattern.DOTALL);
        Matcher m = p.matcher(input);

        String body = "";        
        if (m.find())
        {            
            body = m.group(1);
        }

        return body;    
    }
     
}
