package token;

public class Add extends Operation {
    @Override
    public NumberToken evaluate(NumberToken x, NumberToken y) {
        return new NumberToken(x.getValue() + y.getValue());
    }

    @Override
    public String toString() {
        return "+";
    }
}
