package com.valentsolutions.stxt;

import java.io.IOException;
import java.io.Writer;

import com.valentsolutions.stxt.dom.StxtDocument;

/**
 * Any class that converts an StxtDocument to some linear format should
 * implement this interface.
 */
public interface StxtOutputter
{
    /**
     * Handles turning the document into some linear form to be written out.
     * 
     * @throws StxtException if there is any trouble converting 'document'
     * @throws IOException if there is trouble writing to 'out'
     * 
     * @pre document != null
     * @pre out != null
     */
	void processDocument(StxtDocument document, Writer out)
		throws StxtException, IOException;
}