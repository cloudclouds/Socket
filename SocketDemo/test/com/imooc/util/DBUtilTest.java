package com.imooc.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBUtilTest {
	@Test
    public void testGetConnection()
    {
    	assertEquals(null,DBUtil.getConnection());
    }
}
