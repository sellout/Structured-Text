package com.valentsolutions.stxt;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;

import com.valentsolutions.stxt.dom.StxtDocument;

/**
 * This outputter provides a number of fairly standard formats in a way that
 * does not require an external stylesheet.
 *
 * @inv this.outputter != null
 */
public class SimpleOutputter
    implements StxtOutputter
{
    /**
     * An enumeration of the file formats this outputter is capable of
     * producing.
     */
    public static class Format
    {
        /** XHTML 1.1 compliant HTML (http://www.w3.org/TR/xhtml11/) */
        public static final Format HTML = new Format("etc/stxt2html.xsl",
                                                     "html");
        /** LaTeX 2e compliant TeX */
        public static final Format LATEX = new Format("etc/stxt2latex.xsl",
                                                      "tex");
        /** STXT Schema */
        public static final Format RAW = new Format(null, "xml");
        /** RTF 1.5 compliant RTF */
        public static final Format RTF = new Format("etc/stxt2rtf.xsl", "rtf");
        /** STXT compliant with this distribution of the engine */
        public static final Format STXT = new Format("etc/stxt2stxt.xsl",
                                                     "stxt");
        /** UTF-8 text file, with ASCII formatting*/
        public static final Format TEXT = new Format("etc/stxt2txt.xsl", "txt");
       
//        private URL stylesheet;
        private File stylesheet;
        private String extension;
        
        private Format(String fileName, String extension)
        {
            if (fileName == null)
                this.stylesheet = null;
            else
            {
                this.stylesheet = new File(fileName);
            }
                
            this.extension = extension;
        }
        
        public File getFile()
        {
            return this.stylesheet;
        }
        
        /**
         * Method fromString.
         * @param string
         */
        public static Format fromString(String string)
            throws StxtException
        {
            Format format;

            if (string == null
                || string.compareToIgnoreCase("raw") == 0)
            {
                format = Format.RAW;
            }
            else if (string.compareToIgnoreCase("html") == 0
                || string.compareToIgnoreCase("xhtml") == 0)
            {
                format = Format.HTML;
            }
            else if (string.compareToIgnoreCase("latex") == 0
                     || string.compareToIgnoreCase("tex") == 0)
            {
                format = Format.LATEX;
            }
            else if (string.compareToIgnoreCase("rtf") == 0)
            {
                format = Format.RTF;
            }
            else if (string.compareToIgnoreCase("stxt") == 0)
            {
                format = Format.STXT;
            }
            else if (string.compareToIgnoreCase("text") == 0
                     || string.compareToIgnoreCase("txt") == 0)
            {
                format = Format.TEXT;
            }
            else
            {
                throw new StxtException("'" + string + "' is not a valid output format");
            }
            
            return format;
        }

        /**
         * Returns the extension.
         * @return String
         */
        public String getExtension()
        {
            return extension;
        }

    }

    protected XsltOutputter outputter;

	/**
	 * @pre format != null
	 */
    protected SimpleOutputter(Format format)
    {
        try
        {
            if (format.getFile() == null)
                outputter = new XsltOutputter(null);
            else
                outputter = new XsltOutputter(new FileReader(format.getFile()));
        }
        catch (IOException e)
        {
            // Shouldn't get here, the file is stored internal to the distro
            System.err.println("Unable to load the XSLT template: " + e);
        }
        catch (StxtException e)
        {
            // Shouldn't get here, the file should be valid
            System.err.println(e);
        }
    }
    
    public static SimpleOutputter makeOutputter(Format format)
    {
        SimpleOutputter outputter;

        if (format == Format.HTML)
            outputter = new HtmlOutputter();
        else if (format == Format.RAW)
            outputter = new XmlOutputter(Format.RAW);
        else
            outputter = new SimpleOutputter(format);
        
        return outputter;
    }

    public void setTemplate(String template)
    {
        this.outputter.setParameter("template", template);
    }
    
    /**
     * @see com.valentsolutions.stxt.StxtOutputter#processDocument(StxtDocument, Writer)
     */
    public void processDocument(StxtDocument document, Writer out)
        throws StxtException, IOException
    {
        this.outputter.processDocument(document, out);
    }
}