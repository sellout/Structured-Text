package com.valentsolutions.stxt.parser;

import java.io.OutputStreamWriter;

import com.valentsolutions.stxt.HtmlOutputter;
import com.valentsolutions.stxt.SimpleOutputter;
import com.valentsolutions.stxt.dom.Block;
import com.valentsolutions.stxt.dom.Blockquote;
import com.valentsolutions.stxt.dom.Code;
import com.valentsolutions.stxt.dom.DefList;
import com.valentsolutions.stxt.dom.Heading;
import com.valentsolutions.stxt.dom.List;
import com.valentsolutions.stxt.dom.Para;
import com.valentsolutions.stxt.dom.StxtDocument;

import junit.framework.TestCase;

/**
 * Write a comment.
 */
public class BlockScannerTest extends TestCase
{
                                
    private static String[] GeneratePlatformSpecificStrings(String unixForm)
    {
        return new String[] {unixForm,
                             unixForm.replace('\n', '\r'),
                             unixForm.replaceAll("\n", "\r\n")};
    }

    /**
     * Constructor for BlockScannerTest.
     * @param arg0
     */
    public BlockScannerTest(String arg0)
    {
        super(arg0);
    }

    /**
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testScan()
        throws Exception
    {
        String[] platforms = GeneratePlatformSpecificStrings(
                                "This is a normal para, followed by a list\n" +
                                "\n" +
                                "== Heading 2\n"+
                                "\n" +
                                "A final para");

        BlockScanner scanner = new BlockScanner();
        for (int i = 0; i < platforms.length; ++i)
        {            
            Block[] blocks = scanner.scan(platforms[i]);

            assertEquals("pass number " + i, 3, blocks.length);

            assertEquals("pass number " + i, Para.class, blocks[0].getClass());
            assertEquals("pass number " + i, Heading.class, blocks[1].getClass());
            assertEquals("pass number " + i, Para.class, blocks[2].getClass());

            assertEquals("pass number " + i, 2, ((Heading) blocks[1]).getLevel());
        }
    }

    public void testScanForList()
        throws Exception
    {
        String[] platforms = GeneratePlatformSpecificStrings(
                                "This is a normal para, followed by a list\n" +
                                "\n" +
                                " # Item 1\n"+
                                " # Item 2\n"+
                                " # Item 3\n"+
                                "\n" +
                                "A final para");
            
        BlockScanner scanner = new BlockScanner();
        for (int i = 0; i < platforms.length; ++i)
        {            
            Block[] blocks = scanner.scan(platforms[i]);
        
//            StxtDocument doc = new StxtDocument();
//            doc.getBody().appendChildren(blocks);
//            SimpleOutputter out = new SimpleOutputter(SimpleOutputter.Format.HTML);
//            out.processDocument(doc, new OutputStreamWriter(System.out));

            assertEquals("pass number " + i, 3, blocks.length);
    
            assertEquals("pass number " + i, Para.class, blocks[0].getClass());
            assertEquals("pass number " + i, List.class, blocks[1].getClass());
            assertEquals("pass number " + i, Para.class, blocks[2].getClass());
            
            assertEquals("pass number " + i, 3, ((List) blocks[1]).getChildren().length);
        }
    }

    public void testScanForSpacedList()
        throws Exception
    {
        String[] platforms = GeneratePlatformSpecificStrings(
                                "This is a normal para, followed by a list\n" +
                                "\n" +
                                " # Item 1\n" +
                                " # Item 2\n" +
                                " # Item 3\n" +
                                "\n" +
                                " # Item 4\n" +
                                "\n" +
                                "   * sub 1\n" +
                                "   * sub 2\n" +
                                "\n" +
                                "A final para");
            
        BlockScanner scanner = new BlockScanner();
        for (int i = 0; i < platforms.length; ++i)
        {            
            Block[] blocks = scanner.scan(platforms[i]);
            
            StxtDocument doc = new StxtDocument();
            doc.getBody().appendChildren(blocks);
            SimpleOutputter out = new HtmlOutputter();
            out.processDocument(doc, new OutputStreamWriter(System.out));

            assertEquals("pass number " + i, 3, blocks.length);
    
            assertEquals("pass number " + i, Para.class, blocks[0].getClass());
            assertEquals("pass number " + i, List.class, blocks[1].getClass());
            assertEquals("pass number " + i, Para.class, blocks[2].getClass());
            
            assertEquals("pass number " + i, 4, ((List) blocks[1]).getChildren().length);
            assertEquals("pass number " + i, 2, ((List) blocks[1]).getChildren()[3].getChildren().length);
        }
    }

    public void testScanForBlockquote()
        throws Exception
    {
        String[] platforms = GeneratePlatformSpecificStrings(
                                "This is a normal para, followed by a list\n" +
                                "\n" +
                                ">> BQ line 1\n"+
                                "line 2\n"+
                                "line 3\n"+
                                "\n" +
                                "   second BQ para\n" +
                                "\n" +
                                "A final para");
            
        BlockScanner scanner = new BlockScanner();
        for (int i = 0; i < platforms.length; ++i)
        {            
            Block[] blocks = scanner.scan(platforms[i]);
            
            assertEquals("pass number " + i, 3, blocks.length);
    
            assertEquals("pass number " + i, Para.class, blocks[0].getClass());
            assertEquals("pass number " + i, Blockquote.class, blocks[1].getClass());
            assertEquals("pass number " + i, Para.class, blocks[2].getClass());
            
            assertEquals("pass number " + i, 2, ((Blockquote) blocks[1]).getChildren().length);
//            StxtDocument doc = new StxtDocument();
//            doc.getBody().appendChildren(blocks);
//            SimpleOutputter out = new SimpleOutputter(SimpleOutputter.Format.HTML);
//            out.processDocument(doc, new OutputStreamWriter(System.out));
        }
    }

    public void testScanForCode()
        throws Exception
    {
        String[] platforms = GeneratePlatformSpecificStrings(
                                "This is a normal para, followed by a list\n" +
                                "\n" +
                                "   :::::::::::   \n"+
                                "code\n"+
                                "and\n"+
                                "\n" +
                                "   more code\n" +
                                "   :::::::: \n" +
                                "\n" +
                                "A final para");
            
        BlockScanner scanner = new BlockScanner();
        for (int i = 0; i < platforms.length; ++i)
        {            
            Block[] blocks = scanner.scan(platforms[i]);
            
            assertEquals("pass number " + i, 3, blocks.length);
    
            assertEquals("pass number " + i, Para.class, blocks[0].getClass());
            assertEquals("pass number " + i, Code.class, blocks[1].getClass());
            assertEquals("pass number " + i, Para.class, blocks[2].getClass());
            
//            StxtDocument doc = new StxtDocument();
//            doc.getBody().appendChildren(blocks);
//            SimpleOutputter out = new SimpleOutputter(SimpleOutputter.Format.HTML);
//            out.processDocument(doc, new OutputStreamWriter(System.out));
        }
    }

    public void testScanForDefList()
        throws Exception
    {
        String[] platforms = GeneratePlatformSpecificStrings(
                                "This is a normal para, followed by a list\n" +
                                "\n" +
                                "; foo\n"+
                                "  -- the def of foo\n"+
                                "; bar\n"+
                                "  -- the def of bar\n" +
                                "  -- where you get drinks\n" +
                                "\n" +
                                "A final para");
                                
        BlockScanner scanner = new BlockScanner();
        for (int i = 0; i < platforms.length; ++i)
        {            
            Block[] blocks = scanner.scan(platforms[i]);
            
            assertEquals("pass number " + i, 3, blocks.length);
    
            assertEquals("pass number " + i, Para.class, blocks[0].getClass());
            assertEquals("pass number " + i, DefList.class, blocks[1].getClass());
            assertEquals("pass number " + i, Para.class, blocks[2].getClass());
            
            assertEquals("pass number " + i, 2, ((DefList) blocks[1]).getChildren().length);
            assertEquals("pass number " + i, 1, ((DefList) blocks[1]).getChildren()[0].getDefinitions().length);
            assertEquals("pass number " + i, 2, ((DefList) blocks[1]).getChildren()[1].getDefinitions().length);
//            StxtDocument doc = new StxtDocument();
//            doc.getBody().appendChildren(blocks);
//            SimpleOutputter out = new SimpleOutputter(SimpleOutputter.Format.HTML);
//            out.processDocument(doc, new OutputStreamWriter(System.out));
        }
    }
    
    public void testBlankLinesWithWhitespace()
        throws Exception
    {
        String[] platforms = GeneratePlatformSpecificStrings(
                                "This is the first para\n" +
                                " \n" +
                                "Second para");

        BlockScanner scanner = new BlockScanner();
        for (int i = 0; i < platforms.length; ++i)
        {            
            Block[] blocks = scanner.scan(platforms[i]);
            
            assertEquals("pass number " + i, 2, blocks.length);
        }
    }

    public void testTrailingBlankLines()
        throws Exception
    {
        String[] platforms = GeneratePlatformSpecificStrings(
                                "This is the first para\n" +
                                " \n" +
                                "Second para\n" +
                                "\n" +
                                "\n");

        BlockScanner scanner = new BlockScanner();
        for (int i = 0; i < platforms.length; ++i)
        {            
            Block[] blocks = scanner.scan(platforms[i]);
            
            assertEquals("pass number " + i, 2, blocks.length);
        }
    }
}
