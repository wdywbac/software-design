package visitor;

import token.Brace;
import token.NumberToken;
import token.Operation;
import token.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CalcVisitor implements TokenVisitor {
    private Deque<NumberToken> stack;

    @Override
    public void visit(NumberToken token) {
        stack.add(token);
    }

    @Override
    public void visit(Brace token) {
        throw new IllegalStateException();
    }

    @Override
    public void visit(Operation token) {
        NumberToken x = stack.pop();
        NumberToken y = stack.pop();
        stack.add(token.evaluate(x, y));
    }

    public int calculate(List<Token> tokens) {
        stack = new ArrayDeque<>();
        for (Token token : tokens) {
            token.accept(this);
        }
        if (stack.size() != 1) {
            throw new IllegalStateException();
        }
        return stack.pop().getValue();
    }
}
