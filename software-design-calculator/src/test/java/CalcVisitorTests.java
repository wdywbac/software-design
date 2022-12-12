import org.junit.jupiter.api.Test;
import visitor.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class CalcVisitorTests {

    @Test
    public void testNumber() throws IOException {
        assertEquals(239, evaluate("239"));
    }

    @Test
    public void testBinaryOperation() throws IOException {
        assertEquals(22, evaluate("1 * 22"));
        assertEquals(22, evaluate("22 / 1"));
        assertEquals(-21, evaluate("1 - 22"));
        assertEquals(23, evaluate("1 + 22"));
    }


    @Test
    public void testBraces() throws IOException {
        assertEquals(-4, evaluate("(   (1 - 3) / 2) * 4"));
        assertEquals(10, evaluate("(3 + 4) * (3 + 7) / 7"));
    }

    private int evaluate(String input) throws IOException {
        CalcVisitor calcVisitor = new CalcVisitor();
        ParserVisitor parserVisitor = new ParserVisitor();
        return calcVisitor.calculate(parserVisitor.toReversePolishNotation(new Tokenizer(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))).tokenize()));
    }
}
