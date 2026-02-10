package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testNormalizePhoneNumber() {
        assertEquals("+38765921456", Main.normalizePhoneNumber("065921456"));
        assertEquals("+38765921456", Main.normalizePhoneNumber("065 921 456"));
        assertEquals("+38765921456", Main.normalizePhoneNumber("065/921-456"));
        assertEquals("+381642661410", Main.normalizePhoneNumber("+381642661410"));
        assertEquals("+38165090700", Main.normalizePhoneNumber("381065090700"));
    }
}
