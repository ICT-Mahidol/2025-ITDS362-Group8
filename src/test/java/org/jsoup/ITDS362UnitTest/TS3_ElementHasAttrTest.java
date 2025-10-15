package org.jsoup.ITDS362UnitTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TS3_ElementHasAttrTest {

    @Test
    public void testHasAttr_whenAttributeExists() {
        // C1b2, C2b2, C3b1 → Base test
        Element el = Jsoup.parse("<div id=\"main\"></div>").selectFirst("div");
        assertTrue(el.hasAttr("id"), "Element should have attribute 'id'");
    }

    @Test
    public void testHasAttr_whenAttributeNotExists() {
        // Variation: (C3b2)
        Element el = Jsoup.parse("<div></div>").selectFirst("div");
        assertFalse(el.hasAttr("id"), "Element should not have attribute 'id'");
    }

    @Test
    public void testHasAttr_whenAttributeKeyIsNull() {
        // Variation: (C1b1)
        Element el = Jsoup.parse("<div id=\"main\"></div>").selectFirst("div");
        assertThrows(IllegalArgumentException.class, () -> {
            el.hasAttr(null);
        }, "Should throw IllegalArgumentException when key is null");
    }

    @Test
    public void testHasAttr_whenAttributeKeyIsEmpty() {
        Element el = Jsoup.parse("<div id=\"main\"></div>").selectFirst("div");
        assertFalse(el.hasAttr(""), "Empty key should not throw exception, just return false");
    }


    //Functional Test
    /**
     * T1: attributeKey = "id", element <div id="main">
     * Expected: true
     */
    @Test
    public void functionalTest_testHasAttr_whenAttributeExists() {
        Element el = Jsoup.parse("<div id=\"main\"></div>").selectFirst("div");
        assertTrue(el.hasAttr("id"), "Expected true because element has attribute 'id'");
    }

    /**
     * T2: attributeKey = "class", element <div id="main">
     * Expected: false
     */
    @Test
    public void functionalTest_testHasAttr_whenAttributeNotExists() {
        Element el = Jsoup.parse("<div id=\"main\"></div>").selectFirst("div");
        assertFalse(el.hasAttr("class"), "Expected false because element does not have 'class'");
    }

    /**
     * T3: attributeKey = null, element <div>
     * Expected: IllegalArgumentException
     */
    @Test
    public void functionalTest_testHasAttr_whenKeyIsNull() {
        Element el = Jsoup.parse("<div></div>").selectFirst("div");
        assertThrows(IllegalArgumentException.class, () -> el.hasAttr(null),
                "Expected IllegalArgumentException when attributeKey is null");
    }

    /**
     * T4: attributeKey = "", element <div>
     * Expected: IllegalArgumentException
     */
    @Test
    public void functionalTest_testHasAttr_whenKeyIsEmpty() {
        Element el = Jsoup.parse("<div></div>").selectFirst("div");
        assertFalse(el.hasAttr(""), "Empty key should not throw exception, just return false");
    }

    /**
     * T5: attributeKey = "id", element has no attributes (attributes = null)
     * Expected: false
     */
    @Test
    public void functionalTest_testHasAttr_whenAttributesIsNull() {
        // สร้าง element แบบไม่มี attributes เลย
        Element el = new Element("div");
        // ใน jsoup attributes จะไม่เป็น null แต่เราทดสอบ case ที่เทียบเท่ากับไม่มี attribute
        assertFalse(el.hasAttr("id"), "Expected false because element has no attributes");
    }
}