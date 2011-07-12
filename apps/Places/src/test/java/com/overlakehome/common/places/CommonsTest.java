package com.overlakehome.common.places;

import static org.apache.commons.lang.StringUtils.*;
import junit.framework.Assert;

import org.junit.Test;

public class CommonsTest {
    // Boilerplate Java code busters ...
    @Test
    public void testStringUtils() {
        Assert.assertTrue(isAlpha("abcde"));
        Assert.assertTrue(isNumeric("12345"));
        Assert.assertTrue(isAlphanumeric("12345abc"));

        Assert.assertTrue(isBlank(""));
        Assert.assertTrue(isBlank(null));

        Assert.assertTrue(isAllLowerCase("abcdef"));
        Assert.assertTrue(isAllUpperCase("ABCDEF"));
    }
}

