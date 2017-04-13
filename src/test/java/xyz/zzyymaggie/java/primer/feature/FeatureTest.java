/**
 * Copyright © zzyymaggie. All Rights Reserved.
 */
package xyz.zzyymaggie.java.primer.feature;

import xyz.zzyymaggie.java.primer.feature.RecursiveDemo;
import junit.framework.TestCase;

public class FeatureTest  extends TestCase{

    public void testRecursive()
    {
        RecursiveDemo demo = new RecursiveDemo();
        assertEquals("dddddcba", demo.aaa("abcd"));
    }
}
