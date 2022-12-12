package token;

import visitor.TokenVisitor;

public abstract class Operation implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public abstract NumberToken evaluate(NumberToken x, NumberToken y);
}
