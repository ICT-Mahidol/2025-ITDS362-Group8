
package org.jsoup.ITDS362UnitTest;

import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TS8_SafelistIsSafeTagTest {

    private Safelist safelist;

    @BeforeEach
    void setUp() {
        safelist = Safelist.basic(); // whitelist พื้นฐาน เช่น p, b, i, strong, a
    }

    @Test
    void testSafeTag_ValidTag() {
        assertTrue(safelist.isSafeTag("p"));
    }

    @Test
    void testSafeTag_UnsafeTag() {
        assertFalse(safelist.isSafeTag("script"));
    }

    @Test
    void testSafeTag_NullTag() {
        assertFalse(safelist.isSafeTag(null));
    }

    @Test
    void testSafeTag_EmptyTag() {
        assertFalse(safelist.isSafeTag(""));
    }
}