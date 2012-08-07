package com.valentsolutions.stxt;

import java.io.OutputStreamWriter;
import java.io.StringWriter;

import junit.framework.TestCase;

import com.valentsolutions.stxt.dom.StxtDocument;

/**
 * 
 */
public class SimpleOutputterTest
    extends TestCase
{
    // Gotta make sure we're using the right line ending for the system.
    public static final String ENDL = System.getProperty("line.separator");

    /**
     * Constructor for SimpleOutputterTest.
     * @param arg0
     */
    public SimpleOutputterTest(String arg0)
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

    public void testHTML()
        throws Exception
    {
        String beginning = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + ENDL;
        String middle =
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">" + ENDL +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">" + ENDL +
                "<head>" + ENDL +
                    "<title>Untitled</title>" + ENDL;
        String end =
                "</head>" + ENDL +
                "<body>" + ENDL +
                    "<h2>heading text</h2>" + ENDL +
                    "<p>para text<strong>bold text</strong>" + ENDL +
                        "<em>italic text</em>" + ENDL +
                        "<sup>super text</sup>" + ENDL +
                        "<sub>sub text</sub>" + ENDL +
                        "<kbd>mono text</kbd>" + ENDL +
                        "<a href=\"URI\">link text</a>" + ENDL +
                        "<object data=\"URI\">alt text</object>trailing para text</p>" + ENDL +
                    "<blockquote>" + ENDL +
                        "<p>blockquote text</p>" + ENDL +
                    "</blockquote>" + ENDL +
                    "<pre>code text</pre>" + ENDL +
                    "<ol>" + ENDL +
                        "<li>" + ENDL +
                            "<p>item 1</p>" + ENDL +
                        "</li>" + ENDL +
                        "<li>" + ENDL +
                            "<p>item 2</p>" + ENDL +
                        "</li>" + ENDL +
                        "<li>" + ENDL +
                            "<p>item 3</p>" + ENDL +
                        "</li>" + ENDL +
                    "</ol>" + ENDL +
                    "<dl>" + ENDL +
                        "<dt>term 1</dt>" + ENDL +
                        "<dd>def 1</dd>" + ENDL +
                        "<dt>term 2</dt>" + ENDL +
                        "<dd>def 2</dd>" + ENDL +
                        "<dd>def 3</dd>" + ENDL +
                    "</dl>" + ENDL +
                "</body>" + ENDL +
            "</html>" + ENDL;
            
        String xml_stylesheet = "<?xml-stylesheet href=\"data/test.css\" type=\"text/css\"?>" + ENDL;
        String link = "<link type=\"text/css\" href=\"data/test.css\" rel=\"stylesheet\" />" + ENDL;
        String style =
            "<style type=\"text/css\">" + ENDL +
            "</style>" + ENDL;

        StxtDocument document = DocumentFactory.makeSampleDocument();

        StringWriter writer;
        SimpleOutputter outputter = SimpleOutputter.makeOutputter(SimpleOutputter.Format.HTML);

        writer = new StringWriter();
        outputter.processDocument(document, writer);
        TestCase.assertEquals("raw output did not match expected",
                              beginning + middle + end,
                              writer.toString());

        writer = new StringWriter();
        ((HtmlOutputter) outputter).setStylesheet("data/test.css");
        outputter.processDocument(document, writer);
        TestCase.assertEquals("raw output did not match expected",
                              beginning + xml_stylesheet + middle + link + end,
                              writer.toString());

        writer = new StringWriter();
        ((HtmlOutputter) outputter).embedStylesheet = true;
        outputter.processDocument(document, writer);
        TestCase.assertEquals("raw output did not match expected",
                              beginning + xml_stylesheet + middle + style + end,
                              writer.toString());
    }

    public void testLATEX()
        throws Exception
    {
        String expectedOut =
            "\\documentclass{article}" + ENDL +
            "\\usepackage{hyperref}" + ENDL +
            ENDL +
            "\\begin{document}" + ENDL +
            ENDL +
            "\\subsection{heading text}" + ENDL +
            ENDL +
            ENDL +
            "para text\\textbf{bold text}\\textit{italic text}super textsub text\\texttt{mono text}\\href{URI}{link text}\\hyperimage{URI} (alt text)trailing para text" + ENDL +
            "\\begin{quotation}" + ENDL +
            ENDL +
            ENDL +
            "blockquote text" + ENDL +
            "\\end{quotation}" + ENDL +
            ENDL +
            "\\begin{verbatim}" + ENDL +
            "code text" + ENDL +
            "\\end{verbatim}" + ENDL +
            ENDL +
            "\\begin{enumerate}" + ENDL +
            "\\item " + ENDL + 
            ENDL +
            "item 1" + ENDL +
            "\\item " + ENDL + 
            ENDL +
            "item 2" + ENDL +
            "\\item " + ENDL + 
            ENDL +
            "item 3" + ENDL +
            "\\end{enumerate}" + ENDL +
            ENDL +
            "\\begin{description}" + ENDL +
            "\\item[term 1] def 1" + ENDL +
            "\\item[term 2] def 2def 3" + ENDL +
            "\\end{description}" + ENDL +
            ENDL +
            "\\end{document}" + ENDL +
            "\\end" + ENDL;

        StxtDocument document = DocumentFactory.makeSampleDocument();

        StringWriter writer = new StringWriter();
        SimpleOutputter outputter = new SimpleOutputter(SimpleOutputter.Format.LATEX);
        outputter.processDocument(document, writer);
        TestCase.assertEquals(expectedOut, writer.toString());
    }

    public void testRTF()
        throws Exception
    {
        StxtDocument document = DocumentFactory.makeSampleDocument();

        SimpleOutputter outputter = new SimpleOutputter(SimpleOutputter.Format.RTF);
        outputter.processDocument(document, new OutputStreamWriter(System.out));
    }

    public void testSTXT()
        throws Exception
    {
        String expectedOut =
            "+++++" + ENDL +
            ENDL +
            "+++++" + ENDL +
            ENDL +
            ENDL +
            "= heading text" + ENDL +
            "para text*bold text*_italic text_super textsub textmono text[URI | link text]{URI | alt text}trailing para text" + ENDL +
            ENDL +
            ">> blockquote text" + ENDL +
            ENDL +
            "::" + ENDL +
            "code text" + ENDL +
            "::" + ENDL +
            ENDL +
            ENDL +
            "# item 1" + ENDL +
            "# item 2" + ENDL +
            "# item 3" + ENDL +
            ENDL +
            ENDL +
            "; term 1" + ENDL +
            "\t-- def 1" + ENDL +
            "; term 2" + ENDL +
            "\t-- def 2" + ENDL +
            "\t-- def 3" + ENDL;

        StxtDocument document = DocumentFactory.makeSampleDocument();

        StringWriter writer = new StringWriter();
        SimpleOutputter outputter = new SimpleOutputter(SimpleOutputter.Format.STXT);
        outputter.processDocument(document, writer);
        TestCase.assertEquals(expectedOut, writer.toString());
    }

    public void testTEXT()
        throws Exception
    {
        String expectedOut =
            ENDL +
            "\t\t====== heading text ======" + ENDL +
            ENDL +
            ENDL +
            "para text*bold text*_italic text_[super text](sub text)`mono text`link text (URI)alt text (URI)trailing para text" + ENDL +
            ENDL +
            ">> " + ENDL +
            ENDL +
            "blockquote text" + ENDL +
            ENDL +
            "----------------------------------------------------------" + ENDL +
            "code text" + ENDL +
            "----------------------------------------------------------" + ENDL +
            ENDL +
            "1. item 1" + ENDL +
            "2. item 2" + ENDL +
            "3. item 3" + ENDL +
            "term 1:" + ENDL +
            "\t-- def 1" + ENDL +
            "term 2:" + ENDL +
            "\t-- def 2" + ENDL +
            "\t-- def 3";

        StxtDocument document = DocumentFactory.makeSampleDocument();

        StringWriter writer = new StringWriter();
        SimpleOutputter outputter = new SimpleOutputter(SimpleOutputter.Format.TEXT);
        outputter.processDocument(document, writer);
        TestCase.assertEquals(expectedOut, writer.toString());
    }
}
