/**
 * Created on Mar 26, 2003
 *
 * To change this generated comment edit the template variable "filecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of file comments go to
 * Window>Preferences>Java>Code Generation.
 */
package com.valentsolutions.stxt;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import com.valentsolutions.stxt.dom.StxtDocument;

/**
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HtmlOutputter
    extends XmlOutputter
{
    private static final Format FORMAT = Format.HTML;
    public boolean embedStylesheet;
    
    public HtmlOutputter()
    {
        super(FORMAT);
    }

    /**
     * @see com.valentsolutions.stxt.StxtOutputter#processDocument(StxtDocument, Writer)
     */
    public void processDocument(StxtDocument document, Writer out)
        throws StxtException, IOException
    {
        if (embedStylesheet)
        {
            try
            {
                String styleContent = "";
                char[] characters = new char[1024];
                URL prefix = new URL("file:///" + (new File(".")).getAbsolutePath());
                URL stylesheetUrl = new URL(prefix, stylesheet);
                Reader reader = new InputStreamReader(stylesheetUrl.openStream());
                while (reader.read(characters) != -1)
                {
                    styleContent += new String(characters);
                }
                this.outputter.setParameter("stylesheet_content", styleContent);
            }
            catch (IOException e)
            {
                System.err.println(e);
            }
        }

        this.outputter.processDocument(document, out);
    }
}
