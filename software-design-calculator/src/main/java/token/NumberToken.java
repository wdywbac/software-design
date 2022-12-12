package token;

import visitor.TokenVisitor;

public class NumberToken implements Token {
    private final Integer value;

    public NumberToken(int value) {
        this.value = value;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public int getValue() {
        return value;
    }
}
