package org.jsoup.ITDS362UnitTest;

import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TS2 - StringUtil.isBlank()
 * Interface-Based & Functional-Based Tests
 * ใช้ตรวจการทำงานของเมธอด StringUtil.isBlank(String)
 */
@DisplayName("TS2 — StringUtil.isBlank Tests")
public class TS2_StringUtilBlankTest {

    // ---------------- Interface-Based Tests ----------------
    @Nested
    @DisplayName("Interface-Based Tests")
    class InterfaceBase {

        @Test
        @DisplayName("TC1: Empty string → true")
        void testIsBlank_empty() {
            assertTrue(StringUtil.isBlank(""),
                    "Expected true when string is empty");
        }

        @Test
        @DisplayName("TC2: Non-empty string → false")
        void testIsBlank_notEmpty() {
            assertFalse(StringUtil.isBlank("Heeeeey"),
                    "Expected false for non-empty string");
        }
    }

    // ---------------- Functional-Based Tests ----------------
    @Nested
    @DisplayName("Functional-Based Tests")
    class FunctionalBase {

        @Test
        @DisplayName("TC1: Null input → true")
        void testIsBlank_null() {
            assertTrue(StringUtil.isBlank(null),
                    "Expected true when input is null");
        }

        @Test
        @DisplayName("TC2: Empty string → true")
        void testIsBlank_empty() {
            assertTrue(StringUtil.isBlank(""),
                    "Expected true when string is empty");
        }

        @Test
        @DisplayName("TC3: Space-only string → true")
        void testIsBlank_spaceOnly() {
            assertTrue(StringUtil.isBlank(" "),
                    "Expected true when string contains only space");
        }

        @Test
        @DisplayName("TC4: Escape characters (\\n\\t) → true")
        void testIsBlank_escapeCharacter() {
            assertTrue(StringUtil.isBlank("\n\t"),
                    "Expected true when string has only whitespace characters");
        }

        @Test
        @DisplayName("TC5: Regular character → false")
        void testIsBlank_regularChar() {
            assertFalse(StringUtil.isBlank("x"),
                    "Expected false when string has visible characters");
        }
    }
}
