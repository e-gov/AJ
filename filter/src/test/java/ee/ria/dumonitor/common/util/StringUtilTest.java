/*
 * MIT License
 * Copyright (c) 2016 Estonian Information System Authority (RIA)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ee.ria.dumonitor.common.util;

import org.junit.Test;

import ee.ria.dumonitor.common.util.StringUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class StringUtilTest {

  @Test
  public void testIsEmpty() {
    assertTrue(StringUtil.isEmpty(null));
    assertTrue(StringUtil.isEmpty(""));
    assertFalse(StringUtil.isEmpty("test"));
  }

  @Test
  public void testJoin() {
    assertEquals("", StringUtil.join(Collections.<String>emptyList(), ", "));
    assertEquals("str", StringUtil.join(Collections.singletonList("str"), ", "));
    assertEquals("str1, str2", StringUtil.join(Arrays.asList("str1", "str2"), ", "));
  }

  @Test
  public void testSplit() {
    assertArrayEquals(new String[]{null}, StringUtil.split(null, ','));
    assertArrayEquals(new String[]{""}, StringUtil.split("", ','));
    assertArrayEquals(new String[]{"str"}, StringUtil.split("str", ','));
    assertArrayEquals(new String[]{"str1", "str2"}, StringUtil.split("str1.str2", '.'));
    assertArrayEquals(new String[]{"str1", "str2"}, StringUtil.split("str1 , str2", ','));
    assertArrayEquals(new String[]{"str1", "str2"}, StringUtil.split("str1   str2", ' '));
  }

}
