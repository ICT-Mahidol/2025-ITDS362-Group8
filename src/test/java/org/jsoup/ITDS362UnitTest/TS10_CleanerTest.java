package org.jsoup.ITDS362UnitTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Safelist;

import static org.junit.jupiter.api.Assertions.*;
//----------------------Interface-Based Tests ------------------------------

@DisplayName("Cleaner.clean — Interface-Based ISP")
public class TS10_CleanerTest {

    private final Cleaner sut = new Cleaner(Safelist.relaxed());

    private static Document makeDoc(String html, String baseUri, boolean prettyPrint) {
        Document d = Jsoup.parse(html, baseUri);
        d.outputSettings().prettyPrint(prettyPrint);
        return d;
    }

    private static Document clean(Cleaner sut, String html, String baseUri, boolean prettyPrint) {
        return sut.clean(makeDoc(html, baseUri, prettyPrint));
    }

    @Test
    @DisplayName("T1: null input → throws IllegalArgumentException")
    void t1_nullInput_throws() {
        assertThrows(IllegalArgumentException.class, () -> sut.clean(null));
    }

    @Test
    @DisplayName("T2: baseUri='' + safe content → preserved; baseUri propagated; prettyPrint=true")
    void t2_emptyBaseUri_safePreserved() {
        Document dirty = makeDoc("<p>Hello</p>", "", true);
        Document cleaned = sut.clean(dirty);

        assertEquals("", cleaned.baseUri());
        Element p = cleaned.body().selectFirst("p");
        assertNotNull(p);
        assertEquals("Hello", p.text());

        assertNotSame(dirty.outputSettings(), cleaned.outputSettings());
        assertEquals(dirty.outputSettings().prettyPrint(), cleaned.outputSettings().prettyPrint());
        assertTrue(cleaned.outputSettings().prettyPrint());
    }

    @Test
    @DisplayName("T3: safe-only content kept; baseUri non-empty; prettyPrint=true")
    void t3_safeOnly_kept() {
        String baseUri = "https://ex";
        Document dirty = makeDoc("<div><p>Hello</p></div>", baseUri, true);
        Document cleaned = sut.clean(dirty);

        assertEquals(baseUri, cleaned.baseUri());
        assertEquals("Hello", cleaned.body().selectFirst("p").text());
        assertTrue(cleaned.outputSettings().prettyPrint());
    }

    @Test
    @DisplayName("T4: unsafe-only content (script) removed")
    void t4_unsafeOnly_removed() {
        Document cleaned = clean(sut, "<script>alert(1)</script>", "https://ex", true);
        assertTrue(cleaned.body().select("script").isEmpty());
        assertFalse(cleaned.body().text().contains("alert(1)"));
    }

    @Test
    @DisplayName("T5: mixed content → safe kept, unsafe attrs removed")
    void t5_mixed_safeKept_unsafeAttrsRemoved() {
        Document cleaned = clean(sut, "<p>x</p><a href=\"/x\" onclick=\"hack()\">go</a>", "https://ex", true);

        assertEquals("x", cleaned.body().selectFirst("p").text());
        Element a = cleaned.body().selectFirst("a");
        assertNotNull(a);
        String href = a.attr("href");
        // ✅ accept both relative and absolute URLs
        assertTrue(href.equals("/x") || href.equals("https://ex/x"),
                "Unexpected href: " + href);
        assertFalse(a.hasAttr("onclick"));
    }


    @Test
    @DisplayName("T6: prettyPrint=false preserved via cloned OutputSettings")
    void t6_outputSettings_preserved() {
        Document dirty = makeDoc("<p>Hello</p>", "https://ex", false);
        Document cleaned = sut.clean(dirty);

        assertNotSame(dirty.outputSettings(), cleaned.outputSettings());
        assertEquals(dirty.outputSettings().prettyPrint(), cleaned.outputSettings().prettyPrint());
        assertFalse(cleaned.outputSettings().prettyPrint());
    }

    @Test
    @DisplayName("T7: large mixed → safe order preserved, unsafe stripped; prettyPrint=false")
    void t7_largeMixed_orderAndStrip() {
        String html =
                "<div><p>safe1</p><script>x()</script><p>safe2</p>" +
                        "<a href=\"#\" style=\"color:red\" onclick=\"x()\">k</a>" +
                        "<ul><li>safe3</li><li><img src=x onerror=bad()></li></ul></div>";

        Document cleaned = clean(sut, html, "https://ex", false);

        assertTrue(cleaned.select("script").isEmpty());
        assertTrue(cleaned.select("[onclick]").isEmpty());
        assertTrue(cleaned.select("[onerror]").isEmpty());
        String text = cleaned.body().text();
        assertTrue(text.indexOf("safe1") < text.indexOf("safe2"));
        assertTrue(text.contains("safe3"));
        assertFalse(cleaned.outputSettings().prettyPrint());
    }

    //----------------------Functional-Based Tests ------------------------------
    @Test
    @DisplayName("FT1: null -> throws IllegalArgumentException (guard)")
    void ft1_nullGuard() {
        assertThrows(IllegalArgumentException.class, () -> sut.clean(null));
    }

    @Test
    @DisplayName("FT2: shell created with baseUri (empty body)")
    void ft2_shellWithBaseUri() {
        Document dirty = makeDoc("", "", true);
        Document cleaned = sut.clean(dirty);
        assertEquals("", cleaned.baseUri());
        assertEquals(0, cleaned.body().childrenSize());
    }

    @Test
    @DisplayName("FT3: safe nodes copied")
    void ft3_safeCopied() {
        Document dirty = makeDoc("<p>Hello</p>", "https://ex", true);
        Document cleaned = sut.clean(dirty);
        assertEquals("Hello", cleaned.body().selectFirst("p").text());
    }

    @Test
    @DisplayName("FT4: unsafe nodes removed")
    void ft4_unsafeRemoved() {
        Document dirty = makeDoc("<p>x</p><script>alert(1)</script>", "https://ex", true);
        Document cleaned = sut.clean(dirty);
        assertTrue(cleaned.select("script").isEmpty());
        assertEquals("x", cleaned.body().selectFirst("p").text());
    }

    @Test
    @DisplayName("FT5: OutputSettings cloned (equal values, different instance)")
    void ft5_outputSettingsCloned() {
        Document dirty = makeDoc("<p>x</p>", "https://ex", false);
        Document cleaned = sut.clean(dirty);
        assertNotSame(dirty.outputSettings(), cleaned.outputSettings());
        assertEquals(dirty.outputSettings().prettyPrint(), cleaned.outputSettings().prettyPrint());
        assertFalse(cleaned.outputSettings().prettyPrint());
    }

    @Test
    @DisplayName("FT6: mixed with unsafe attrs -> removed; prettyPrint=false remains")
    void ft6_mixedUnsafeAttributes() {
        Document dirty = makeDoc("<a href=\"#\" onclick=\"hack()\">go</a>", "https://ex", false);
        Document cleaned = sut.clean(dirty);
        assertTrue(cleaned.select("[onclick]").isEmpty());
        assertFalse(cleaned.outputSettings().prettyPrint());
    }
}
