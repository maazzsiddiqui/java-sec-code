package net.minidev.json.parser;

/**
 * Test-only stub of JSONParserBase to reproduce the vulnerable symbols for tooling/tests.
 * This class is intentionally simple and should only be used for local testing.
 */
public class JSONParserBase {

    /**
     * Simple stub of the original "readArray" method referenced by the advisory.
     * Accepts a JSON-like string that starts with '[' and returns the raw input for testing.
     */
    public Object readArray(String input) throws Exception {
        if (input == null) return null;
        String trimmed = input.trim();
        if (!trimmed.startsWith("[")) {
            throw new IllegalArgumentException("Input does not look like a JSON array");
        }
        // Return a lightweight representation for tests.
        return trimmed;
    }

    /**
     * Simple stub of the original "readObject" method referenced by the advisory.
     * Accepts a JSON-like string that starts with '{' and returns the raw input for testing.
     */
    public Object readObject(String input) throws Exception {
        if (input == null) return null;
        String trimmed = input.trim();
        if (!trimmed.startsWith("{")) {
            throw new IllegalArgumentException("Input does not look like a JSON object");
        }
        // Return a lightweight representation for tests.
        return trimmed;
    }
}
