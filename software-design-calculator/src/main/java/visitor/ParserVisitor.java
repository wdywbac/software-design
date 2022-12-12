package visitor;

import token.*;

import java.util.*;

public class ParserVisitor implements TokenVisitor {
    private List<Token> polish;
    private Deque<Token> stack;

    @Override
    public void visit(NumberToken token) {
        polish.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token instanceof OpeningBrace) {
            stack.push(token);
        } else {
            while (!stack.isEmpty() && !(stack.peek() instanceof OpeningBrace)) {
                polish.add(stack.pop());
            }
            if (stack.isEmpty()) {
                throw new IllegalStateException();
            }
            stack.pop();
        }
    }

    @Override
    public void visit(Operation token) {
        if ((token instanceof Add) || (token instanceof Subtract)) {
            visitLowerOrderOperation();
        }
        if ((token instanceof Multiply) || (token instanceof Divide)) {
            visitHigherOrderOperation();
        }
        stack.push(token);
    }

    private void visitHigherOrderOperation() {
        while ((stack.peek() instanceof Multiply) || (stack.peek() instanceof Divide)) {
            polish.add(stack.pop());
        }
    }

    private void visitLowerOrderOperation() {
        while ((stack.peek() instanceof Operation)) {
            polish.add(stack.pop());
        }
    }

    public List<Token> toReversePolishNotation(List<Token> tokens) {
        polish = new ArrayList<>();
        stack = new ArrayDeque<>();
        for (Token token : tokens) {
            token.accept(this);
        }
        while (!stack.isEmpty()) {
            polish.add(stack.pop());
        }
        return polish;
    }
}
