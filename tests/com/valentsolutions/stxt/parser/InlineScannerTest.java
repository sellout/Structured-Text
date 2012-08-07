package com.valentsolutions.stxt.parser;

import com.valentsolutions.stxt.dom.Bold;
import com.valentsolutions.stxt.dom.Image;
import com.valentsolutions.stxt.dom.InlineElement;
import com.valentsolutions.stxt.dom.Italic;
import com.valentsolutions.stxt.dom.Link;
import com.valentsolutions.stxt.dom.Monospace;
import com.valentsolutions.stxt.dom.Subscript;
import com.valentsolutions.stxt.dom.Superscript;
import com.valentsolutions.stxt.dom.Text;

import junit.framework.TestCase;

/**
 * Write a comment.
 */
public class InlineScannerTest extends TestCase
{

    /**
     * Constructor for InlineScannerTest.
     * @param arg0
     */
    public InlineScannerTest(String arg0)
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

    public void testScanForSimpleBold()
        throws Exception
    {
        String text = "This is *bold*, and *bold with spaces* with other text *and a\n"+
                      " newline* and filler and a bold at the *end of line*\n";
                       
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
		assertEquals(9, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Bold.class, elements[1].getClass());
		assertEquals(Text.class, elements[2].getClass());
		assertEquals(Bold.class, elements[3].getClass());
		assertEquals(Text.class, elements[4].getClass());
		assertEquals(Bold.class, elements[5].getClass());
		assertEquals(Text.class, elements[6].getClass());
		assertEquals(Bold.class, elements[7].getClass());
		assertEquals(Text.class, elements[8].getClass());
    }

    public void testScanForFalseBold()
        throws Exception
    {
        String text = "What about * a random star?";
                       
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(1, elements.length);
		assertEquals(Text.class, elements[0].getClass());
    }

    public void testScanForSimpleItalic()
        throws Exception
    {
        String text = "This is _italic_, and _italic with spaces_ with other text _and a\n"+
                      " newline_ and filler and an italic at the _end of line_\n";
                       
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(9, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Italic.class, elements[1].getClass());
		assertEquals(Text.class, elements[2].getClass());
		assertEquals(Italic.class, elements[3].getClass());
		assertEquals(Text.class, elements[4].getClass());
		assertEquals(Italic.class, elements[5].getClass());
		assertEquals(Text.class, elements[6].getClass());
		assertEquals(Italic.class, elements[7].getClass());
		assertEquals(Text.class, elements[8].getClass());
    }

    public void testScanForSimpleSuper()
        throws Exception
    {
        String text = "This is ^super^, and ^super with spaces^ with other text ^and a\n"+
                      " newline^ and filler and super at the ^end of line^\n";
                       
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(9, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Superscript.class, elements[1].getClass());
		assertEquals(Text.class, elements[2].getClass());
		assertEquals(Superscript.class, elements[3].getClass());
		assertEquals(Text.class, elements[4].getClass());
		assertEquals(Superscript.class, elements[5].getClass());
		assertEquals(Text.class, elements[6].getClass());
		assertEquals(Superscript.class, elements[7].getClass());
		assertEquals(Text.class, elements[8].getClass());
    }

    public void testScanForSimpleSub()
        throws Exception
    {
        String text = "This is ~sub~, and ~sub with spaces~ with other text ~and a\n"+
                      " newline~ and filler and sub at the ~end of line~\n";
                       
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(9, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Subscript.class, elements[1].getClass());
		assertEquals(Text.class, elements[2].getClass());
		assertEquals(Subscript.class, elements[3].getClass());
		assertEquals(Text.class, elements[4].getClass());
		assertEquals(Subscript.class, elements[5].getClass());
		assertEquals(Text.class, elements[6].getClass());
		assertEquals(Subscript.class, elements[7].getClass());
		assertEquals(Text.class, elements[8].getClass());
    }

    public void testScanForEmbeddedSub()
        throws Exception
    {
        String text = "H~2~O";
                       
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(3, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Subscript.class, elements[1].getClass());
		assertEquals(Text.class, elements[2].getClass());
    }

    public void testScanForSimpleMono()
        throws Exception
    {
        String text = "This is :monospace:, and :mono with spaces: with other text :and a\n"+
                      " newline: and filler and mono at the :end of line:\n";
                       
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(9, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Monospace.class, elements[1].getClass());
		assertEquals(Text.class, elements[2].getClass());
		assertEquals(Monospace.class, elements[3].getClass());
		assertEquals(Text.class, elements[4].getClass());
		assertEquals(Monospace.class, elements[5].getClass());
		assertEquals(Text.class, elements[6].getClass());
		assertEquals(Monospace.class, elements[7].getClass());
		assertEquals(Text.class, elements[8].getClass());
    }

    public void testScanForSimpleLink()
        throws Exception
    {
        String text = "This is [http://foo/ | a link].";
                       
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(3, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Link.class, elements[1].getClass());
		assertEquals(Text.class, elements[2].getClass());
    }

    public void testScanForSimpleImage()
        throws Exception
    {
        String text = "This is {http://foo/ | an image}.";
                       
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(3, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Image.class, elements[1].getClass());
		assertEquals(Text.class, elements[2].getClass());
    }
    
    public void testScanForMixedContent()
        throws Exception
    {
        String text = "Para with *bold* and _italic_";
        
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(4, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Bold.class, elements[1].getClass());
		assertEquals(Text.class, elements[2].getClass());
		assertEquals(Italic.class, elements[3].getClass());
    }

    public void testScanForNestedContent()
        throws Exception
    {
        String text = "Para with *bold including _italic_*";
        
        InlineScanner scanner = new InlineScanner();
        InlineElement[] elements = scanner.scan(text);
        
        assertNotNull(elements);
        assertEquals(2, elements.length);
		assertEquals(Text.class, elements[0].getClass());
		assertEquals(Bold.class, elements[1].getClass());

		InlineElement[] boldElements = ((Bold) elements[1]).getChildren();	
		assertEquals(2, boldElements.length);
		assertEquals(Text.class, boldElements[0].getClass());
		assertEquals(Italic.class, boldElements[1].getClass());
    }

}
