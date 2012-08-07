package com.valentsolutions.stxt.dom;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * 
 */
public class AllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for com.valent.stxt.dom");
        //$JUnit-BEGIN$
        suite.addTest(new TestSuite(StxtDocumentTest.class));
        //$JUnit-END$
        return suite;
    }
}
