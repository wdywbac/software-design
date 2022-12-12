import org.junit.jupiter.api.Test;
import visitor.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class PrintVisitorTests {

    @Test
    public void testNumber() throws IOException {
        assertEquals("239", print("239"));
    }

    @Test
    public void testBinaryOperation() throws IOException {
        assertEquals("1 22 *", print("1 * 22"));
        assertEquals("1 22 /", print("1 / 22"));
        assertEquals("1 22 -", print("1 - 22"));
        assertEquals("1 22 +", print("1 + 22"));
    }

    @Test
    public void testBraces() throws IOException {
        assertEquals("1 3 - 2 / 4 *", print("(   (1 - 3) / 2) * 4"));
        assertEquals("1 2 + 4 + 7 8 / - 2 1 - * 1 /", print("(1 + 2 + 4 - 7 / 8) * (2 - 1) / 1"));
    }

    private String print(String input) throws IOException {
        ParserVisitor parserVisitor = new ParserVisitor();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new PrintVisitor(out).print(parserVisitor.toReversePolishNotation(new Tokenizer(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))).tokenize()));
        return out.toString().strip();
    }

}