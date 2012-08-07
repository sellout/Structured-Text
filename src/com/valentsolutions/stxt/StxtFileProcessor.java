package com.valentsolutions.stxt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class StxtFileProcessor
    extends StxtProcessor
{
    protected String fileExtension;

    /**
     * Constructor for StxtFileProcessor.
     * @param format
     */
    public StxtFileProcessor(SimpleOutputter.Format format)
    {
        super(format);
        this.fileExtension = format.getExtension();
    }

    public void processFile(File baseDir, String original, File destination)
        throws FileNotFoundException, IOException, StxtException
    {
        Pattern p = Pattern.compile("\\.stxt$");
        String newFilename = p.matcher(original).replaceAll("");
        newFilename += "." + fileExtension;
        
        File file = new File(destination, newFilename);
        file.getParentFile().mkdirs();
        
        File source;
        if (baseDir != null)
            source = new File(baseDir, original);
        else
            source = new File(original);

        this.processDocument(new FileReader(source),
                             new FileWriter(file));
    }
}
