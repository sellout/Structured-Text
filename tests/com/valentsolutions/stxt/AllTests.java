package com.valentsolutions.stxt;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * 
 */
public class AllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for com.valent.stxt");
        //$JUnit-BEGIN$
        suite.addTest(new TestSuite(RawOutputterTest.class));
        suite.addTest(new TestSuite(XsltOutputterTest.class));
        suite.addTest(new TestSuite(SimpleOutputterTest.class));
        //$JUnit-END$
        return suite;
    }
}
