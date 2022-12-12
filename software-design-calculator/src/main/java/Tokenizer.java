import token.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Tokenizer {
    private State state = State.Start;
    private final InputStreamReader input;
    private char curChar;
    private int number;
    private final Set<Character> operators = Set.of('(', ')', '+', '-', '*', '/');

    public Tokenizer(InputStream is) throws IOException {
        input = new InputStreamReader(is);
        curChar = (char) input.read();
    }

    public List<Token> tokenize() throws IOException {
        List<Token> tokens = new ArrayList<>();
        Token currentToken = next();
        while (currentToken != null) {
            tokens.add(currentToken);
            currentToken = next();
        }
        return tokens;
    }

    public Token next() throws IOException {
        skipWhitespaces();
        switch (state) {
            case Start -> {
                if (operators.contains(curChar)) {
                    Token token = createToken();
                    nextChar();
                    return token;
                }
                if (Character.isDigit(curChar)) {
                    state = State.Number;
                    number = parseNumber();
                    return next();
                }
                if (curChar == '\uFFFF') {
                    state = State.End;
                    return null;
                }
            }
            case Number -> {
                state = State.Start;
                return new NumberToken(number);
            }
            case Error -> throw new IllegalStateException();
            case End -> {
                return null;
            }
        }
        return null;
    }

    private Token createToken() {
        switch (curChar) {
            case '(' -> {
                return new OpeningBrace();
            }
            case ')' -> {
                return new ClosingBrace();
            }
            case '+' -> {
                return new Add();
            }
            case '-' -> {
                return new Subtract();
            }
            case '*' -> {
                return new Multiply();
            }
            case '/' -> {
                return new Divide();
            }
            default -> throw new IllegalStateException();
        }
    }

    private void skipWhitespaces() throws IOException {
        while (Character.isWhitespace(curChar)) {
            nextChar();
        }
    }

    private void nextChar() throws IOException {
        curChar = (char) input.read();
    }

    private int parseNumber() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(curChar)) {
            sb.append(curChar);
            nextChar();
        }
        return Integer.parseInt(sb.toString());
    }
}
