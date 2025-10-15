/* Copyright (C) 2025 Chatchai n - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

package org.jsoup.ITDS362UnitTest;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for Element#hasText() method.
 */
public class TS5_6_hasTextAndAddElementTest {

    private Element div;

    @BeforeEach
    public void setUp() {
        div = new Element("div");
    }

    // Test case 1: Element with direct text
    @Test
    public void testHasTextWithDirectText() {
        div.text("Hello");
        assertTrue(div.hasText());
    }

    // Test case 2: Element with child text
    @Test
    public void testHasTextWithChildText() {
        div.append("<p>World</p>");
        assertTrue(div.hasText());
    }

    // Test case 3: Element with no text, only whitespace
    @Test
    public void testHasTextWithOnlyWhitespace() {
        div.text("   ");
        assertFalse(div.hasText());
    }

    // Test case 4: Element with empty text
    @Test
    public void testHasTextWithEmptyText() {
        div.text("");
        assertFalse(div.hasText());
    }

    // Test case 5: Element with no children and no text
    @Test
    public void testHasTextWhenEmpty() {
        assertFalse(div.hasText());
    }

    // Test case 6: Element with a child that has no text
    @Test
    public void testHasTextWithEmptyChild() {
        div.append("<p></p>");
        assertFalse(div.hasText());
    }

    // Test case 7: Element with a mix of text and empty children
    @Test
    public void testHasTextWithMixedContent() {
        div.append("Some text");
        div.append("<span></span>");
        assertTrue(div.hasText());
    }
}