package org.jsoup.ITDS362UnitTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TS4_InTagElementTextTest {

    /**
     * T1: <div><span>Hello</span></div>
     * C1b1, C2b1, C3b1, C4b2
     * Expected: "Hello"
     */
    @Test
    public void testText_withChildNodes_shouldReturnCombinedText() {
        Element el = Jsoup.parse("<div><span>Hello</span></div>").selectFirst("div");
        assertEquals("Hello", el.text(),
                "Expected combined text from nested span element.");
    }

    /**
     * T2: <p>Hello</p>
     * C1b2, C2b1, C3b1, C4b2
     * Expected: "Hello"
     */
    @Test
    public void testText_noChildNodes_shouldReturnOwnText() {
        Element el = Jsoup.parse("<p>Hello</p>").selectFirst("p");
        assertEquals("Hello", el.text(),
                "Expected text from single paragraph element.");
    }

    /**
     * T3: <div></div>
     * C1b1, C2b2, C3b1, C4b2
     * Expected: ""
     */
    @Test
    public void testText_emptyElement_shouldReturnEmptyString() {
        Element el = Jsoup.parse("<div></div>").selectFirst("div");
        assertEquals("", el.text(),
                "Expected empty string for element with no text content.");
    }

    /**
     * T4: <p>Hello <b>World</b></p>
     * C1b1, C2b1, C3b2, C4b2
     * Expected: "Hello World"
     */
    @Test
    public void testText_withNestedElements_shouldReturnFlattenedText() {
        Element el = Jsoup.parse("<p>Hello <b>World</b></p>").selectFirst("p");
        assertEquals("Hello World", el.text(),
                "Expected flattened combined text from nested bold element.");
    }

    /**
     * T5: <p>  Trimmed Text  </p>
     * C1b1, C2b1, C3b1, C4b1
     * Expected: "Trimmed Text"
     */
    @Test
    public void testText_withLeadingTrailingSpaces_shouldTrimOutput() {
        Element el = Jsoup.parse("<p>  Trimmed Text  </p>").selectFirst("p");
        assertEquals("Trimmed Text", el.text(),
                "Expected leading and trailing spaces to be trimmed.");
    }



    /**
     * T1: <div><span>Hello</span></div>
     * C1b1, C2b1, C3b1, C4b2
     * Expected: "Hello"
     */
    @Test
    public void Function_testText_withChildNodes_shouldReturnCombinedText() {
        Element el = Jsoup.parse("<div><span>Hello</span></div>").selectFirst("div");
        assertEquals("Hello", el.text(),
                "Expected combined text from nested <span> element.");
    }

    /**
     * T2: <p>Hello</p>
     * C1b2, C2b1, C3b1, C4b2
     * Expected: "Hello"
     */
    @Test
    public void Function_testText_noChildNodes_shouldReturnOwnText() {
        Element el = Jsoup.parse("<p>Hello</p>").selectFirst("p");
        assertEquals("Hello", el.text(),
                "Expected text from single <p> element without children.");
    }

    /**
     * T3: <div></div>
     * C1b1, C2b2, C3b1, C4b2
     * Expected: ""
     */
    @Test
    public void Function_testText_emptyElement_shouldReturnEmptyString() {
        Element el = Jsoup.parse("<div></div>").selectFirst("div");
        assertEquals("", el.text(),
                "Expected empty string for element without any text content.");
    }

    /**
     * T4: <p>Hello <b>World</b></p>
     * C1b1, C2b1, C3b2, C4b2
     * Expected: "Hello World"
     */
    @Test
    public void Function_testText_withNestedElements_shouldFlattenText() {
        Element el = Jsoup.parse("<p>Hello <b>World</b></p>").selectFirst("p");
        assertEquals("Hello World", el.text(),
                "Expected flattened text from nested <b> element.");
    }

    /**
     * T5: <p>  Trimmed Text  </p>
     * C1b1, C2b1, C3b1, C4b1
     * Expected: "Trimmed Text"
     */
    @Test
    public void Function_testText_withLeadingTrailingSpaces_shouldTrim() {
        Element el = Jsoup.parse("<p>  Trimmed Text  </p>").selectFirst("p");
        assertEquals("Trimmed Text", el.text(),
                "Expected leading and trailing spaces to be trimmed.");
    }
}