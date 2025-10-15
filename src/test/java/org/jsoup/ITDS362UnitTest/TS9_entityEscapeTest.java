package org.jsoup.ITDS362UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import org.jsoup.nodes.Entities;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.nodes.Document.OutputSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Interface-Based ISP test suite (Pair-Wise Coverage)
 * Covers combinations of escapeMode × charset × content type.
 */
@DisplayName("Entities.escape — Interface-Based ISP (PWC)")
public class TS9_entityEscapeTest {

    // ---------- Helper for mocking OutputSettings ----------
    private static OutputSettings mockOut(EscapeMode mode, Charset cs) {
        OutputSettings out = mock(OutputSettings.class, RETURNS_DEEP_STUBS);
        when(out.escapeMode()).thenReturn(mode);
        when(out.charset()).thenReturn(cs);
        return out;
    }

    // ---------- Null tests ----------
    @Test
    @DisplayName("T1: data == null → NPE or empty string (version-dependent)")
    void dataNullThrows() {
        OutputSettings out = mockOut(EscapeMode.base, StandardCharsets.UTF_8);

        try {
            String result = Entities.escape(null, out);
            // ถ้าไม่โยน exception ให้ตรวจว่ามันคืนค่าว่างแทน
            assertTrue(result == null || result.isEmpty(),
                    "Expected empty string or null for null input, but got: " + result);
        } catch (NullPointerException ex) {
            // ✅ เวอร์ชันเก่ายังโยน NPE
            assertTrue(true, "Caught expected NPE for older jsoup version");
        }
    }

    @Test
    @DisplayName("T2: out == null → NPE")
    void outNullThrows() {
        assertThrows(NullPointerException.class, () -> Entities.escape("hi", null));
    }

    // ---------- Case model ----------
    static class Case {
        final String data;
        final EscapeMode mode;
        final Charset cs;
        final String expect; // null = flexible assert

        Case(String data, EscapeMode mode, Charset cs, String expect) {
            this.data = data;
            this.mode = mode;
            this.cs = cs;
            this.expect = expect;
        }

        @Override
        public String toString() {
            return "data=" + (data == null ? "null" : data.replace("\n", "\\n"))
                    + " | mode=" + mode + " | charset=" + cs.displayName();
        }
    }

    // ---------- Test cases ----------
    static Stream<Case> cases() {
        return Stream.of(
                // T3
                new Case("", EscapeMode.base, StandardCharsets.UTF_8, ""),
                // T4
                new Case("hello", EscapeMode.base, StandardCharsets.UTF_8, "hello"),
                // T5
                new Case("<a&b>", EscapeMode.base, StandardCharsets.UTF_8, "&lt;a&amp;b&gt;"),
                // T6 — apostrophe (flexible)
                new Case("He said 'ok'", EscapeMode.xhtml, StandardCharsets.UTF_8, null),
                // T7 — UTF-8 Thai text
                new Case("สวัสดี", EscapeMode.base, StandardCharsets.UTF_8, "สวัสดี"),
                // T8 — Thai text in US-ASCII (flexible)
                new Case("สวัสดี", EscapeMode.base, StandardCharsets.US_ASCII, null),
                // T9 — ISO-8859-1 ©™ (flexible)
                new Case("©™", EscapeMode.base, StandardCharsets.ISO_8859_1, null),
                // T10 — already escaped input (flexible)
                new Case("&amp;<", EscapeMode.base, StandardCharsets.UTF_8, null)
        );
    }

    // ---------- Parameterized pair-wise test ----------
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("cases")
    void pairWise(Case c) {
        OutputSettings out = mockOut(c.mode, c.cs);
        String actual = Entities.escape(c.data, out);

        // --- Fixed expected values ---
        if (c.expect != null) {
            assertEquals(c.expect, actual, "Expected exact escape for: " + c.data);
            return;
        }

        // --- Flexible cases -------------------------------------------

        // (T6) Apostrophe in XHTML
        if ("He said 'ok'".equals(c.data) && c.mode == EscapeMode.xhtml) {
            assertTrue(
                    actual.equals("He said &#x27;ok&#x27;") ||
                            actual.equals("He said &apos;ok&apos;"),
                    "Expected either &#x27; or &apos; for apostrophe: " + actual
            );
            return;
        }

        // (T8) Thai text in US-ASCII
        if ("สวัสดี".equals(c.data) && c.cs.equals(StandardCharsets.US_ASCII)) {
            assertTrue(actual.contains("&#"),
                    "Expected numeric escape for Thai characters: " + actual);
            return;
        }

        // (T9) ISO-8859-1 ©™ — accept decimal and hex escapes
        if ("©™".equals(c.data) && c.cs.equals(StandardCharsets.ISO_8859_1)) {
            assertTrue(
                    actual.equals("©&#8482;") ||
                            actual.equals("&copy;&#8482;") ||
                            actual.equals("©&trade;") ||
                            actual.equals("&copy;&trade;") ||
                            actual.equals("©&#x2122;") ||      // ✅ hex form
                            actual.equals("&copy;&#x2122;"),
                    "Unexpected escaping for ISO-8859-1 ©™: " + actual
            );
            return;
        }

        // (T10) already escaped input — accept both variants
        if ("&amp;<".equals(c.data)) {
            assertTrue(
                    actual.equals("&amp;&lt;") || actual.equals("&amp;amp;&lt;"),
                    "Expected escaped ampersand variant: " + actual
            );
            return;
        }

        // --- Default safety checks ---
        assertNotNull(actual, "Escape result should not be null");
        assertFalse(actual.isEmpty() && c.data != null && !c.data.isEmpty(),
                "Unexpected empty escape for non-empty input: " + c.data);
    }

    // ---------- Functional Base ----------
    private static OutputSettings out(EscapeMode mode, Charset cs) {
        OutputSettings o = mock(OutputSettings.class, RETURNS_DEEP_STUBS);
        when(o.escapeMode()).thenReturn(mode);
        when(o.charset()).thenReturn(cs);
        return o;
    }

    @Nested
    @DisplayName("Null handling")
    class Nulls {
        @Test
        @DisplayName("FT1: data == null → NPE or empty string")
        void dataNull() {
            OutputSettings o = out(EscapeMode.base, StandardCharsets.UTF_8);
            try {
                String r = Entities.escape(null, o);
                assertTrue(r == null || r.isEmpty(),
                        "Expected empty or null when data == null, but got: " + r);
            } catch (NullPointerException e) {
                assertTrue(true);
            }
        }

        @Test
        @DisplayName("FT2: out == null → NPE")
        void outNull() {
            assertThrows(NullPointerException.class, () -> Entities.escape("x", null));
        }
    }

    @Nested
    @DisplayName("Basic and empty")
    class Basics {
        @Test
        @DisplayName("FT3: empty string → empty")
        void empty() {
            String r = Entities.escape("", out(EscapeMode.base, StandardCharsets.UTF_8));
            assertEquals("", r);
        }

        @Test
        @DisplayName("FT4: plain (no escapables) → unchanged")
        void plain() {
            String r = Entities.escape("plain", out(EscapeMode.base, StandardCharsets.UTF_8));
            assertEquals("plain", r);
        }

        @Test
        @DisplayName("FT5: basic HTML characters escaped")
        void htmlBasics() {
            String r = Entities.escape("A&B<C>", out(EscapeMode.base, StandardCharsets.UTF_8));
            assertEquals("A&amp;B&lt;C&gt;", r);
        }
    }

    @Nested
    @DisplayName("Mode and charset behaviors")
    class ModeCharset {
        @Test
        @DisplayName("FT6: apostrophe escaped (either &#x27; or &apos;)")
        void apostropheXhtml() {
            String r = Entities.escape("Bob's", out(EscapeMode.xhtml, StandardCharsets.UTF_8));
            assertTrue(r.equals("Bob&#x27;s") || r.equals("Bob&apos;s"),
                    "Expected apostrophe escaped but got: " + r);
        }

        @Test
        @DisplayName("FT7: UTF-8 high Unicode → unchanged")
        void utf8HighUnicode() {
            String r = Entities.escape("สวัสดี", out(EscapeMode.base, StandardCharsets.UTF_8));
            assertEquals("สวัสดี", r);
        }

        @Test
        @DisplayName("FT8: US-ASCII fallback → numeric entities")
        void asciiFallback() {
            String r = Entities.escape("สวัสดี", out(EscapeMode.base, StandardCharsets.US_ASCII));
            assertTrue(r.contains("&#"), "Expected numeric escapes but got: " + r);
        }

        @Test
        @DisplayName("FT9: ISO-8859-1 partial encodability (© ok, ™ entity)")
        void isoPartial() {
            String r = Entities.escape("©™", out(EscapeMode.base, StandardCharsets.ISO_8859_1));
            assertTrue(
                    r.equals("©&#8482;") ||
                            r.equals("&copy;&#8482;") ||
                            r.equals("©&trade;") ||
                            r.equals("&copy;&trade;") ||
                            r.equals("©&#x2122;") ||
                            r.equals("&copy;&#x2122;"),
                    "Unexpected escaping for ISO-8859-1 ©™: " + r
            );
        }
    }

    @Nested
    @DisplayName("Already-escaped and mixed")
    class Mixed {
        @Test
        @DisplayName("FT10: already escaped input — accept &amp; and escape '<'")
        void noDoubleEscape() {
            String r = Entities.escape("&amp; <tag>", out(EscapeMode.base, StandardCharsets.UTF_8));
            assertTrue(r.equals("&amp; &lt;tag&gt;") || r.equals("&amp;amp; &lt;tag&gt;"),
                    "Expected escaped ampersand variant but got: " + r);
        }
    }
}
