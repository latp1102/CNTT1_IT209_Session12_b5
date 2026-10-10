package com.example.b5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class B5ApplicationTests {

    @Test
    void testMessage() {
        B5Application app = new B5Application();
        assertEquals("Session12 B5 CI/CD", app.getMessage());
    }
}
