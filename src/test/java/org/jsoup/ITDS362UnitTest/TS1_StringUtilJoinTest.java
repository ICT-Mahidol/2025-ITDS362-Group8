package org.jsoup.ITDS362UnitTest;

import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TS1 — StringUtil.join Tests")
public class TS1_StringUtilJoinTest {

    @Nested
    @DisplayName("Interface Base Tests")
    class InterfaceBase {
        @Test
        @DisplayName("TC1: join with separator")
        void testJoin_withSeparator() {
            assertEquals("ITDS362-So Good",
                    StringUtil.join(Arrays.asList("ITDS362", "So Good"), "-"));
        }

        @Test
        @DisplayName("TC2: join empty list")
        void testJoin_emptyList() {
            assertEquals("", StringUtil.join(Collections.emptyList(), ""));
        }
    }

    @Nested
    @DisplayName("Functional Base Tests")
    class FunctionalBase {
        @Test
        @DisplayName("TC1: join with separator")
        void testJoin_withSeparator() {
            assertEquals("I-Love-You",
                    StringUtil.join(Arrays.asList("I", "Love", "You"), "-"));
        }

        @Test
        @DisplayName("TC2: join empty list")
        void testJoin_emptyList() {
            assertEquals("", StringUtil.join(Collections.emptyList(), ""));
        }

        @Test
        @DisplayName("TC3: null input → NPE")
        void testJoin_withSeparatorX() {
            assertThrows(NullPointerException.class,
                    () -> StringUtil.join((Collection<?>) null, null));
        }
    }
}
