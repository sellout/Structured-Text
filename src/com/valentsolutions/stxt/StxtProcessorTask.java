package com.valentsolutions.stxt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;

/**
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class StxtProcessorTask
    extends Task
{
    public static class FormatEnum
        extends EnumeratedAttribute
    {
        public String[] getValues()
        {
            return new String[] {"html", "xhtml",
                                 "latex", "tex",
                                 "raw",
                                 "rtf",
                                 "stxt",
                                 "text", "txt"};
        }
    }

    protected Vector filesets = new Vector();
    protected boolean isXml;
    protected FormatEnum format;
    protected File dest;
    protected String template;
    protected String stylesheet;
    protected boolean embedstyle;
    
    private StxtFileProcessor processor;
    private String fileExtension;

    public void execute()
        throws BuildException
    {
        try
        {
            setupProcessor();
        }
        catch (StxtException e)
        {
            throw new BuildException(e);
        }

        for (int i = 0; i < filesets.size(); i++)
        {
            FileSet fs = (FileSet) filesets.elementAt(i);
                DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                String[] files = ds.getIncludedFiles();
                processFiles(fs.getDir(getProject()), files);
        }
    }

    public void processFiles(File d, String[] files)
    {
        for (int j = 0; j < files.length; j++)
        {
            try
            {
                System.out.println("Processing file: " + files[j]);
                processor.processFile(d, files[j], dest);
//                System.out.println("Wrote file: " + file.getAbsolutePath());
            }
            catch (FileNotFoundException e)
            {
                System.out.println(e);
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
            catch (StxtException e)
            {
                System.out.println(e);
            }
        }
    }

    private void setupProcessor()
        throws StxtException
    {
        SimpleOutputter.Format format = SimpleOutputter.Format.fromString(this.format.getValue());

        fileExtension = format.getExtension();
        processor = new StxtFileProcessor(format);
        
        if (template != null)
        {
            processor.setTemplate(template);
        }
        
        if (stylesheet != null)
        {
            processor.setStylesheet(stylesheet);
            processor.embedStylesheet(embedstyle);
        }
    }

    public void addFileset(FileSet fileSet)
    {
        filesets.addElement(fileSet);
    }

    /**
     * Sets the dest.
     * @param dest The dest to set
     */
    public void setDest(File dest)
    {
        this.dest = dest;
    }

    /**
     * Sets the format.
     * @param format The format to set
     */
    public void setFormat(FormatEnum format)
    {
        this.format = format;
    }

    /**
     * Sets the isXml.
     * @param isXml The isXml to set
     */
    public void setXml(boolean xml)
    {
        this.isXml = xml;
    }

    /**
     * Sets the template.
     * @param template The template to set
     */
    public void setTemplate(String template)
    {
        this.template = template;
    }
    /**
     * Sets the embedstyle.
     * @param embedstyle The embedstyle to set
     */
    public void setEmbedstyle(boolean embedStyle)
    {
        this.embedstyle = embedStyle;
    }

    /**
     * Sets the stylesheet.
     * @param stylesheet The stylesheet to set
     */
    public void setStylesheet(String stylesheet)
    {
        this.stylesheet = stylesheet;
    }

}
