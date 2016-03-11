/**
 * Copyright © zzyymaggie. All Rights Reserved.
 */
package xyz.zzyymaggie.java_primer.feature;

import junit.framework.TestCase;

public class FeatureTest  extends TestCase{

    public void testRecursive()
    {
        RecursiveDemo demo = new RecursiveDemo();
        assertEquals("dddddcba", demo.aaa("abcd"));
    }
}
