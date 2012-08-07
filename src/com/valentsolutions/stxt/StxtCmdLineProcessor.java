package com.valentsolutions.stxt;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import jargs.gnu.CmdLineParser;

/**
 * Takes either a stxt file or an XML file conforming to the stxt schema via
 * standard in, and translates it, sending the output to standard out. This is
 * the most basic of the UIs.
 */
public class StxtCmdLineProcessor
{
    public static void main(String[] args)
        throws IOException, ParserConfigurationException, SAXException, StxtException
    {
        CmdLineParser cmdParser = new CmdLineParser();
        
        CmdLineParser.Option helpOpt = cmdParser.addBooleanOption('h', "help");
//        CmdLineParser.Option xmlOpt = cmdParser.addBooleanOption('x', "xml");
        CmdLineParser.Option formatOpt = cmdParser.addStringOption('f', "format");
        CmdLineParser.Option stylesheetOpt = cmdParser.addStringOption('s', "stylesheet");
        CmdLineParser.Option embedStyleOpt = cmdParser.addBooleanOption('e', "embed-style");
        CmdLineParser.Option destOpt = cmdParser.addStringOption('d', "dest");
        CmdLineParser.Option templateOpt = cmdParser.addStringOption('t', "template");
        
        try 
        {
            cmdParser.parse(args);
        }
        catch (CmdLineParser.OptionException e) 
        {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(2);
        }        
        
        Boolean help = (Boolean) cmdParser.getOptionValue(helpOpt);
        if (help != null && help.booleanValue() == true)
        {
            printUsage();
            System.exit(0);
        }   


        StxtFileProcessor processor;
        String template = (String) cmdParser.getOptionValue(templateOpt);

        String formatValue = (String) cmdParser.getOptionValue(formatOpt);

        SimpleOutputter.Format format = SimpleOutputter.Format.fromString(formatValue);
        if (format == null)
            throw new StxtException(formatValue + " is an unknown outputter format");

        processor = new StxtFileProcessor(format);
        
        if (template != null)
            processor.setTemplate(template);
            
        String stylesheet = (String) cmdParser.getOptionValue(stylesheetOpt);
        if (stylesheet != null)
        {
            processor.setStylesheet(stylesheet);
        }

        Boolean embedStyle = (Boolean) cmdParser.getOptionValue(embedStyleOpt);
        if (embedStyle != null)
        {
            processor.embedStylesheet(embedStyle.booleanValue());
        }
        
        String[] files = cmdParser.getRemainingArgs();
        if (files.length == 0)
        {
            System.err.println("No files to process");
            System.exit(1);
        }

        for (int i = 0; i < files.length; i++)
        {       
            String destDir = ".";
            if (cmdParser.getOptionValue(destOpt) != null)
                destDir = (String) cmdParser.getOptionValue(destOpt);

            processor.processFile(null, files[i], new File(destDir));
        }
    }

    /**
     * Method printUsage.
     */
    private static void printUsage()
    {
        System.out.println(
            "usage: java -jar stxt.jar [options] files\n\n" +
            "       -d[est]  The directory to write all output files to\n" +
            "     -f[ormat]  The output format: [x]html, t[e]xt, [la]tex, stxt, rtf\n" +
            "       -h[elp]  Display this message\n" +
            "-e[mbed-style]  If a stylesheet is given, this tells us to embed its content in\n" +
            "                the output, rather than referencing the stylesheet as a URL. This\n" +
            "                only works when the format is HTML\n" +
            " -s[tylesheet]  The URL of the stylesheet to reference if the output is in an XML\n" +
            "                format.\n" +
            "   -t[emplate]  The template into which the output should be inserted\n" +
            "        -x[ml]  Indicates that the incoming data is in STXT's XML format\n"
        );
        
    }
}
