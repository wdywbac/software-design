import token.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTests {
    @Test
    public void testToken() throws IOException {
        assertEquals("/", getTokens("/"));
        assertEquals("239", getTokens("239"));
    }

    @Test
    public void testBinaryOperation() throws IOException {
        assertEquals("1 * 22", getTokens("1 * 22"));
        assertEquals("1 / 22", getTokens("1 / 22"));
        assertEquals("1 - 22", getTokens("1 - 22"));
        assertEquals("1 + 22", getTokens("1 + 22"));
    }

    @Test
    public void testBraces() throws IOException {
        assertEquals("( ( 1 - 3 ) / 2 ) * 4",
                getTokens("(   (1 - 3) / 2) * 4"));
    }

    private String getTokens(String input) throws IOException {
        List<Token> tokens = new Tokenizer(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))).tokenize();
        StringBuilder res = new StringBuilder();
        for (Token token : tokens) {
            res.append(token.toString()).append(" ");
        }
        return res.toString().strip();
    }

}