package org.jsoup.ITDS362UnitTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TS7_matchesTest {

    @Test
    void testContainsText_NormalText() {
        Document doc = Jsoup.parse("<div>Hello</div>");
        Element div = doc.selectFirst("div");
        Evaluator evaluator = new Evaluator.ContainsText("Hello");
        assertTrue(evaluator.matches(doc, div));
    }

    @Test
    void testContainsText_EmptyElement() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.selectFirst("div");
        Evaluator evaluator = new Evaluator.ContainsText("Hello");
        assertFalse(evaluator.matches(doc, div));
    }

    @Test
    void testContainsText_WhitespaceOnly() {
        Document doc = Jsoup.parse("<div>   </div>");
        Element div = doc.selectFirst("div");
        Evaluator evaluator = new Evaluator.ContainsText("Hello");
        assertFalse(evaluator.matches(doc, div));
    }

    @Test
    void testContainsText_NestedElement() {
        Document doc = Jsoup.parse("<div><p>Hello</p></div>");
        Element div = doc.selectFirst("div");
        Evaluator evaluator = new Evaluator.ContainsText("Hello");
        assertTrue(evaluator.matches(doc, div));
    }
}