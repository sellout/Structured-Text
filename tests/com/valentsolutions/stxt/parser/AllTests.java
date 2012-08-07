package com.valentsolutions.stxt.parser;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * 
 */
public class AllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for com.valent.stxt.parser");
        //$JUnit-BEGIN$
        suite.addTest(new TestSuite(BlockScannerTest.class));
        suite.addTest(new TestSuite(HeadingParserTest.class));
        suite.addTest(new TestSuite(ImageParserTest.class));
        suite.addTest(new TestSuite(InlineScannerTest.class));
        suite.addTest(new TestSuite(LinkParserTest.class));
        suite.addTest(new TestSuite(ListParserTest.class));
        suite.addTest(new TestSuite(MetadataParserTest.class));
        suite.addTest(new TestSuite(StxtParserTest.class));
        //$JUnit-END$
        return suite;
    }
}
